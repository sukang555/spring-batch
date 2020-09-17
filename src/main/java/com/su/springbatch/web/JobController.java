package com.su.springbatch.web;

import com.su.springbatch.entity.DemoJobDTO;
import com.su.springbatch.entity.JobParam;

import com.su.springbatch.manager.JobManager;
import com.su.springbatch.utils.BeanUtil;
import com.su.springbatch.utils.JobUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: sukang
 * @Date: 2020/9/17 14:18
 */
@RestController
@Slf4j
public class JobController {

    @Resource
    private JobManager jobManager;



    @PostMapping(value = "exec-job")
    public String execJob(@RequestBody JobParam<DemoJobDTO> jobParam){

        log.info(BeanUtil.toJsonStr(jobParam));

        try {

            if (jobManager.isNotExit(jobParam.getJobName())){
                return "{\"code\":\"1000\",\"msg\":\"job不存在\"}";
            }

            jobManager.runJob(jobParam.getJobName(), JobUtils.buildJobParameter(jobParam));

            return "{\"code\":\"0000\",\"msg\":\"job运行成功\"}";
        } catch (Exception e) {
            log.error("任务执行异常",e);
        }

        return "{\"code\":\"10000\",\"msg\":\"job运行异常\"}";
    }





















}
