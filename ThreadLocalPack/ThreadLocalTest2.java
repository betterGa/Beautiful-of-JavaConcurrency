package ThreadLocalPack;

/**ThreadLocal 不支持继承性*/
public class ThreadLocalTest2 {

    //创建线程变量
    public static ThreadLocal<String> threadLocal=new InheritableThreadLocal<>();

    public static void main(String[] args) {
        //设置线程变量
        threadLocal.set("hello");

        //启动子线程
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //子线程输出线程变量的值
                System.out.println("thread:"+threadLocal.get());
            }
        });

        thread.start();

        //主线程输出线程变量的值
        System.out.println("main:"+threadLocal.get());
    }
}
/*可以看到，同一个 ThreadLocal 变量，
在父线程（主线程）中被设置值后，在 子线程 中 是获取不到的，
因为 在子线程 thread 中 调用 get() 方法时，当前线程是 thread ，
而这里 调用  set 方法设置 线程变量的是 main 线程，自然 子线程访问时 返回的就是 null 了*/