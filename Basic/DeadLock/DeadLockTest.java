package Basic.DeadLock;

public class DeadLockTest {
    // 创建资源
    private static Object resourceA=new Object();
    private static Object resourceB=new Object();

    public static void main(String[] args) {
        // 创建线程 A
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread()+"get ResourceA");
                    try {
                        // 这里睡眠是为了让另一个线程获取资源
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread()+"Waiting get sourceB");

                    synchronized (resourceB) {
                    System.out.println(Thread.currentThread()+"get resourceB");
                    }
                }
                }
        });

        // 创建线程 B
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {

                /*死锁示例
                synchronized (resourceB) {
                   System.out.println(Thread.currentThread()+"get ResourceB");
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

                   System.out.println(Thread.currentThread()+"waiting get resourceA");
                   synchronized (resourceA) {
                   System.out.println(Thread.currentThread()+"get ResourceA");
                   }
                }
            }
        });
        */
                /*使用资源申请的有序性，避免死锁*/
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + "get ResourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread()+"waiting get ResourceA");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread()+"get ResourceB");
                    }
                }
            }
        });

        //启动线程
        threadA.start();
        threadB.start();
    }
}
