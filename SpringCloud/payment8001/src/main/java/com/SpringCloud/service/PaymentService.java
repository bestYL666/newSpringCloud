package com.SpringCloud.service;


import com.api.commons.entities.Payment;
import org.springframework.stereotype.Service;

/**
 * @Author: YL
 * @Date: 2023/3/26 16:59
 */

public interface PaymentService
{
    public int create(Payment payment);

    public Payment getPaymentById(Long id);

}
