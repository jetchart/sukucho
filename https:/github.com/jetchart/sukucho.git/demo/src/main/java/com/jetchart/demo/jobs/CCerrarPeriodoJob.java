package com.jetchart.demo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jetchart.demo.business.email.CEmailBusiness;
import com.jetchart.demo.business.periodo.CPeriodoBusiness;
import com.jetchart.demo.service.periodo.CPeriodoService;
import com.jetchart.demo.util.CUtil;

public class CCerrarPeriodoJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(CCerrarPeriodoJob.class);
	
        public void execute(JobExecutionContext ctx) throws JobExecutionException {
        	try {
				CPeriodoService.cerrarPeriodoVigente();
				logger.info("Periodo cerrado");
				CPeriodoService.enviarMailPeriodosPendientesAviso();
				logger.info("Eenvio de mails realizado");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
}
