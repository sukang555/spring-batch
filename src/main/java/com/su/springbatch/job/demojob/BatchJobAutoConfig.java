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
    public Job demoJob(@Named("jobExecutionListener") JobExecutionListener jobExecutionListener,
                       @Named("dealStep") Step dealStep){

        return jobBuilders.get("demoJob")
                .start(dealStep)
                .listener(jobExecutionListener)
                .build();
    }


    /**
     * 1、Skip:如果处理过程中某条记录是错误的,如CSV文件中格式不正确的行,那么可以直接跳过该对象,继续处理下一个。
     *      * 2、在chunk元素上定义skip-limit属性,告诉Spring最多允许跳过多少个items,超过则job失败
     *      * 3、Restart:如果将job状态存储在数据库中,而一旦它执行失败,	那么就可以选择重启job实例,	并继续上次的执行位置。
     *      * 4、最后,对于执行失败的job作业,我们可以重新启动,并让他们从上次断开的地方继续执行。要达到这一点,只需要使用和上次 一模一样的参数来启动job,
     *      * 则Spring	Batch会自动从数据库中找到这个实例然后继续执行。你也可以拒绝重启,或者参数控 制某个
     *      * job中的一个tep可以重启的次数(一般来说多次重试都失败了,那我们可能需要放弃。)
     */
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
