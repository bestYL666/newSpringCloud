package com.SpringCloud.Hystrix.service.imp;

import com.SpringCloud.Hystrix.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: YL
 * @Date: 2023/5/2 15:59
 */
@Service
public class HystrixServiceImpl implements HystrixService {
    @Override
    public String payment_OK(Long id) {

        return "线程池"+Thread.currentThread().getName()+"     payment_OK,id: "+id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler",commandProperties =
            //execution.isolation.thread.timeoutInMilliseconds设置等待时间的峰值  默认是1000毫秒
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="3000")})
    public String payment_TimeOut(Long id) {
        int timeOut = 2;

        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  "线程池"+Thread.currentThread().getName()+"     payment_TimeOut,id: "+id+"    Time OUt,耗时（秒）："+timeOut;
    }

    public String payment_TimeOutHandler(Long id)
    {
        return "已超时";
    }

}
