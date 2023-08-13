package com.consumer.fegin.controller;

import com.api.commons.entities.CommonResult;
import com.api.commons.entities.Payment;
import com.consumer.fegin.service.PaymentFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.context.XmlServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: YL
 * @Date: 2023/4/12 16:59
 */
@RestController
public class ConsumerFeginController {

    @Autowired
    private PaymentFeginService paymentFeginService;

    @PostMapping("/consumer/create")
    public CommonResult<String> getPaymentById(@RequestBody Payment payment){

        String paymentById = paymentFeginService.paymentInfo_OK(payment);
        return new CommonResult<>(200,paymentById,null);
    }


    @GetMapping("/consumer/TimeOut/{id}")
    public CommonResult<String> getPaymentTimeOut(@PathVariable("id") Long id){
        String paymentById = paymentFeginService.paymentInfo_TimeOut(id);
        if(paymentById.indexOf("超时")!=-1){
            return new CommonResult<>(444,paymentById,null);
        }
        return new CommonResult<>(200,paymentById,null);
    }

}
