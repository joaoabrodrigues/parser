package br.com.db1.parser;

import br.com.db1.parser.service.ParserService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

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
        URL url = classLoader.getResource("access.log");

        File file = new File(Optional.ofNullable(url).orElseThrow(IOException::new).getFile());

        service.importLogFile(file.getAbsolutePath());
    }
}
