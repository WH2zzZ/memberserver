package com.wanghan.microservices.memberserver.context;

/**
 * 线程的上下文参数
 *
 * @Author WangHan
 * @Create 2019/12/13 12:49 下午
 */
public class ContextHolder {

    private static final ThreadLocal<Context> holder = new ThreadLocal<>();

    public static Context getContext(){
        Context context = holder.get();
        if (context == null) {
            context = new Context();
            holder.set(context);
        }
        return context;
    }

    public static void setContext(Context context) {
        holder.set(context);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        holder.remove();
    }
}
