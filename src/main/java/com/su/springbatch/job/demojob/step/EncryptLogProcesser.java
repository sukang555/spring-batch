package com.su.springbatch.job.demojob.step;

import com.su.springbatch.entity.EncryptLog;
import org.springframework.batch.item.ItemProcessor;

/**
 * @Author: sukang
 * @Date: 2020/9/15 15:36
 */
public class EncryptLogProcesser implements ItemProcessor<EncryptLog,EncryptLog> {


    @Override
    public EncryptLog process(EncryptLog encryptLog) throws Exception {
        return null;
    }
}
