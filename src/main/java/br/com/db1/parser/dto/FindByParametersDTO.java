package br.com.db1.parser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ImportFileRequestDTO {

    @Getter
    @Setter
    private String path;
}