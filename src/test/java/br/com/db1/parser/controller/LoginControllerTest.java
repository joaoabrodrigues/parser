package br.com.db1.parser.controller;

import br.com.db1.parser.security.AccountCredentials;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LoginControllerTest extends AbstractMockMvc {

    private JacksonTester<AccountCredentials> json;

    @Test
    public void shouldThrowException() throws Exception {
        JsonContent<AccountCredentials> content = json.write(new AccountCredentials("teste", "teste"));

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content.getJson()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnToken() throws Exception {
        JsonContent<AccountCredentials> content = json.write(new AccountCredentials("test", "test"));

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content.getJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['token']", is(String.class)));
    }

}