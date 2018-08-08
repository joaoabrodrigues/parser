package br.com.db1.parser.exception.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler, HandlerUtil {

    private static final Log LOG = LogFactory.getLog(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) {
        try {
            handle(response, exception, HttpServletResponse.SC_FORBIDDEN);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}