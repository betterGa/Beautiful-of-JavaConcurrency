package SYNC.SemaphorePack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 在主线程中开启两个子线程让他们执行，等所有子线程执行完毕后，主线程再继续向下执行
 */
public class SemaphoreTest1 {
    // 创建一个 Semaphore 实例
    private static Semaphore semaphore= new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 将线程 A 添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println(Thread.currentThread() + " over");
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
                    System.out.println(Thread.currentThread() + " over");
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // 等待子线程执行完毕，返回
        semaphore.acquire(2);
        System.out.println("all child thread over!");

        // 关闭线程池
        executorService.shutdown();
    }
}
