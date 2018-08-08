package br.com.db1.parser.controller;

import br.com.db1.parser.model.User;
import br.com.db1.parser.security.TokenAuthenticationService;
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
public class SignupControllerTest extends AbstractMockMvc {

    private JacksonTester<User> json;

    @Test
    public void shouldCreateUser() throws Exception {
        JsonContent<User> content = json.write(new User("test2", "test2"));

        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content.getJson()))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldThrowException() throws Exception {
        String token = TokenAuthenticationService.createToken("test");
        JsonContent<User> content = json.write(new User("test", "test"));

        mockMvc.perform(post("/signup")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content.getJson()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$['error']", is("Username already exists.")));
        ;
    }
}