package com.SpringCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: YL
 * @Date: 2023/6/6 13:33
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class Gataway {
    public static void main(String[] args) {
        SpringApplication.run(Gataway.class,args);
    }
}
