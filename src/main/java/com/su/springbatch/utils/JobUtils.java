package com.su.springbatch.utils;

import com.su.springbatch.entity.DemoJobDTO;
import com.su.springbatch.entity.JobParam;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;


import java.util.Map;

/**
 * @Author: sukang
 * @Date: 2020/9/17 14:39
 */
public class JobUtils {


    public static JobParameters buildJobParameter(JobParam<DemoJobDTO> jobParam) throws Exception {

        JobParametersBuilder parametersBuilder = new JobParametersBuilder();

        Map<String, Object> bean2map = BeanUtil.bean2map(jobParam.getObject());

        for (Map.Entry<String, Object> entry : bean2map.entrySet()) {
            parametersBuilder.addParameter(entry.getKey(),new JobParameter(String.valueOf(entry.getValue())));
        }

        return parametersBuilder.toJobParameters();
    }
}
