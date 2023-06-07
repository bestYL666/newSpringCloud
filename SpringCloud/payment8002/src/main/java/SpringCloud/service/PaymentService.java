package SpringCloud.service;


import com.api.commons.entities.Payment;

/**
 * @Author: YL
 * @Date: 2023/3/26 16:59
 */

public interface PaymentService
{
    public int create(Payment payment);

    public Payment getPaymentById(Long id);

}
