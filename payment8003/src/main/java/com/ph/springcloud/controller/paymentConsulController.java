package com.ph.springcloud.controller;

import com.ph.springcloud.entities.CommonResult;
import com.ph.springcloud.entities.Payment;
import com.ph.springcloud.service.PaymentConsulService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.naming.ldap.PagedResultsControl;
import java.util.List;

@RestController
@Slf4j
public class paymentConsulController {

    @Resource
    private PaymentConsulService paymentConsulService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/consul/create")
    public CommonResult<Payment> create(@RequestBody Payment payment)
    {
        int result = paymentConsulService.create(payment);
        log.info("*****插入结果："+result);

        if(result > 0)
        {
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/consul/get/{id}")
    public CommonResult<Payment> getPaymentConsulById(@PathVariable("id") Long id)
    {
        Payment payment = paymentConsulService.getPaymentConsulById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: "+id,null);
        }
    }

    @GetMapping(value = "/payment/consul/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String service: services) {
            log.info("*******service:"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CONSUL-PROVIDER-PAYMENT");
        for (ServiceInstance instance: instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }
}
