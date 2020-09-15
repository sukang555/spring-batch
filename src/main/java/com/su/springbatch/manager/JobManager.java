package com.su.springbatch.manager;

import com.su.springbatch.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import java.util.Collection;


/**
 * @Author: sukang
 * @Date: 2020/9/15 15:51
 */
@ManagedBean
@Slf4j
public class JobManager implements CommandLineRunner {

    @Resource
    private JobLauncher jobLauncher;

    @Resource
    private JobRegistry jobRegistry;


    @Override
    public void run(String... args) throws Exception {

        Collection<String> jobNames = jobRegistry.getJobNames();

        for (String jobName : jobNames) {
            log.info("job-{}" ,jobName);
        }

    }
}
