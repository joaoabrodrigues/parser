package br.com.db1.parser.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentials {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
