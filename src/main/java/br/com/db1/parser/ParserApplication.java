package br.com.db1.parser;

import br.com.db1.parser.model.DurationType;
import br.com.db1.parser.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class ParserApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ParserApplication.class, args);

        context.getBean(ParserService.class).print();
    }
}
