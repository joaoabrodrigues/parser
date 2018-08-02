package br.com.db1.parser.repository;

import br.com.db1.parser.model.LogItem;
import br.com.db1.parser.service.ParserService;
import br.com.db1.parser.service.ParserServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogItemRepositoryTest {

    @Autowired
    private LogItemRepository repository;

    @Autowired
    private ParserService service;

    @Before
    public void before() {
        service.importLogFile("/home/local/DB1/joao.rodrigues/DB1/parser/src/main/resources/access.log");
    }

    @Test
    public void shouldFindByDateBetween() {

    }

    @Test
    public void shouldFindByIp() {
        List<LogItem> items = repository.findByIp("192.168.11.231");
        Assert.assertEquals(211, items.size());
    }
}