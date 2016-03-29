
package com.jetchart.demo.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jetchart.demo.jobs.CCerrarPeriodoJob;
import com.jetchart.demo.jobs.CCrearPeriodoJob;

@WebListener
public class CQuartzListener extends QuartzInitializerListener {
	
//	Armar el cronScheduler
//	http://www.cronmaker.com/
    private static final Logger logger = LoggerFactory.getLogger(CQuartzListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);
        ServletContext ctx = sce.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
        try {
            Scheduler scheduler = factory.getScheduler();
            
            /* JOB CCrearPeriodoJob */
            JobDetail jobCrearPeriodo = JobBuilder.newJob(CCrearPeriodoJob.class).build();
            /* Se ejecuta todos los lunes a las 3am.
             * Es de gusto que se ejecute 4 veces en un mes, pero por las dudas.. */
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("jobCrearPeriodo").withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0 3 ? * MON *")
            ).startNow().build();
            scheduler.scheduleJob(jobCrearPeriodo, trigger);
            scheduler.start();
            
            /* JOB CCrearPeriodoJob */
            JobDetail jobCerrarPeriodo = JobBuilder.newJob(CCerrarPeriodoJob.class).build();
            /* Se ejecuta el primer dia de cada mes a las 6am */
            Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("jobCerrarPeriodo").withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0 6 1 1/1 ? *")
            ).startNow().build();
            scheduler.scheduleJob(jobCerrarPeriodo, trigger2);
            scheduler.start();
            logger.error("Se scheduleó el job ");
        } catch (Exception e) {
        	logger.error("Ocurrió un error al calendarizar el trabajo", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        super.contextDestroyed(sce);
    }

}
