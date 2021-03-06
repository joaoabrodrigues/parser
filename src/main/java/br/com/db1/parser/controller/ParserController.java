package br.com.db1.parser.controller;

import br.com.db1.parser.dto.ImportFileRequestDTO;
import br.com.db1.parser.exception.BusinessException;
import br.com.db1.parser.model.DurationType;
import br.com.db1.parser.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@PreAuthorize("isAuthenticated()")
public class ParserController {

    private final ParserService parserService;

    @Autowired
    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @PostMapping(path = "/importFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity loadLogFile(@RequestBody ImportFileRequestDTO request) throws IOException, BusinessException {
        parserService.importLogFile(request.getPath());
        return ResponseEntity.ok("");
    }

    @GetMapping(path = "/log", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity findLogs(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd.HH:mm:ss") LocalDateTime startDate,
                                   @RequestParam("duration") DurationType duration,
                                   @RequestParam("threshold") Integer threshold) {
        return ResponseEntity.ok(parserService.findLogs(startDate, duration, threshold));
    }
}
