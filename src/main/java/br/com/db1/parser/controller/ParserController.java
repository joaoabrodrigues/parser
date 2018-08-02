package br.com.db1.parser.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import br.com.db1.parser.dto.LogRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.db1.parser.model.DurationType;
import br.com.db1.parser.model.LogItem;
import br.com.db1.parser.service.ParserService;

import javax.websocket.server.PathParam;

@RestController
public class ParserController {
	
	@Autowired
	private ParserService parserService;

	@PostMapping(path = "/importFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity loadLogFile(@RequestBody String path) {
		parserService.importLogFile(path);
		return ResponseEntity.ok("");
	}

	@GetMapping(path = "/log", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity findLogs(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd.HH:mm:ss") LocalDateTime startDate,
								   @RequestParam("duration") DurationType duration,
								   @RequestParam("threshold") Integer threshold){
		return ResponseEntity.ok(parserService.findLogs(startDate, duration, threshold));
	}
}
