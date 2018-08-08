package br.com.db1.parser.exception.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable, HandlerUtil {

    private static final Log LOG = LogFactory.getLog(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        try {
            handle(response, exception, HttpServletResponse.SC_UNAUTHORIZED);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}