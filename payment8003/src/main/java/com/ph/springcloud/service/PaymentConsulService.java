package com.ph.springcloud.service;

import com.ph.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentConsulService {
    public int create(Payment payment);

    public Payment getPaymentConsulById(@Param("id") Long id);
}
