package com.jetchart.demo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jetchart.demo.business.periodo.CPeriodoBusiness;
import com.jetchart.demo.model.CPeriodo;
import com.jetchart.demo.service.periodo.CPeriodoService;

public class CCrearPeriodoJob implements Job {
	
	private static final Logger logger = LoggerFactory.getLogger(CCrearPeriodoJob.class);
	
        public void execute(JobExecutionContext ctx) throws JobExecutionException {
                
			/* En caso que no exista proximo periodo lo crea y copia a los usuarios que 
			 * participaron del periodo anterior */
			CPeriodo periodo;
			try {
				periodo = new CPeriodoBusiness().armarProximoPeriodo();
				periodo = new CPeriodoBusiness().getPeriodoByMesAndAnio(periodo.getMes(), periodo.getAnio());
				if (periodo == null){
					CPeriodoService.insertarProximoPeriodo();
					logger.info("Se insertó el proximo periodo");
				}else{
					logger.info("Ya existe un proximo periodo creado");
				}
			} catch (Exception e) {
				logger.error("Ocurrió un error al crear el nuevo periodo", e);
				e.printStackTrace();
			}
        }
}
