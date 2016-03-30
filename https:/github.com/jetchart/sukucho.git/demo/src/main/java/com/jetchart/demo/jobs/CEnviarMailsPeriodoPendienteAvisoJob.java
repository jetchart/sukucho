package com.jetchart.demo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CEnviarMailsPeriodoPendienteAvisoJob implements Job {
	
	private static final Logger logger = LoggerFactory.getLogger(CEnviarMailsPeriodoPendienteAvisoJob.class);
	
        public void execute(JobExecutionContext ctx) throws JobExecutionException {
                
			try {
				logger.info("Envio mails de cierre de periodo");
			} catch (Exception e) {
				logger.error("Ocurrió un error al enviar los mails de cierre de periodo", e);
				e.printStackTrace();
			}
        }
}
