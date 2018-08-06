package br.com.db1.parser.dto;

import br.com.db1.parser.model.DurationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class LogRequestDTO {

    @Getter
    @Setter
    private DurationType duration;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd.HH:mm:ss")
    private LocalDateTime startDate;

    @Getter
    @Setter
    private Integer threshold;
}
