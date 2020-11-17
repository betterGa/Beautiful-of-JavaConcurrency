package LockPack;

import java.util.concurrent.locks.LockSupport;

public class ParkTest3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park!");

                // 调用 park 方法，挂起自己，只有被中断时才会退出循环
                while (!Thread.currentThread().isInterrupted()){
                    LockSupport.park();
                }

                System.out.println("child thread unpark!");
            }
        });

        // 启动子线程
        thread.start();

        // 主线程休眠 1s
        Thread.sleep(1000);

        System.out.println("main thread begin unpark!");

        // 中断子线程
        thread.interrupt();
    }
}
