package DataStractures.QueuePack;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {

    static class Task implements Comparable<Task>{
        private int priority = 0;
        private String taskName;

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        /**
         * 自定义元素优先级比较规则
         */
        @Override
        public int compareTo(Task o) {
            if(this.priority >= o.getPriority()){
                return 1;
            }else {
            return -1;
        }
    }

    public void doSomeThing(){
        System.out.println(taskName + ":" + priority);
        }
    }
    public static void main(String[] args) {
        // 创建任务，并添加到队列
        PriorityBlockingQueue<Task> priorityBlockingQueue=new PriorityBlockingQueue<>();
        Random random = new Random();
        for(int i=0;i<10;++i){
            Task task = new Task();
            // 使用随机数生成器生成 10 个随机的有优先级的任务
            task.setPriority(random.nextInt(10));
            task.setTaskName("taskName"+i);
            priorityBlockingQueue.offer(task);
        }
        // 取出任务执行
        while (!priorityBlockingQueue.isEmpty()){
            Task task=priorityBlockingQueue.poll();
            if(null != task){
                task.doSomeThing();
            }
        }
    }
}
