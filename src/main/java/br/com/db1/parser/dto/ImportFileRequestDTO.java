package br.com.db1.parser.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImportFileRequestDTO {

    @Getter
    private String path;
}