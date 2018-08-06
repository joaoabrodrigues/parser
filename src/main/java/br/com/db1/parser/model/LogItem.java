package br.com.db1.parser.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "LOG")
public class LogItem {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Column(name = "LOG_DATE")
    private LocalDateTime date;

    @Column
    private String ip;

    @Column
    private String method;

    @Column
    private String statusCode;

    @Column
    private String userAgent;

    public LogItem(Long id, LocalDateTime date, String ip, String method, String statusCode, String userAgent) {
        this.id = id;
        this.date = date;
        this.ip = ip;
        this.method = method;
        this.statusCode = statusCode;
        this.userAgent = userAgent;
    }
}