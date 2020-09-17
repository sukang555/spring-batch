package com.su.springbatch.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @Author: sukang
 * @Date: 2020/9/17 14:19
 */
@Setter
@Getter
public class JobParam<T> {

    private String jobName;

    private T object;

}
