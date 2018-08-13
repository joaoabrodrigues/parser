package br.com.db1.parser.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class FindByParametersDTO {

    private String ip;

    private Long numberOfRequests;
}