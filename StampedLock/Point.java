package StampedLock;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.StampedLock;

/**
 * 使用 StampedLock 管理二维点*/
public class Point {
    // 成员变量,表示一个点的二维坐标
    private double x,y;

    // 锁实例，保证操作的原子性
    private final StampedLock stampedLock=new StampedLock();

    /**
     * 使用参数的增量值，改变当前 point 坐标的位置
     * @param deltaX
     * @param deltaY
     */
    void move(double deltaX,double deltaY){
        // 独占锁——写锁
        long stamp=stampedLock.writeLock();
        try{
            x += deltaX;
            y += deltaY;
        }finally {

            // 释放锁
            stampedLock.unlockWrite(stamp);
        }
    }    /**


     * 计算当前位置到原点的距离
     * @return
     */
    // 乐观读锁
    double distanceFromOrigin(){

        // 尝试获取乐观读锁
        long stamp = stampedLock.tryOptimisticRead();

        // （1）将全部变量复制到方法体栈内
        double currentX = x,currentY = y;

        // （2）检查读取读锁戳记后，锁有没有被其他写线程排他性抢占
        if(!stampedLock.validate(stamp)){

            // 如果被抢占，则 获取一个共享读锁
            stamp = stampedLock.readLock();
            try{
                // 将全部变量复制到方法体栈中
                currentX = x;
                currentY = y;
            }finally {
                // 释放共享读锁
                stampedLock.unlock(stamp);
            }
        }
        // 返回计算结果
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    // 使用悲观锁获取读锁，并尝试转换为写锁
    void moveIfAtOrigin(double newX,double newY){
        long stamp = stampedLock.readLock();
        try{
            // 如果当前点在原点，则移动
            while(x == 0.0 && y == 0.0){
                // 尝试将获取的读锁升级为写锁
                long ws=stampedLock.tryConvertToWriteLock(stamp);

                // 升级成功，则更新戳记，并设置坐标值
                if(ws != 0L){
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                }else {
                    // 读锁升级写锁失败，则释放读锁，显式获取独占写锁，然后循环重试
                    stampedLock.unlockRead(stamp);
                    stamp=stampedLock.writeLock();
                }
            }
        }finally {
            // 释放锁
            stampedLock.unlock(stamp);
        }
    }
}
