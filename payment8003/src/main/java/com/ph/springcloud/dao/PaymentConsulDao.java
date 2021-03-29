package com.ph.springcloud.dao;

import com.ph.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaymentConsulDao {
    public int create(Payment payment);

    public Payment getPaymentConsulById(@Param("id") Long id);
}
