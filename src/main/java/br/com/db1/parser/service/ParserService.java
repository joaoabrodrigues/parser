package br.com.db1.parser.service;

import br.com.db1.parser.model.DurationType;
import br.com.db1.parser.model.LogItem;
import br.com.db1.parser.repository.LogItemRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private final LogItemRepository repository;

    @Autowired
    public ParserService(LogItemRepository repository) {
        this.repository = repository;
    }

    public void print() throws IOException {
        importLogFile(accessLog);
        findLogs(startDate, duration, threshold);
    }

    public void importLogFile(String fileName) throws IOException {
        LOG.info(String.format("Importing file: %s ", fileName));

        List<String> list = getFileToList(fileName);

        List<LogItem> logList = list.stream()
                .map(s -> s.split("\\|"))
                .map(line -> LogItem.builder()
                        .withDate(LocalDateTime.parse(line[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")))
                        .withIp(line[1])
                        .withMethod(line[2])
                        .withStatusCode(line[3])
                        .withUserAgent(line[4])
                        .build())
                .collect(Collectors.toList());

        repository.saveAll(logList);
        LOG.info("File imported");
    }

    private List<String> getFileToList(String fileName) throws IOException {
        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            LOG.error("Error loading log file. " + e.getMessage(), e);
            throw e;
        }
    }

    public Map findLogs(LocalDateTime startDate, DurationType duration, Integer threshold) {
        LOG.info(String.format("Finding IPs by %s, %s, %d", startDate.toString(), duration.toString(), threshold));

        LocalDateTime endDate = DurationType.isDaily(duration) ? startDate.plusDays(1L).minusNanos(1L) : startDate.plusHours(1L).minusNanos(1L);

        List<LogItem> logs = repository.findByDateBetween(startDate, endDate);

        HashMap<String, Long> filteredItems = logs.stream().collect(groupingBy(LogItem::getIp, HashMap::new, counting()));
        filteredItems.values().removeIf(count -> count < threshold);

        LOG.info("IPs found: \n" + filteredItems);
        return filteredItems;
    }
}
