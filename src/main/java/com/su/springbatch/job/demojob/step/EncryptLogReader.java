package com.su.springbatch.job.demojob.step;


import com.su.springbatch.entity.EncryptLog;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @Author: sukang
 * @Date: 2020/9/15 15:35
 */
public class EncryptLogReader implements ItemReader<EncryptLog> {


    @Override
    public EncryptLog read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
