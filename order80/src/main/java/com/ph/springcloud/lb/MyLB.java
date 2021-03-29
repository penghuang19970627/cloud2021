package com.ph.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component//需要跟主启动类同包，或者在其子孙包下。
public class MyLB implements LoadBalancer{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int corrent;
        int next;
        //自旋锁
        do{
            corrent = this.atomicInteger.get();
            next = corrent>=2147483647?0:corrent+1;
        }while (!this.atomicInteger.compareAndSet(corrent,next));
        System.err.println("********第"+next+"次访问");
        return next;
    }
    //负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启动后rest接口计数从1开始。
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}
