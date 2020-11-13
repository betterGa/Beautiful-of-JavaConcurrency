package ThreadLocalPack;

public class ThreadLocalTest1 {

    // 创建 THreadLocal 变量
    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    static void print(String str) {

        // 打印 当前线程 本地内存中 localVariable 变量的值
        System.out.println(str + ":" + localVariable.get());

        // 清除 当前线程 本地内存中 localVariable 变量
        //localVariable.remove();
        localVariable.set(null);
    }

    public static void main(String[] args) {
        //创建线程 One
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                // 设置 线程One 中本地变量 localVariable 的值
                localVariable.set("threadOne local variable");

                // 调用打印函数
                print("threadOne");

                //打印本地变量值
                System.out.println("threadOne remove after" + ":" + localVariable.get());
            }
        });

        // 创建线程 Two
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                // 设置线程Two 中本地变量 localVariable 的值
                localVariable.set("threadTwo local variable");

                // 调用打印函数
                print("threadTwo");

                // 打印本地变量值
                System.out.println("threadTwo remove after" + ":" + localVariable.get());
            }
        });

        // 启动线程
        threadOne.start();
        threadTwo.start();
    }
}

/*线程的 run 方法中 ，通过 set 方法 设置了 localVariable 的值，
这其实设置的是 线程 本地内存中 的一个副本，
这个副本是其他线程访问不了的。*/