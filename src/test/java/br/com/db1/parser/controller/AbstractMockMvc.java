package br.com.db1.parser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@WithMockUser
@WebAppConfiguration
@ContextConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public abstract class AbstractMockMvc {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        JacksonTester.initFields(this, new ObjectMapper());
    }
}
