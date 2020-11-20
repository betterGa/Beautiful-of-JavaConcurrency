package ReentrantLockPack;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用 ReentrantLock 实现一个简单的线程安全的 list
 * */
public class ReentrantLockList {
    private ArrayList<String> arrayLIst=new ArrayList<>();

    // 独占锁
    private volatile ReentrantLock lock=new ReentrantLock();

    // 添加元素
    public void add(String e){
        lock.lock();
        try{
            arrayLIst.add(e);
        }finally {
            lock.unlock();
        }
    }

    // 删除元素
    public void remove(String e){
        lock.lock();
        try{
            arrayLIst.remove(e);
        }finally {
            lock.unlock();
        }
    }

    // 获取元素
    public String get(int index){
        lock.lock();
        try{return arrayLIst.get(index) ;}
        finally {
            lock.unlock();
        }
    }
    /*通过在操作 array 元素前进行加锁保证同一时间只有一个线程可以对 array 数组进行修改，
    但是也只能有一个线程对 array 元素进行访问。*/
}
