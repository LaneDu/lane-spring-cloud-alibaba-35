package com.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import zipkin2.server.internal.EnableZipkinServer;

import javax.sql.DataSource;


/**
 * @author lane
 * @date 2021年06月29日 下午3:13
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApplication9411 {
    public static void main(String[] args) {

        SpringApplication.run(ZipkinServerApplication9411.class, args);
    }


    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){

        return new DataSourceTransactionManager(dataSource);
    }

}

