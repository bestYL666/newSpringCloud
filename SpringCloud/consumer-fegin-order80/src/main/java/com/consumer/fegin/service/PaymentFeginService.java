package com.consumer.fegin.service;

import com.api.commons.entities.CommonResult;
import com.api.commons.entities.Payment;
import com.consumer.fegin.fallBack.FallBackAll;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: YL
 * @Date: 2023/4/12 16:53
 */

@Component
@FeignClient(name = "CLOUD-PAYMENT-SERVICE",fallback = FallBackAll.class)
public interface PaymentFeginService {

//    @GetMapping(value = "/payment/get/{id}")
//    public CommonResult getPaymentById(@PathVariable("id") Long id);


    @GetMapping("/payment/create")
    public String paymentInfo_OK(@RequestBody Payment payment);

    @GetMapping("/payment/get/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id);
}
