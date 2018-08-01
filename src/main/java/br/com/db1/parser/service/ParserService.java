package br.com.db1.parser.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.db1.parser.model.LogFile;
import br.com.db1.parser.repository.LogFileRepository;

@Service
public class ParserService {
	
	@Autowired
	private LogFileRepository repository;

	public void importLogFile() {
		String fileName = "D:\\access.log";
		
		List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			list = stream.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		list.forEach(s -> {
			String[] line = s.split("\\|");
			System.out.println(Arrays.toString(line));
			
			LogFile log = LogFile.builder()
				   .withDate(LocalDateTime.parse(line[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")))
				   .withIp(line[1])
				   .withMethod(line[2])
				   .withStatusCode(line[3])
				   .withUserAgent(line[4])
				   .build();
			
			repository.saveAndFlush(log);
		});
	}
	
	public List<LogFile> getLogFile(){
		return repository.findAll();
	}

}
