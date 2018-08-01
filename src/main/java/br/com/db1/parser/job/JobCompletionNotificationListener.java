//package br.com.db1.parser.job;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.BatchStatus;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobExecutionListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JobCompletionNotificationListener implements JobExecutionListener {
//
//	private static final Logger LOG = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
//
//	@Override
//	public void afterJob(JobExecution jobExecution) {
//		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
//			LOG.info("!!! JOB FINISHED! Time to verify the results");
//		}
//	}
//
//	@Override
//	public void beforeJob(JobExecution jobExecution) {
//		LOG.info("Starting job");
//	}
//}
