package br.com.db1.parser;

import br.com.db1.parser.exception.BusinessException;
import br.com.db1.parser.service.ParserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@SpringBootApplication
@Configuration
public class ParserApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws IOException, BusinessException {
        ConfigurableApplicationContext context = SpringApplication.run(ParserApplication.class, args);

        context.getBean(ParserService.class).print();
    }
}