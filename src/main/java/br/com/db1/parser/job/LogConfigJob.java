//package br.com.db1.parser.job;
//
//import javax.sql.DataSource;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecutionListener;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.step.tasklet.TaskletStep;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//
//import br.com.db1.parser.model.LogFile;
//import br.com.db1.parser.util.LogFileItemProcessor;
//
//@Configuration
//@EnableBatchProcessing
//public class LogConfigJob {
//	
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//    
//    @Bean
//    public FlatFileItemReader<LogFile> reader() {
//        return new FlatFileItemReaderBuilder<LogFile>()
//            .name("LogFileItemReader")
//            .resource(new ClassPathResource("access.log"))
//            .delimited().delimiter("|")
//            .names(new String[]{"date", "ip", "method", "statusCode", "userAgent"})
//            .fieldSetMapper(new BeanWrapperFieldSetMapper<LogFile>() {{
//                setTargetType(LogFile.class);
//            }})
//            .build();
//    }
//
//    @Bean
//    public LogFileItemProcessor processor() {
//        return new LogFileItemProcessor();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<LogFile> writer(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<LogFile>()
//            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//            .sql("INSERT INTO log (date, ip, method, statusCode, userAgent) VALUES (:date, :ip, :method, :statusCode, :userAgent)")
//            .dataSource(dataSource)
//            .build();
//    }
//    
////    @Bean
////    public Job importUserJob(JobCompletionNotificationListener listener, TaskletStep step1) {
////    	
////    	
//////        return jobBuilderFactory.get("importUserJob")
//////            .incrementer(null)
//////            .listener(listener)
//////            .flow(step1)
//////            .end()
//////            .build();
////    }
//
////    @Bean
////    public TaskletStep step1(JdbcBatchItemWriter<LogFile> writer) {
////        return stepBuilderFactory.get("step1")
////            .<LogFile, LogFile> chunk(10)
////            .reader(reader())
////            .processor(processor())
////            .writer(writer)
////            .build();
////    }
//}