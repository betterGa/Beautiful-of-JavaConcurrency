package Basic.SynchrVSValatile;
/**
 * 没有使用同步措施的
*/
public class ThreadNotSafeInteger {
    private int value;
    public int get()
    {return value;}

    public void set(int value){
        this.value=value;
    }
}
