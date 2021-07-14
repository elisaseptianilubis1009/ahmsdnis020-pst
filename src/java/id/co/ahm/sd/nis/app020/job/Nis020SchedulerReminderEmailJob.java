/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app020.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.scheduling.quartz.QuartzJobBean;

import id.co.ahm.sd.nis.app020.task.Nis020SchedulerReminder;

/**
 *
 * @author geonet
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class Nis020SchedulerReminderEmailJob extends QuartzJobBean implements Job {
    
    private Nis020SchedulerReminder job;

    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        Trigger trigger = jec.getTrigger();
        CronTriggerImpl crontrigger = (CronTriggerImpl) trigger;
        try {
            Scheduler scheduler = jec.getScheduler();
            String newCron = job.doGetCronJob();
            if (!crontrigger.getCronExpression().equals(newCron)) {
                crontrigger.setCronExpression(newCron);
                scheduler.rescheduleJob(trigger.getKey(), crontrigger);
            }
            job.doScheduleSendMailReminder();
        } catch (Exception e) {
            e.printStackTrace();
        }//To change body of generated methods, choose Tools | Templates.
    }

	public Nis020SchedulerReminder getJob() {
		return job;
	}

	public void setJob(Nis020SchedulerReminder job) {
		this.job = job;
	}

  
}
