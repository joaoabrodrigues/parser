package br.com.db1.parser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class LoginResponseDTO {

    @Getter
    @Setter
    private String token;
}
