# spring-batch

          
JobRepository  管理job的元数据信息

JobLauncher    运行job
        
job 

    step1   步骤1
        ItemReader 数据读取
        ItemProcessor  数据处理
        ItemWriter    数据写入
    step2   步骤2
    
    listen  实现JobExecutionListener 监听job的执行,以及在job执行前后添加逻辑             
    
        
spring.batch.job.enabled 可以控制启动时是否执行job任务
