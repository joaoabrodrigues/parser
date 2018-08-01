package br.com.db1.parser.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db1.parser.model.DurationType;
import br.com.db1.parser.model.LogFile;
import br.com.db1.parser.service.ParserService;

@RestController
public class ParserController {
	
	@Value("${duration:}")
	private DurationType duration;
	
	@Value("${startDate:}")
	@DateTimeFormat(pattern = "yyyy-MM-dd.HH:mm:ss")
	private LocalDateTime startDate;
	
	@Value("${threshold:}")
	private Integer threshold;
	
	@Autowired
	private ParserService parserService;
	
	@GetMapping(path = "/")
	public String print() {
		return duration + " " + startDate + " " + threshold;
	}
	
	@GetMapping(path = "/test")
	public String loadLogFile() {
		parserService.importLogFile();
		return "aaa";
	}
	
	@GetMapping(path = "/test2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<LogFile> getLogFile() {
		return parserService.getLogFile();
	}
}
