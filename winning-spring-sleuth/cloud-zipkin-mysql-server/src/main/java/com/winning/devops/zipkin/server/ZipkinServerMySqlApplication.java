package com.winning.devops.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import zipkin2.server.internal.EnableZipkinServer;
import zipkin2.storage.mysql.v1.MySQLStorage;

import javax.sql.DataSource;

/**
 * @author chensj
 * @title Zipkin Server Application
 * @project winning-spring-cloud
 * @package com.winning.devops.zipkin.server
 * @date: 2019-04-29 21:25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinServerMySqlApplication {

    public static void main(String[] args){
        SpringApplication.run(ZipkinServerMySqlApplication.class,args);
    }

    @Bean
    public MySQLStorage mySQLStorage(DataSource datasource) {
        return MySQLStorage.newBuilder().datasource(datasource).executor(Runnable::run).build();
    }
}
