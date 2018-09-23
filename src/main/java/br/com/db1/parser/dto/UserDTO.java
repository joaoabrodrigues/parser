package br.com.db1.parser.dto;

import br.com.db1.parser.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;

    private String password;

    public User toEntity() {
        return new User(username, password);
    }
}

