/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app020.task;

import id.co.ahm.jxf.util.DateUtil;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import id.co.ahm.sd.nis.app020.service.Nis020Service;

/**
 * @author geonet
 */
public class Nis020SchedulerReminder {
    
    @Autowired
    private Nis020Service service;
    
    public String doScheduleSendMailReminder() {
        System.out.println("Start Job Reminder Email " + DateUtil.dateToString(new Date(), "dd-MMM-yyyy HH:mm:ss"));
        try {
            service.kirimEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End Job Reminder Email " + DateUtil.dateToString(new Date(), "dd-MMM-yyyy HH:mm:ss"));
        return "";
    }
    
    public String doGetCronJob(){
        return service.getCronJob();
    }
}
