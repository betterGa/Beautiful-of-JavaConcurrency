package AQSPack;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 AQS 实现一个不可重入的独占锁，
 * 自定义 AQS 需要重写一系列函数，还需要定义原子变量 state 的含义。我们定义：
 *  state  为 0 表示锁没有被线程持有
 *  state  为 1 表示锁已经被某一个线程持有。
 * 由于是不可重入锁，所以不需要记录持有锁的线程获取锁的次数，
 * 而且，自定义的锁支持条件变量。
 */

public class NonReentrantLock implements Lock,Serializable {


    // 内部帮助类
    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        // 锁是否已经被持有
        protected boolean isHeldExclusively(){
            return getState()==1;
        }


        // 如果 state 为0，则尝试获取锁
        @Override
        public boolean tryAcquire(int acquires){

            assert  acquires == 1;

            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 尝试释放锁
        @Override
        protected boolean tryRelease(int releases) {

            assert releases == 1;
            if (getState() == 0)
                throw new IllegalMonitorStateException();

                setExclusiveOwnerThread(null);

                // 设置状态值 为 0
                setState(0);
                return true;
        }

        // 提供条件变量接口
        Condition newCondition(){
            return new ConditionObject();
        }
    }

    // 创建 Sync 对象
    private final Sync sync=new Sync();

    @Override
    public void lock() {
        // 尝试获取资源，也就是设置 state 的值
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(timeout));
    }

}
