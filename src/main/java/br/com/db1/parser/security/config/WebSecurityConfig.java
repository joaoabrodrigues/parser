package br.com.db1.parser.security.config;


import br.com.db1.parser.exception.handler.CustomAuthenticationEntryPoint;
import br.com.db1.parser.security.AuthManager;
import br.com.db1.parser.security.filter.JWTAuthenticationFilter;
import br.com.db1.parser.security.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthEntryPoint;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", new AuthManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}