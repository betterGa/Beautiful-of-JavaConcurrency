package Basic.Interrupt;

/**
* 当线程为了等待一些特定条件的到来时，
* 一般会调用 sleep、wait 系列函数 或者 join 函数来阻塞当前线程。
* 比如一个线程调用了 Thread.sleep(3000)，那么线程就会阻塞，
* 直到 3s 后才会从阻塞状态变为激活状态。但是有可能在 3s 内条件已被满足，
* 如果一直等到 3s 后再返回，有点浪费时间，这时可以调用该线程的 interrupt 方法，
* 强制 sleep 抛出 InterruptedException 异常而返回，线程恢复到激活状态：
*/

public class InterruptedTest2 {
    public static void main(String[] args) throws InterruptedException {

        Thread threadOne=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("threadOne begin sleep for 2000 seconds.");
                    Thread.sleep(2000000);
                    System.out.println("threadOne awaking");
                }
                catch (InterruptedException e) {
                   System.out.println("threadOne is interrupted while sleeping.");
                   //return 退出整个run方法
                   return;
                }
                System.out.println("threadOne-leaving normally");
            }
        });

        // 启动线程
        threadOne.start();

        // 主线程休眠，CPU 给到子线程，子线程休眠 2000 s
        Thread.sleep(1000);

        // 打断子线程的休眠，让子线程从 sleep 方法返回
        threadOne.interrupt();

        // 等待子线程执行完毕
        threadOne.join();
        System.out.println("main thread is over.");
    }
}
/*
* 本来 threadOne 线程是要休眠 2000s 后才会被唤醒的，
* 本例就是通过调用 threadOne.interrupt() 方法打断了该线程的休眠，
* 该线程在调用 sleep 方法处 抛出 InterruptedException 异常后返回。
*/