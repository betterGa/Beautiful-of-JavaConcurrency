package ReentrantReadWriteLockPack;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentranLockList {
    private ArrayList<String> arrayLIst=new ArrayList<>();

    // 独占锁
    private final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    private final Lock readLock=lock.readLock();
    private final Lock writeLock=lock.writeLock();


    // 添加元素
    public void add(String e){
        writeLock.lock();
        try{
            arrayLIst.add(e);
        }finally {
            writeLock.unlock();
        }
    }

    // 删除元素
    public void remove(String e){
        writeLock.lock();
        try{
            arrayLIst.remove(e);
        }finally {
            writeLock.unlock();
        }
    }

    // 获取元素
    public String get(int index){
        readLock.lock();
        try{return arrayLIst.get(index) ;}
        finally {
            readLock.unlock();
        }
    }
}
