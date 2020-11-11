package Basic.SynchrVSValatile;

/**
 * 使用 synchronized 关键字进行同步
 * */
public class ThreadSafeInteger1 {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value){
        this.value=value;
    }
}
