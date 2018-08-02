package br.com.db1.parser.dto;

import br.com.db1.parser.model.DurationType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class LogRequestDTO {

    private DurationType duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd.HH:mm:ss")
    private LocalDateTime startDate;

    private Integer threshold;

    public DurationType getDuration() {
        return duration;
    }

    public void setDuration(DurationType duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
}
