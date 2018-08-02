package br.com.db1.parser.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.db1.parser.model.DurationType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import br.com.db1.parser.model.LogItem;
import br.com.db1.parser.repository.LogItemRepository;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class ParserService {

    private static final Log LOG = LogFactory.getLog(ParserService.class);

    @Value("${duration:}")
    private DurationType duration;

    @Value("${startDate:}")
    @DateTimeFormat(pattern = "yyyy-MM-dd.HH:mm:ss")
    private LocalDateTime startDate;

    @Value("${threshold:}")
    private Integer threshold;

    @Value("${accesslog:}")
    private String accessLog;

    @Autowired
    private LogItemRepository repository;

    public void print() {
        importLogFile(accessLog);
        findLogs(startDate, duration, threshold);
    }

    public void importLogFile(String fileName) {
        LOG.info(String.format("Importing file: %s ", fileName));

        List<String> list = getFileToList(fileName);

        List<LogItem> logList = new ArrayList<>();

        list.forEach(s -> {
            String[] line = s.split("\\|");

            LogItem log = LogItem.builder()
                    .withDate(LocalDateTime.parse(line[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")))
                    .withIp(line[1])
                    .withMethod(line[2])
                    .withStatusCode(line[3])
                    .withUserAgent(line[4])
                    .build();

            logList.add(log);
        });

        repository.saveAll(logList);
        LOG.info("File imported");
    }

    private List<String> getFileToList(String fileName) {
        List<String> list = new ArrayList<>();

        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            list = stream.collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Error loading log file. " + e.getMessage(), e);
        }
        return list;
    }

    public Map findLogs(LocalDateTime startDate, DurationType duration, Integer threshold) {
        LOG.info(String.format("Finding ips by %s, %s, %d", startDate.toString(), duration.toString(), threshold));

        LocalDateTime endDate = DurationType.isDaily(duration) ? startDate.plusDays(1L) : startDate.plusHours(1L);

        List<LogItem> logs = repository.findByDateBetween(startDate, endDate);

        HashMap<String, Long> filteredItems = logs.stream().collect(groupingBy(i -> i.getIp(), HashMap::new, counting()));
        filteredItems.values().removeIf(count -> count < threshold);

        LOG.info("IPs found: \n" + filteredItems);
        return filteredItems;
    }
}
