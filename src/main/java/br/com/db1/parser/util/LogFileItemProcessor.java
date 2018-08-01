//package br.com.db1.parser.util;
//
//import org.springframework.batch.item.ItemProcessor;
//
//import br.com.db1.parser.model.LogFile;
//
//public class LogFileItemProcessor implements ItemProcessor<LogFile, LogFile> {
//	
//    @Override
//    public LogFile process(LogFile logFile) throws Exception {
//        LogFile transformedLogFile = LogFile.builder().withDate(logFile.getDate())
//        											  .withIp(logFile.getIp())
//        											  .withMethod(logFile.getMethod())
//        											  .withStatusCode(logFile.getStatusCode())
//        											  .withUserAgent(logFile.getUserAgent())
//        											  .build();
//
//        return transformedLogFile;
//    }
//
//}
