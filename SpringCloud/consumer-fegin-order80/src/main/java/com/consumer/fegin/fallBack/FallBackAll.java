package com.consumer.fegin.fallBack;

import com.api.commons.entities.Payment;
import com.consumer.fegin.service.PaymentFeginService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: YL
 * @Date: 2023/6/3 18:01
 */
@Component
public class FallBackAll implements PaymentFeginService {

    @Override
    public String paymentInfo_OK(Payment payment) {
        return "创建服务超时  QAQ";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        return "查询服务超时  QAQ";
    }


}
