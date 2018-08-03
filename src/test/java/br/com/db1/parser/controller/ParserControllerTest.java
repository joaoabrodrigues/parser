package br.com.db1.parser.controller;

import br.com.db1.parser.model.DurationType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void step1_shouldImportFile() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("access.log");
        File file = new File(Optional.ofNullable(url).orElseThrow(IOException::new).getFile());

        mockMvc.perform(post("/importFile")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"path\":\"" + file.getAbsolutePath() + "\"}")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void step2_shouldFindLogs() throws Exception {
        String startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));

        mockMvc.perform(get("/log")
                .param("startDate", startDate)
                .param("duration", DurationType.HOURLY.toString())
                .param("threshold", "100")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['192.168.106.134']", is(232)))
                .andExpect(jsonPath("$['192.168.11.231']", is(211)));
    }
}
