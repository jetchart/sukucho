
package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import jobs.CCerrarPeriodoJob;

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

@WebListener
public class CQuartzListener extends QuartzInitializerListener {

    private static final Logger logger = LoggerFactory.getLogger(CQuartzListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);
        ServletContext ctx = sce.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) ctx.getAttribute(QUARTZ_FACTORY_KEY);
        try {
            Scheduler scheduler = factory.getScheduler();
            JobDetail job = JobBuilder.newJob(CCerrarPeriodoJob.class).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simple").withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")
            ).startNow().build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (Exception e) {
        	logger.error("Ocurri\u00f3 un error al calendarizar el trabajo", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        super.contextDestroyed(sce);
    }

}
