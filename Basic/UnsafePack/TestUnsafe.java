package Basic.UnsafePack;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnsafe {
    // 获取 Unsafe 的实例
    /*
    *这样写会抛出异常
    static final Unsafe unsafe=Unsafe.getUnsafe();
    */

    static final Unsafe unsafe;
    // 记录变量 state 在类 TestUnsafe 中的偏移量
    static final long stateOffset;

    // 变量
    private volatile long state=0;

    static {
        try{

            // 通过反射获取 Unsafe 的成员变量 theUnsafe
            Field field=Unsafe.class.getDeclaredField("theUnsafe");

            // 设置为可存取
            field.setAccessible(true);

            // 获取该变量的值
            unsafe= (Unsafe) field.get(null);
            // 获取 state 变量在类 TestUnsafe 中的偏移量
            stateOffset=unsafe.objectFieldOffset(TestUnsafe.class.
                    getDeclaredField("state"));
        }
        catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            throw new Error(ex);
        }
    }
    public static void main(String[] args) {
        // 创建实例，并设置 state 的值为 1
        TestUnsafe testUnsafe=new TestUnsafe();
        Boolean sucess=unsafe.compareAndSwapInt(testUnsafe,stateOffset,0,1);
        System.out.println(sucess);
    }
}
