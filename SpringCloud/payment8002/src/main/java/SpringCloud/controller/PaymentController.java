package SpringCloud.controller;


import SpringCloud.service.PaymentService;

import com.api.commoms.exceptions.BussException;
import com.api.commons.entities.CommonResult;
import com.api.commons.entities.Payment;
import com.api.commons.entities.QueueEntites;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import  com.SpringCloud.Product.ProductQueue;


/**
 * @Author: YL
 * @Date: 2023/3/26 17:03
 */
@RestController
@Slf4j
public class PaymentController
{
    @Autowired
    private PaymentService paymentService;



    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
      int result = paymentService.create(payment);
      log.info("***插入结果："+result);
      if (result>0){
          return new CommonResult(200,"插入成功,serverPort: "+serverPort);
      }else {
          return new CommonResult(444,"插入失败",null);
      }
    }

    @GetMapping(value = "/payment/get/{id}")
//    @HystrixCommand(fallbackMethod = "fallBackFun",commandProperties =
//            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="3000")})
    public CommonResult getPaymentById(@PathVariable("id") Long id) throws Exception {

            Payment payment = paymentService.getPaymentById(id);
            log.info("***查询：" + payment.toString());
            if (null != payment) {
                ProductQueue productQueue = new ProductQueue();
                QueueEntites queueEntites = new QueueEntites();
                productQueue.outFun(queueEntites.getPRO_QUE(), queueEntites.getHost(), queueEntites.getUserName(), queueEntites.getPwd(), payment);
                return new CommonResult(200, "查询成功,serverPort: " + serverPort);
            } else {
                return new CommonResult(444, "没有对应记录，ID：" + id);
            }
    }


    //降级方法
    public CommonResult fallBackFun(Long id)
    {
        return new CommonResult(666,"查询超时，请稍后再试");
    }
}
