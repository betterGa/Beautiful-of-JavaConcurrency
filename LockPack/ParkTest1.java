package LockPack;

import java.util.concurrent.locks.LockSupport;

/**
 main 函数中调用 park 方法，当前线程会被挂起，
 因为默认情况下调用线程是不持有许可证的。
 */

public class ParkTest1 {
    public static void main(String[] args) {
        // 使当前线程获取许可证
        LockSupport.unpark(Thread.currentThread());
        System.out.println("begin park!");
        LockSupport.park();
        System.out.println("end park!");
    }
}
