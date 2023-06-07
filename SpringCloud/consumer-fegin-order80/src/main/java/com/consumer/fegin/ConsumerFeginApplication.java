package com.consumer.fegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: YL
 * @Date: 2023/4/12 16:49
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class ConsumerFeginApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConsumerFeginApplication.class,args);
    }
}
