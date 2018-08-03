package br.com.db1.parser;

import br.com.db1.parser.service.ParserService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

public abstract class AbstractLogTest {

    @Autowired
    protected ParserService service;

    private static boolean isFileImported = false;

    @Before
    public void setUp() throws IOException {
        if (!isFileImported) {
            importFile();
            isFileImported = true;
        }
    }

    private void importFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("access.log").getFile());

        service.importLogFile(file.getAbsolutePath());
    }
}
