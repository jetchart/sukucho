package com.jetchart.demo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jetchart.demo.service.periodo.CPeriodoService;

public class CCerrarPeriodoJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(CCerrarPeriodoJob.class);
	
        public void execute(JobExecutionContext ctx) throws JobExecutionException {
        	try {
				CPeriodoService.insertaNuevoPeriodoVigente();
				logger.info("Periodo cerrado");
				CPeriodoService.enviarMailPeriodosPendientesAviso();
				logger.info("Envio de mails realizado");
			} catch (Exception e) {
				logger.error("Ocurrio un error al ejecutar el Job CCerrarPeriodoJob");
				e.printStackTrace();
			}
        	
        }
}
