package com.ph.springcloud.service.impl;

import com.ph.springcloud.dao.PaymentConsulDao;
import com.ph.springcloud.entities.Payment;
import com.ph.springcloud.service.PaymentConsulService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentConsulServiceImpl implements PaymentConsulService {

    @Resource
    private PaymentConsulDao paymentConsulDao;

    public int create(Payment payment)
    {
        return paymentConsulDao.create(payment);
    }

    public Payment getPaymentConsulById(Long id)
    {
        return paymentConsulDao.getPaymentConsulById(id);
    }
}
