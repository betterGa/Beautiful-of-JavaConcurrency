package Basic.Sleep;

/*
当一个线程处于睡眠状态时，如果另一个线程中断了它，会抛出异常。
*/
public class SleepTest2 {
    public static void main(String[] args) throws InterruptedException {

        //创建线程
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {System.out.println("child thread is in sleep");
                Thread.sleep(10000);
                System.out.println("child thread is awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        //启动线程
        thread.start();

        //主线程休眠2s
        Thread.sleep(2000);

        //主线程中断子线程
        thread.interrupt();
    }
}
