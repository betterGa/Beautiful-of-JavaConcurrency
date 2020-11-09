package Basic.WaitAndNotify;

public class NotifyExam {
    //创建资源 resourceA
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {

        //创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取资源 resourceA 的监视器锁
                synchronized (resourceA)
                {
                    System.out.println("threadA get resourceA lock");

                    try
                    {
                        //resourceA 调用 wait 方法
                        System.out.println("threadA begin wait");
                        resourceA.wait();
                        System.out.println("threadA end wait");}
                    catch (InterruptedException e)
                    {e.printStackTrace();}
                }
            }
        });

        //创建线程
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA)
                {System.out.println("threadB get resourceA lock");
                    try
                    {System.out.println("threadB begin wait");
                        resourceA.wait();
                        System.out.println("threadB end wait");}
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //创建线程
        Thread threadC=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA)
                {System.out.println("threadC begin notify");
                    resourceA.notify();}
            }
        });

        //启动线程
        threadA.start();
        threadB.start();

        //让主线程先睡眠 1 s，
        // 这样才能让线程 A 和 B 都能调用到 wait 方法
        //然后线程 C 才能调用 notify 方法
        Thread.sleep(1000);
        threadC.start();

        //等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main over");

    }
}