package SYNC.SemaphorePack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest2 {
    // 创建一个 Semapore 实例
    private static volatile Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 将线程 A 添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println(Thread.currentThread() + "A task over");
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        // 将线程 B 添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println(Thread.currentThread() + "A task over");
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // 等待子线程执行任务 A 完毕
        semaphore.acquire(2);

        // 将线程 C 添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println(Thread.currentThread() + "B task over");
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // 将线程 D 添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println(Thread.currentThread() + "B task over");
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // 等待子线程执行 B 完毕，返回
        semaphore.acquire(2);

        System.out.println("task is over");

        // 关闭线程池
        executorService.shutdown();
    }
}
