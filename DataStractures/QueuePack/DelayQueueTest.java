package DataStractures.QueuePack;

import java.util.Random;
import java.util.concurrent.*;

/**
   创建一个延迟队列，使用随机数生成 10 个延迟任务，最后依次获取延迟任务，并打印
 */
public class DelayQueueTest {
    static class DelayedEle implements Delayed {

        // 延迟时间，表示 当前任务需要延迟多少 ms 时间 过期
        private final long delayTime;

        // 到期时间
        private final long expire;

        // 任务名称
        private String taskName;

        public DelayedEle(long delay,String taskName){
            delayTime = delay;
            this.taskName = taskName;
            expire = System.currentTimeMillis() + delay;
        }

        /**
         * 剩余时间 = 到期时间 - 当前时间
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        /**
         优先级队列里的优先级规则
         */
        @Override
        public int compareTo(Delayed o) {
            return (int)(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "DelayedEle{" +
                    "delayTime=" + delayTime +
                    ", expire=" + expire +
                    ", taskName='" + taskName + '\'' +
                    '}';
        }
        ScheduledThreadPoolExecutor
    }
    public static void main(String[] args) {
        // 创建 delay 队列
        DelayQueue<DelayedEle> delayQueue = new DelayQueue<>();

        // 创建延迟任务
        Random random = new Random();
        for(int i = 0;i < 10;++i){
            DelayedEle element = new DelayedEle(random.nextInt(500),"task:" + i);
            delayQueue.offer(element);
        }

        // 依次取出任务并打印
        DelayedEle ele = null;
        try{

            for( ; ;){
                // 获取过期任务并打印
                while ((ele = delayQueue.take()) != null){
                    System.out.println(ele.toString());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
