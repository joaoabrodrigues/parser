package br.com.db1.parser.repository;

import br.com.db1.parser.AbstractLogTest;
import br.com.db1.parser.model.LogItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogItemRepositoryTest extends AbstractLogTest {

    @Autowired
    private LogItemRepository repository;

    @Test
    public void shouldFindByDateBetweenHour() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0);
        List<LogItem> items = repository.findByDateBetween(startDate, startDate.plusHours(1L).minusNanos(1L));

        Assert.assertEquals(4465, items.size());
    }

    @Test
    public void shouldFindByDateBetweenDaily() {
        LocalDateTime startDate = LocalDateTime.of(2017, 1, 1, 0, 0, 0);
        List<LogItem> items = repository.findByDateBetween(startDate, startDate.plusDays(1L).minusNanos(1L));

        Assert.assertEquals(116484, items.size());
    }

    @Test
    public void shouldFindByIp() {
        List<LogItem> items = repository.findByIp("192.168.11.231");
        Assert.assertEquals(211, items.size());
    }

    @Test
    public void shouldFindByIp2() {
        List<LogItem> items = repository.findByIp("192.168.122.135");
        Assert.assertEquals(229, items.size());
    }
}