package com.wanghan.microservices.memberserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wanghan.microservices.memberserver.web.request.UserSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员业务层
 * @Author WangHan
 * @Create 2019/12/6 12:53 下午
 */
@Service
/**
 * 类级别的hystrix配置
 */
@DefaultProperties(
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
        }
)
public class UserService {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 不加HystrixCommand，则不会断路，而是一直等待，加了断路器注解，则会抛出timeout异常
     *
     * @Author WangHan
     * @Create 12:44 下午 2019/12/10
     * @Param [request]
     * @Return java.lang.String
     */
    @HystrixCommand
    public String addUser(UserSaveRequest request) throws JsonProcessingException {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return objectMapper.writeValueAsString(request);
    }

    /**
     * HystrixCommand注解也可以包含附加属性，来自定义hystrix
     *
     *
     * @Author WangHan
     * @Create 12:48 下午 2019/12/10
     * @Param [request]
     * @Return java.lang.String
     */
    @HystrixCommand(
        commandProperties = {
            //自定义超时时间为30s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
            //控制hystrix考虑将断路器跳闸之前，在指定 "默认值为10s的监视服务调用问题的时间大小所配置的时间（metrics.rollingStats.timeInMilliseconds）" 之内必须发生的连续调用数量
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //超过上面的值时，在断路器跳闸之前必须达到的调用失败
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "0"),
            //断路器跳闸之后，允许另一个调用通过，以便查询被调用的服务是否恢复健康之前，hystrix所休眠的时间
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
            //控制hystrix监视服务调用问题的时间大小，默认为10s
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "5000"),
            //"hystrix监视服务调用问题"统计调用数据时，会将每一次调用放在一个一个桶之中，这里定义的就是这个桶的数量，桶的数量一定要可以被上面的配置所整除
            //比如这里，相当于将统计数据放在了长度为3s的5个桶中
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5"),
        },
        //后备模式
//        fallbackMethod = "loginFallback",
        //设置舱壁模式
        //定义线程池的唯一名称
        threadPoolKey = "loginThreadPool",
        threadPoolProperties = {
                //定义线程池中线程最大数量
                @HystrixProperty(name = "coreSize", value = "10"),
                //线程池中线程繁忙时允许堵塞的请求数，如果为-1则不会进入阻塞队列，而是直接阻塞请求
                @HystrixProperty(name = "maxQueueSize", value = "5")
        }
    )
    public String login(UserSaveRequest request) throws JsonProcessingException {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return objectMapper.writeValueAsString(request);
    }

    /**
     * 后备模式执行的方法
     * @Author WangHan
     * @Create 1:00 下午 2019/12/10
     * @Param
     * @Return
     */
    private String loginFallback(UserSaveRequest request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }
}
