package com.wanghan.microservices.memberserver.config.hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 将自定义的熔断并发策略类交给springCloud
 * 隔离策略是thread才会这样，
 * 隔离策略若是信号量的话是在同一个线程中执行，不会有上下文切换问题
 * @Author WangHan
 * @Create 2019/12/18 12:45 下午
 */

@Configuration
public class ThreadLocalConfiguration {

    @Autowired(required = false)
    private HystrixConcurrencyStrategy existingConcurrencyStrategy;


    @PostConstruct
    public void init(){
        //因为要注册一个新的并发策略，所以需要获取所有其他的hystrix组件，然后重新设置hystrix插件

        //在HystrixCommand和HystrixObservableCommand执行期间发生的事件在HystrixEventNotifier上触发，从而提供了警报和统计信息收集的机会。
        HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();

        HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();

        //If you implement a custom HystrixPropertiesStrategy, this gives you full control over how properties are defined for the system.
        //The default implementation uses Archaius.
        HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();

        HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();

        //并发策略
//        HystrixConcurrencyStrategy concurrencyStrategy = HystrixPlugins.getInstance().getConcurrencyStrategy();
        HystrixPlugins.reset();

        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
        //注册自定义的并发策略，以及补上其他组件
        HystrixPlugins.getInstance().registerConcurrencyStrategy(existingConcurrencyStrategy);
        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);

    }

}
