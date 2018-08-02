package br.com.db1.parser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

/**
 *
 * Configuration class to use different types 
 * of data in property sources, like @Value
 * @author joaoabrodrigues
 *
 */
@Configuration
@ComponentScan
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static ConversionService conversionService() {
        return new DefaultFormattingConversionService();
    }
}