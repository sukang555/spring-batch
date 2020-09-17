package com.su.springbatch.job.demojob.step;

import com.su.springbatch.entity.EncryptLog;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @Author: sukang
 * @Date: 2020/9/15 15:36
 */
public class EncryptLogWriter implements ItemWriter<EncryptLog> {


    @Override
    public void write(List<? extends EncryptLog> list) throws Exception {

    }


}
