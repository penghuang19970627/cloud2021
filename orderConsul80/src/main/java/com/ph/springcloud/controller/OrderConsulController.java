package com.ph.springcloud.controller;

import com.ph.springcloud.entities.CommonResult;
import com.ph.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderConsulController {

    //public static final String PATMENT_URL = "http://localhost:8001";
    public static final String INVOKE_URL = "http://consul-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(INVOKE_URL+"/payment/consul/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/consul/get/{id}")
    public CommonResult<Payment> getPaymentConsulById(@PathVariable Long id){
        return  restTemplate.getForObject(INVOKE_URL+"/payment/consul/get/"+id,CommonResult.class);
    }
}
