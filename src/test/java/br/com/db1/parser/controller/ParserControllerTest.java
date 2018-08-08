package br.com.db1.parser.controller;

import br.com.db1.parser.dto.ImportFileRequestDTO;
import br.com.db1.parser.model.DurationType;
import br.com.db1.parser.security.TokenAuthenticationService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.MediaType;

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

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParserControllerTest extends AbstractMockMvc {

    private JacksonTester<ImportFileRequestDTO> json;

    @Test
    public void step1_shouldImportFile() throws Exception {
        String token = TokenAuthenticationService.createToken("test");
        JsonContent<ImportFileRequestDTO> content = json.write(new ImportFileRequestDTO(getFileName()));

        mockMvc.perform(post("/importFile")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content.getJson()))
                .andExpect(status().isOk());
    }

    @Test
    public void step2_shouldFindLogs() throws Exception {
        String startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));

        String token = TokenAuthenticationService.createToken("test");
        mockMvc.perform(get("/log")
                .header("Authorization", "Bearer " + token)
                .param("startDate", startDate)
                .param("duration", DurationType.HOURLY.toString())
                .param("threshold", "100")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['192.168.106.134']", is(232)))
                .andExpect(jsonPath("$['192.168.11.231']", is(211)));
    }

    @Test
    public void step3_shouldReturnUnauthorized() throws Exception {
        String startDate = LocalDateTime.of(2017, 1, 1, 15, 0, 0)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));

        mockMvc.perform(get("/log")
                .param("startDate", startDate)
                .param("duration", DurationType.HOURLY.toString())
                .param("threshold", "100"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void step4_shouldThrowUnsupportedMediaType() throws Exception {
        String token = TokenAuthenticationService.createToken("test");
        mockMvc.perform(post("/importFile")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void step5_shouldThrowMethodNotAllowed() throws Exception {
        String token = TokenAuthenticationService.createToken("test");
        mockMvc.perform(get("/importFile")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isMethodNotAllowed());
    }

    private String getFileName() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("access.log");
        File file = new File(Optional.ofNullable(url).orElseThrow(IOException::new).getFile());
        return file.getAbsolutePath();
    }
}