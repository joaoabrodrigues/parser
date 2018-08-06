package br.com.db1.parser.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class ImportFileRequestDTO {

    @Getter
    @Setter
    private String path;
}