package Basic.Interrupt;

/**根据中断标志判断线程是否终止*/
public class InterruptedTest1 {
    public static void main(String[] args) throws InterruptedException {
        // 子线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 如果当前线程被中断则退出循环
                while (!Thread.currentThread().isInterrupted()){
                // 没有被中断就输出hello
                    System.out.println(Thread.currentThread() + "hello");
                }
            }
        });

        // 启动子线程
        thread.start();

        // 主线程休眠 1s，以便中断让子线程输出
        Thread.sleep(1000);

        // 中断子线程
        System.out.println("main thread interrupt thread");
        // 这个间隙，CPU 给子线程了，所以中间还会输出 hello
        thread.interrupt();

        // 等待子线程执行完毕
        thread.join();
        System.out.println("main is over");
    }
}



