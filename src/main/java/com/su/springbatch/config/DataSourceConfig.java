package com.su.springbatch.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Named;
import javax.sql.DataSource;

/**
 * @author sukang
 */
@Configuration
public class DataSourceConfig {


    @ConfigurationProperties(prefix = "spring.datasource.batch")
    @Bean
    public DataSource batchDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Named("batchDataSource") DataSource batchDataSource){
        return new JdbcTemplate(batchDataSource);
    }

}