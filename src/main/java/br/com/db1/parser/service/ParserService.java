package br.com.db1.parser.service;

import br.com.db1.parser.exception.BusinessException;
import br.com.db1.parser.model.DurationType;
import br.com.db1.parser.model.LogItem;
import br.com.db1.parser.repository.LogItemRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public void print() throws IOException, BusinessException {
        importLogFile(accessLog);
        findLogs(startDate, duration, threshold);
    }

    public void importLogFile(String fileName) throws IOException, BusinessException {
        LOG.info(String.format("Importing file: %s ", fileName));

        List<String> list = getFileToList(fileName);

        List<LogItem> logList = list.stream()
                .map(s -> s.split("\\|"))
                .map(line -> LogItem.builder()
                        .date(LocalDateTime.parse(line[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")))
                        .ip(line[1])
                        .method(line[2])
                        .statusCode(line[3])
                        .userAgent(line[4])
                        .build())
                .collect(Collectors.toList());

        repository.saveAll(logList);
        LOG.info("File imported");
    }

    private List<String> getFileToList(String fileName) throws IOException, BusinessException {
        Stream<String> stream = null;
        try {
            Path path = Paths.get(fileName);
            boolean isDirectory = Files.isDirectory(path);
            boolean exists = Files.exists(path);
            if (isDirectory || !exists) {
                throw new BusinessException(isDirectory ? "Is a directory." : "File not found.");
            }
            stream = Files.lines(path);
            return stream.collect(Collectors.toList());
        } catch (NoSuchFileException | FileNotFoundException e){
            LOG.error("Error loading log file. " + e.getMessage(), e);
            throw new BusinessException("File not found.");
        } catch (IOException e) {
            LOG.error("Error loading log file. " + e.getMessage(), e);
            throw e;
        } finally {
            Optional.ofNullable(stream).ifPresent(Stream::close);
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