package br.com.db1.parser.service;

import br.com.db1.parser.model.DurationType;
import br.com.db1.parser.repository.LogItemRepository;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParserServiceTest {

    @Autowired
    private ParserService service;

    @Test
    public void step1_shouldImportFile() {
        service.importLogFile("/home/local/DB1/joao.rodrigues/DB1/parser/src/main/resources/access.log");
    }

    @Test
    public void step2_shouldFindLogsWithParameters() {
        Map logs = service.findLogs(LocalDateTime.of(2017, 1, 1, 15, 0, 0), DurationType.HOURLY, 200);
        Assert.assertEquals(2, logs.size());
        Assert.assertTrue(logs.containsKey("192.168.106.134"));
        Assert.assertTrue(logs.containsKey("192.168.11.231"));
    }
}