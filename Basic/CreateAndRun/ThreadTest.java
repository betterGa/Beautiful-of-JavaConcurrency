package Basic.CreateAndRun;

public class ThreadTest {
    public static class MyThread extends Thread
    {
        @Override
        public void run()
        {
            System.out.println("I am a child thread.");
        }
    }

    public static void main(String[] args) {

        //创建线程
        MyThread thread=new MyThread();

        //启动线程
        thread.start();
    }
}

/*
*   当创建完 thread 对象后 该线程并没有被启动执行，
*   直到调用了 start 方法后才真正启动了线程，
*   调用 start 方法后，线程没有马上执行，而是进入了就绪状态，
*   就绪状态是指 该线程已经获取了除了 CPU 资源以外的其他资源，
*   等待获取 CPU 资源后 才会真正处于运行状态。*/