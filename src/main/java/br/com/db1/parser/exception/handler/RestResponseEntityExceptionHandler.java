package br.com.db1.parser.exception.handler;

import br.com.db1.parser.dto.ErrorDTO;
import br.com.db1.parser.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(ex.getMessage()));
    }
}