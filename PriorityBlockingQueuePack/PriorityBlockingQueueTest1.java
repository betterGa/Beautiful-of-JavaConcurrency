package PriorityBlockingQueuePack;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest1 {

    public static void main(String[] args) {
        PriorityBlockingQueue queue=new PriorityBlockingQueue();
        queue.offer(2);
        queue.offer(4);
        queue.offer(6);
        queue.offer(1);

        queue.poll();
        queue.poll();
        queue.poll();
        queue.poll();
    }
}
