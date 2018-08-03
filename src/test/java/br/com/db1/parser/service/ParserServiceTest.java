package br.com.db1.parser.service;

import br.com.db1.parser.AbstractLogTest;
import br.com.db1.parser.model.DurationType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserServiceTest extends AbstractLogTest {

    @Test
    public void shouldFindLogTwoWithParametersHourly() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0);
        Map logs = service.findLogs(startDate, DurationType.HOURLY, 200);

        Assert.assertEquals(2, logs.size());
    }

    @Test
    public void shouldFindIpsWithParametersHourly() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0);
        Map logs = service.findLogs(startDate, DurationType.HOURLY, 200);

        Assert.assertTrue(logs.containsKey("192.168.106.134"));
        Assert.assertTrue(logs.containsKey("192.168.11.231"));
    }

    @Test
    public void shouldFindRequestNumbersWithParametersHourly() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0);
        Map logs = service.findLogs(startDate, DurationType.HOURLY, 200);

        Assert.assertEquals(232L, logs.get("192.168.106.134"));
        Assert.assertEquals(211L, logs.get("192.168.11.231"));
    }

    @Test
    public void shouldFindLogTwoWithParametersDaily() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        Map logs = service.findLogs(startDate, DurationType.DAILY, 500);

        Assert.assertEquals(15, logs.size());
    }

    @Test
    public void shouldFindIpsWithParametersDaily() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        Map logs = service.findLogs(startDate, DurationType.DAILY, 500);

        Assert.assertTrue(logs.containsKey("192.168.143.177"));
        Assert.assertTrue(logs.containsKey("192.168.51.205"));
        Assert.assertTrue(logs.containsKey("192.168.33.16"));
        Assert.assertTrue(logs.containsKey("192.168.185.164"));
        Assert.assertTrue(logs.containsKey("192.168.162.248"));
        Assert.assertTrue(logs.containsKey("192.168.129.191"));
        Assert.assertTrue(logs.containsKey("192.168.31.26"));
        Assert.assertTrue(logs.containsKey("192.168.38.77"));
        Assert.assertTrue(logs.containsKey("192.168.219.10"));
        Assert.assertTrue(logs.containsKey("192.168.52.153"));
        Assert.assertTrue(logs.containsKey("192.168.102.136"));
        Assert.assertTrue(logs.containsKey("192.168.199.209"));
        Assert.assertTrue(logs.containsKey("192.168.203.111"));
        Assert.assertTrue(logs.containsKey("192.168.62.176"));
        Assert.assertTrue(logs.containsKey("192.168.206.141"));
    }

    @Test
    public void shouldFindRequestNumbersWithParametersDaily() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        Map logs = service.findLogs(startDate, DurationType.DAILY, 500);

        Assert.assertEquals(729L, logs.get("192.168.143.177"));
        Assert.assertEquals(610L, logs.get("192.168.51.205"));
        Assert.assertEquals(584L, logs.get("192.168.33.16"));
        Assert.assertEquals(528L, logs.get("192.168.185.164"));
        Assert.assertEquals(623L, logs.get("192.168.162.248"));
        Assert.assertEquals(747L, logs.get("192.168.129.191"));
        Assert.assertEquals(591L, logs.get("192.168.31.26"));
        Assert.assertEquals(743L, logs.get("192.168.38.77"));
        Assert.assertEquals(533L, logs.get("192.168.219.10"));
        Assert.assertEquals(541L, logs.get("192.168.52.153"));
        Assert.assertEquals(513L, logs.get("192.168.102.136"));
        Assert.assertEquals(640L, logs.get("192.168.199.209"));
        Assert.assertEquals(601L, logs.get("192.168.203.111"));
        Assert.assertEquals(582L, logs.get("192.168.62.176"));
        Assert.assertEquals(536L, logs.get("192.168.206.141"));
    }
}