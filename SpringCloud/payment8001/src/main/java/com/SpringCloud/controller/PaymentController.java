package com.SpringCloud.controller;


import com.SpringCloud.service.PaymentService;
import com.api.commons.entities.CommonResult;
import com.api.commons.entities.Payment;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * @Author: YL
 * @Date: 2023/3/26 17:03
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***插入结果：" + payment.toString());
        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort: " + serverPort);
        } else {
            return new CommonResult(444, "插入失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    @HystrixCommand(fallbackMethod = "fallBackFun", commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //是否开启断路器
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),   //请求次数
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  //时间范围
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //失败率达到多少后跳闸
            })
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询：" + payment.toString());
        if (null != payment) {
            return new CommonResult(200, "查询成功,serverPort: " + serverPort, payment);
        } else {
            throw new RuntimeException("没有对应的数据");
            //return new CommonResult(444, "没有对应记录，ID：" + id, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<ServiceInstance> instancesById = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instancesById) {
            log.info("******Service:  " + instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    //降级方法
    public CommonResult fallBackFun(Long id) {
        return new CommonResult(666, "查询超时，请稍后再试");
    }

}
