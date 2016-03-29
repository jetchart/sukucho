package com.jetchart.demo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCerrarPeriodoJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(CCerrarPeriodoJob.class);
	
        public void execute(JobExecutionContext ctx) throws JobExecutionException {
        	logger.info("ENVIAR MAILS de periodo cerrado");
        }
}
