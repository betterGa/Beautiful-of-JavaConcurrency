package LockPack;

import java.util.concurrent.locks.LockSupport;

public class ParkTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park!");

                // 调用park方法，挂起自己
                LockSupport.park();

                System.out.println("child thread unpark!");
            }
        });

        // 启动子线程
        thread.start();

        // 主线程休眠 1s
        Thread.sleep(1000);

        System.out.println("main thread begin unpark!");

        // 调用 unpark 方法让 child 线程持有许可证，然后 park 方法返回
        LockSupport.unpark(thread);
    }
}
