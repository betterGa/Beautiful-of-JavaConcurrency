package AQSPack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

public class ProducerAndConsumer {
    final static NonReentrantLock lock=new NonReentrantLock();
    final static Condition notFull=lock.newCondition();
    final static Condition notEmpty=lock.newCondition();

    final static Queue<String> queue=new LinkedBlockingQueue<>();
    final static int queueSize=10;

    public static void main(String[] args) {
        // 生产者
        Thread producer=new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取独占锁
                lock.lock();
                try{
                    // 如果队列满了，则等待
                    while (queue.size() == queueSize){
                        notEmpty.await();
                    }

                    // 添加元素到队列
                    queue.add("ele");

                    // 唤醒消费线程
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        });

        // 消费者
        Thread consumer=new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取独占锁
                lock.lock();
                try{
                    // 队列空，则等待
                    while (0 == queue.size()){
                        notFull.await();

                        // 消费一个元素
                        String ele=queue.poll();
                        // 唤醒生产线程
                        notEmpty.signalAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        });

        // 启动线程
        producer.start();
        consumer.start();

    }
}
