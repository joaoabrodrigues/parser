package br.com.db1.parser.controller;

import br.com.db1.parser.exception.BusinessException;
import br.com.db1.parser.model.User;
import br.com.db1.parser.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private SignupService service;

    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity signup(@RequestBody User user) throws BusinessException {
        service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}