package com.SpringCloud.Hystrix.service;

/**
 * @Author: YL
 * @Date: 2023/5/2 15:58
 */

public interface HystrixService {

    public String payment_OK(Long id);

    public String payment_TimeOut(Long id);


}
