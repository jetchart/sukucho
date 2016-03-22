package jobs;

import java.util.Locale;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CCerrarPeriodoJob implements Job {

        public void execute(JobExecutionContext ctx) throws JobExecutionException {
                System.out.printf(new Locale("es", "MX"), "%tc Ejecutando tarea...%n", new java.util.Date());
        }
}
