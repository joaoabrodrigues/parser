package br.com.db1.parser.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(name = "STATUS_CODE")
    private String statusCode;

    @Column(name = "USER_AGENT")
    private String userAgent;

    public LocalDateTime getDate() {
        return date;
    }

    public String getIp() {
        return ip;
    }

    public String getMethod() {
        return method;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public static LogFileBuilder builder() {
        return new LogFileBuilder();
    }

    public static class LogFileBuilder {
        LogItem log;

        public LogFileBuilder() {
            log = new LogItem();
        }

        public LogFileBuilder withDate(LocalDateTime date) {
            log.date = date;
            return this;
        }

        public LogFileBuilder withIp(String ip) {
            log.ip = ip;
            return this;
        }

        public LogFileBuilder withMethod(String method) {
            log.method = method;
            return this;
        }

        public LogFileBuilder withStatusCode(String statusCode) {
            log.statusCode = statusCode;
            return this;
        }

        public LogFileBuilder withUserAgent(String userAgent) {
            log.userAgent = userAgent;
            return this;
        }

        public LogItem build() {
            return log;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("date", date)
                .append("ip", ip)
                .append("method", method)
                .append("statusCode", statusCode)
                .append("userAgent", userAgent)
                .toString();
    }
}