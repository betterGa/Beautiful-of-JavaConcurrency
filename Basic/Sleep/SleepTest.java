package Basic.Sleep;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepTest {
    //创建一个独占锁
    private static final Lock lock=new ReentrantLock();
    public static void main(String[] args) {

        //创建线程A
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                //获取独占锁
                lock.lock();
                try
                {
                    System.out.println("child threadA is in sleep.");

                    Thread.sleep(10000);

                    System.out.println("child threadA is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally {
                    //释放锁
                    lock.unlock();
                }
            }
        });

        //创建线程B
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                //获取独占锁
                lock.lock();
                try {
                    System.out.println("child threadB is in sleep");
                    Thread.sleep(10000);
                    System.out.println("child threadB is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
            }
        });

        //启动线程
        threadA.start();
        threadB.start();
    }
}

/*
* 以上代码，先创建了一个独占锁，然后创建了两个线程，
* 每个线程在内部先获取锁🔒，然后睡眠，睡眠结束后释放锁，
* 无论执行多少遍，都是 线程 A 先输出，还是线程 B 先输出，
* 不会出现线程 A 和 B 交叉输出的情况。
*/
