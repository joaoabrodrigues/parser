package br.com.db1.parser;

import br.com.db1.parser.model.DurationType;
import br.com.db1.parser.service.ParserService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

public abstract class AbstractLogTest {

    @Autowired
    protected ParserService service;

    private static boolean isFileImported = false;

    @Before
    public void setup() throws IOException {
        setProperties();
        if (!isFileImported) {
            service.print();
            isFileImported = true;
        }
    }

    private void setProperties() throws IOException {
        ReflectionTestUtils.setField(service, "accessLog", getFileName());
        ReflectionTestUtils.setField(service, "startDate", LocalDateTime.of(2017, 1, 1, 15, 0, 0));
        ReflectionTestUtils.setField(service, "threshold", 200);
        ReflectionTestUtils.setField(service, "duration", DurationType.HOURLY);
    }

    private String getFileName() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("access.log");

        File file = new File(Optional.ofNullable(url).orElseThrow(IOException::new).getFile());
        return file.getAbsolutePath();
    }
}