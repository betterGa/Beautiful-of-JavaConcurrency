package AQSPack;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 使用条件变量
 */
public class ConditionObjectTest {

    public static void main(String[] args) {

        ReentrantLock lock=new ReentrantLock();

        // new 一个在 AQS 内部声明的 ConditionObject 对象
        Condition condition=lock.newCondition();

        lock.lock();
        try{
            System.out.println("begin wait!");

            // 阻塞挂起当前线程，
            // 当其他线程调用条件变量的 signal 方法时
            // 被阻塞的线程才会从 await 处返回
            condition.await();
            System.out.println("end wait!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        lock.lock();
        try{
            System.out.println("begin signal!");
            condition.signal();
            System.out.println("end signal!");
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
