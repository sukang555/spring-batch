package com.su.springbatch.job.demojob;


import com.su.springbatch.job.JobListener;
import com.su.springbatch.job.demojob.step.EncryptLogProcesser;
import com.su.springbatch.job.demojob.step.EncryptLogReader;
import com.su.springbatch.job.demojob.step.EncryptLogWriter;
import com.su.springbatch.entity.EncryptLog;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.annotation.Resource;
import javax.inject.Named;

/**
 * @Author: sukang
 * @Date: 2020/9/15 15:00
 */
@Configuration
@EnableBatchProcessing
public class BatchJobAutoConfig {

    @Resource
    private JobBuilderFactory jobBuilders;


    @Resource
    private StepBuilderFactory stepBuilders;





    @Bean
    public Job dealJob(@Named("jobExecutionListener") JobExecutionListener jobExecutionListener,
                       @Named("dealStep") Step dealStep){


        return jobBuilders.get("demoJob")
                .start(dealStep)
                .listener(jobExecutionListener)
                .build();
    }



    @Bean
    public Step dealStep(@Named("itemReader") ItemReader<EncryptLog> itemReader,
                         @Named("itemProcessor") ItemProcessor<EncryptLog, EncryptLog> itemProcessor,
                         @Named("itemWriter") ItemWriter<EncryptLog> itemWriter){
        return stepBuilders.get("step1")
                .<EncryptLog, EncryptLog> chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

    }


    @Bean
    public ItemReader<EncryptLog> itemReader(){
        return new EncryptLogReader();
    }

    @Bean
    public ItemProcessor<EncryptLog,EncryptLog> itemProcessor(){
        return new EncryptLogProcesser();
    }

    @Bean
    public ItemWriter<EncryptLog> itemWriter(){
        return new EncryptLogWriter();
    }




    @Bean
    public JobExecutionListener jobExecutionListener(){
        return new JobListener();
    }



}
