package com.su.springbatch.config.job;

import com.su.springbatch.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;


/**
 * @Author: sukang
 * @Date: 2020/9/15 15:14
 */
@Slf4j
public class JobListener implements JobExecutionListener {


    @Override
    public void beforeJob(JobExecution jobExecution) {

        log.info("id{}任务开始执行{}",jobExecution.getId(), BeanUtil.toJsonStr(jobExecution.getJobInstance()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        log.info("id{}任务{}结束执行",jobExecution.getId(), BeanUtil.toJsonStr(jobExecution.getJobInstance()));

        if (jobExecution.getStatus() == BatchStatus.COMPLETED ) {
            //job success
            log.info("\n id{}任务success结束执行",jobExecution.getId());
        }
        else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            //job failure

            log.info("\n id{}任务failure结束执行",jobExecution.getId());
        }

    }
}
