package Basic.SynchrVSValatile;

import sun.misc.Unsafe;

/**使用 volatile 进行同步*/
public class ThreadSafeInterger2 {
    private volatile int value;
    public int get(){
        return value;
    }

    public void set(int value){
        this.value=value;
    }
}
