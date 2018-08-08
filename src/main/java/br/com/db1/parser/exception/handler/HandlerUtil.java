package br.com.db1.parser.exception.handler;

import br.com.db1.parser.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

interface HandlerUtil {

    default void handle(HttpServletResponse response, RuntimeException e, Integer status) throws IOException {
        ErrorDTO error = new ErrorDTO(e.getMessage());

        String json = new ObjectMapper().writeValueAsString(error);
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(json);
    }
}
