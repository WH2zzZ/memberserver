package com.wanghan.microservices.memberserver.config.hystrix;

import com.wanghan.microservices.memberserver.context.Context;
import com.wanghan.microservices.memberserver.context.ContextHolder;

import java.util.concurrent.Callable;

/**
 * hystrix通过这种方式将被hystrix注解的方法添加到自己的线程中去执行
 * 由并发策略来帮助协调线程
 * @Author WangHan
 * @Create 2019/12/17 1:02 下午
 */
public class DelegatingUserContextCallable<V> implements Callable<V>{

    private final Callable<V> delegate;

    private Context context;

    /**
     * 原始的callable类将被传递到自定义的callable，自定义的callable将调用hystrix保护的代码和来自父线程的UserContext
     * @param delegate
     * @param context
     */
    public DelegatingUserContextCallable(Callable<V> delegate, Context context) {
        this.delegate = delegate;
        this.context = context;
    }

    /**
     * 此方法在被@HystrixCommand注解保护的方法之前调用
     * @return
     * @throws Exception
     */
    @Override
    public V call() throws Exception {
        //将线程变量传递进来context
        ContextHolder.setContext(context);

        try {
            //调用方法
            return delegate.call();
        }finally {
            this.context = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate, Context context){
        return new DelegatingUserContextCallable<V>(delegate, context);
    }
}
