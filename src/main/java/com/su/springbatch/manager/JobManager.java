package com.su.springbatch.manager;



import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;

import org.springframework.batch.core.launch.JobLauncher;


import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import java.util.Map;


/**
 * @Author: sukang
 * @Date: 2020/9/15 15:51
 */

@Slf4j
@ManagedBean
public class JobManager {

    @Resource
    private Map<String, Job> jobs;

    @Resource
    private JobLauncher jobLauncher;


    public boolean isExit(String jobName){
        return jobs.containsKey(jobName);
    }

    public boolean isNotExit(String jobName){
        return !isExit(jobName);
    }

    public Job getJob(String jobName){
        return jobs.get(jobName);
    }


    public void runJob(String jobName, JobParameters jobParam){
        if (isExit(jobName)){
            try {
                jobLauncher.run(getJob(jobName), jobParam);
            } catch (Exception e) {
                log.error("job({}),执行异常", jobName, e);
            }
        }
    }



}
