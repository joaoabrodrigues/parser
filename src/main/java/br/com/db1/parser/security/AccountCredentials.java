package br.com.db1.parser.security;

import lombok.Getter;
import lombok.Setter;

public class AccountCredentials {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
