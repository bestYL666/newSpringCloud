package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author: YL
 * @Date: 2023/6/5 18:23
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashbord9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashbord9001.class,args);
    }
}
