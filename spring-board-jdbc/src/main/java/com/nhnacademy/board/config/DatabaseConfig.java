package com.nhnacademy.board.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {
    private final DatabaseProperties databaseProperties;

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(databaseProperties.getDriverClassName());
        dataSource.setUrl(databaseProperties.getUrl());
        dataSource.setUsername(databaseProperties.getUsername());
        dataSource.setPassword(databaseProperties.getPassword());
        dataSource.setInitialSize(databaseProperties.getInitialSize());
        dataSource.setMaxIdle(databaseProperties.getMaxIdle());
        dataSource.setMaxTotal(databaseProperties.getMaxTotal());
        dataSource.setMinIdle(databaseProperties.getMinIdle());
        dataSource.setTestOnBorrow(databaseProperties.isTestOnBorrow());
        dataSource.setValidationQuery(databaseProperties.getValidationQuery());
        return dataSource;
    }
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

}
