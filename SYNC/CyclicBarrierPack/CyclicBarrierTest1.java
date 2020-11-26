package SYNC.CyclicBarrierPack;

import java.util.concurrent.*;

/**
 使用两个线程去执行一个被分解的任务 A，
 当两个线程把自己的任务都执行完毕后，
 再对它们的结果汇总处理。
   */
public class CyclicBarrierTest1 {
    // 创建一个 CyclicBarrier 实例，添加一个 所有子线程 全部达到屏障后 执行的任务
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "task1 merge result");
        }
    });

    public static void main(String[] args) {
        // 创建一个线程个数固定为 2 的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 将线程 A 添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println(Thread.currentThread()+ "task1-1");
                    System.out.println(Thread.currentThread()+"enter in barrier");

                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread()+ "enter to barrier");
                } catch (Exception e) {
              e.printStackTrace();
                }
            }
        });
        // 将线程 B 添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println(Thread.currentThread()+ "task1-2");
                    System.out.println(Thread.currentThread()+"enter in barrier");

                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread()+ "enter to barrier");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 关闭线程池
        executorService.shutdown();
    }
}
