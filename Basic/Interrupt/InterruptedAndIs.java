package Basic.Interrupt;

/**
 interrupted() 和 isInterrupted() 方法的不同之处：
 */

public class InterruptedAndIs {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne=new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;)
                {}
            }
        });

        // 启动线程
        threadOne.start();

        // 设置中断标志
        threadOne.interrupt();

        // 获取中断标志
        System.out.println("isInterrupted:"+threadOne.isInterrupted());

        // 获取中断标志并重置
        // interrupted 是 static 方法，发现当前线程，也就是主线程的中断标志位
        // 所以会输出 false
        System.out.println("isInterrupted:"+threadOne.interrupted());

        // 获取中断标志
        System.out.println("isInterrupted:"+Thread.interrupted());

        // 获取中断标志
        System.out.println("isInterrupted:"+threadOne.isInterrupted());

        threadOne.join();
        System.out.println("main thread is over");
    }
}
