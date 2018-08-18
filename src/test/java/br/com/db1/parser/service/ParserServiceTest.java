package br.com.db1.parser.service;

import br.com.db1.parser.AbstractLogTest;
import br.com.db1.parser.dto.FindByParametersDTO;
import br.com.db1.parser.exception.BusinessException;
import br.com.db1.parser.model.DurationType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserServiceTest extends AbstractLogTest {

    @Test
    public void shouldFindLogTwoWithParametersHourly() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0);
        List<FindByParametersDTO> logs = service.findLogs(startDate, DurationType.HOURLY, 200);

        Assert.assertEquals(2, logs.size());
    }

    @Test
    public void shouldFindIpsWithParametersHourly() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0);
        List<FindByParametersDTO> logs = service.findLogs(startDate, DurationType.HOURLY, 200);


        Assert.assertEquals("192.168.106.134", logs.get(0).getIp());
        Assert.assertEquals("192.168.11.231", logs.get(1).getIp());
    }

    @Test
    public void shouldFindRequestNumbersWithParametersHourly() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0);
        List<FindByParametersDTO> logs = service.findLogs(startDate, DurationType.HOURLY, 200);

        Assert.assertEquals(232, logs.get(0).getNumberOfRequests().intValue());
        Assert.assertEquals(211, logs.get(1).getNumberOfRequests().intValue());
    }

    @Test
    public void shouldFindLogTwoWithParametersDaily() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        List<FindByParametersDTO> logs = service.findLogs(startDate, DurationType.DAILY, 500);

        Assert.assertEquals(15, logs.size());
    }

    @Test
    public void shouldFindIpsWithParametersDaily() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        List<FindByParametersDTO> logs = service.findLogs(startDate, DurationType.DAILY, 500);

        Assert.assertEquals("192.168.143.177", logs.get(0).getIp());
        Assert.assertEquals("192.168.51.205", logs.get(1).getIp());
        Assert.assertEquals("192.168.33.16", logs.get(2).getIp());
        Assert.assertEquals("192.168.185.164", logs.get(3).getIp());
        Assert.assertEquals("192.168.162.248", logs.get(4).getIp());
        Assert.assertEquals("192.168.129.191", logs.get(5).getIp());
        Assert.assertEquals("192.168.31.26", logs.get(6).getIp());
        Assert.assertEquals("192.168.38.77", logs.get(7).getIp());
        Assert.assertEquals("192.168.219.10", logs.get(8).getIp());
        Assert.assertEquals("192.168.52.153", logs.get(9).getIp());
        Assert.assertEquals("192.168.102.136", logs.get(10).getIp());
        Assert.assertEquals("192.168.199.209", logs.get(11).getIp());
        Assert.assertEquals("192.168.203.111", logs.get(12).getIp());
        Assert.assertEquals("192.168.62.176", logs.get(13).getIp());
        Assert.assertEquals("192.168.206.141", logs.get(14).getIp());
    }

    @Test
    public void shouldFindRequestNumbersWithParametersDaily() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        List<FindByParametersDTO> logs = service.findLogs(startDate, DurationType.DAILY, 500);

        Assert.assertEquals(729, logs.get(0).getNumberOfRequests().intValue());
        Assert.assertEquals(610, logs.get(1).getNumberOfRequests().intValue());
        Assert.assertEquals(584, logs.get(2).getNumberOfRequests().intValue());
        Assert.assertEquals(528, logs.get(3).getNumberOfRequests().intValue());
        Assert.assertEquals(623, logs.get(4).getNumberOfRequests().intValue());
        Assert.assertEquals(747, logs.get(5).getNumberOfRequests().intValue());
        Assert.assertEquals(591, logs.get(6).getNumberOfRequests().intValue());
        Assert.assertEquals(743, logs.get(7).getNumberOfRequests().intValue());
        Assert.assertEquals(533, logs.get(8).getNumberOfRequests().intValue());
        Assert.assertEquals(541, logs.get(9).getNumberOfRequests().intValue());
        Assert.assertEquals(513, logs.get(10).getNumberOfRequests().intValue());
        Assert.assertEquals(640, logs.get(11).getNumberOfRequests().intValue());
        Assert.assertEquals(601, logs.get(12).getNumberOfRequests().intValue());
        Assert.assertEquals(582, logs.get(13).getNumberOfRequests().intValue());
        Assert.assertEquals(536, logs.get(14).getNumberOfRequests().intValue());
    }

    @Test(expected = BusinessException.class)
    public void shouldThrowIOException() throws IOException, BusinessException {
        service.importLogFile("xablau");
    }
}