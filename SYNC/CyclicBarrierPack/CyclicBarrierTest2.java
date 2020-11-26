package SYNC.CyclicBarrierPack;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 要求，一个任务由 阶段 1、阶段 2 和 阶段 3 组成，
 * 每个线程要串行地执行阶段 1、阶段 2 和 阶段 3，
 * 当多个线程执行该任务时，必须要保证所有线程的阶段 1 全部完成后 才能进入阶段 2，
 * 当所有线程的阶段 2 全部完成后，才能进入阶段 3 执行。*/
public class CyclicBarrierTest2 {

    // 创建一个 CyclicBarrier 实例
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 将线程 A 添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + "step1");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() + "step2");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() + "step3");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // 将线程 B 添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + "step1");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() + "step2");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() + "step3");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // 关闭线程池
        executorService.shutdown();
    }
}
