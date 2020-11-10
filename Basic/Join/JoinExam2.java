/*
 * 例2：
 *  还有一种情况，线程 A 调用 线程 B 的 join 方法后会被阻塞，当其他线程
 *  （我想，例子中的 threadTwo 休眠了 1 s 的原因，可能就是为了让 threadOne 被分到 CPU ，
 *  一定会被执行，这样的话，threadTwo 就是 “其他线程”）
 *  调用了 线程 A 的 interrupt() 方法中断了线程 A 时，
 *  线程 A 会抛出 InterruptedException 异常而返回。
 */
package Basic.Join;

public class JoinExam2 {

    public static void main(String[] args) throws InterruptedException {

        //线程 one
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {

                    System.out.println("threadOne begin run");

                for(;;)
                {}
            }});

        //获取主线程
        final Thread mainThread=Thread.currentThread();

        //线程two
        Thread threadtwo=new Thread(new Runnable() {
            @Override
            public void run() {
                //休眠1s
               // System.out.println("threadTwo begin run");
                    try{
                    Thread.sleep(1000);}

                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //中断主线程
                mainThread.interrupt();
            }});

        //启动子线程
        threadOne.start();

        //延迟1s启动线程
        threadtwo.start();

        try {
            //等待线程one执行结束
            threadOne.join();
        }
        catch (InterruptedException e)
        {System.out.println("main thread：" + e);}
    }
}


/*
*     以上代码 在 threadOne 线程里执行死循环，
*     主线程调用 threadOne 的 join 方法，阻塞自己，
*     等待线程 threadOne 执行完毕，从运行结果可以看到，主线程被阻塞后，
*     CPU 是先给到 threadOne了，输出 “threadOne begin run” ，
*     然后执行死循环，而 死循环不会永久霸占 CPU ，
*     所以接下来时间片给到了 threadTwo ，休眠 1 s 后，
*     （这 1 s 内会执行 threadOne 的死循环）
*     等 CPU 给到 threadTwo 时，就会 调用主线程的 interrupt() 方法设置主线的中断标志，
*     从结果看 ，在 主线程中的 threadOne.join() 处会抛出 InterruptedException 异常。
    这个例子就不是因为 threadOne 的 run 方法执行完毕（死循环是不可能执行完毕的🤓 ），
    而使得主线程得以继续向下进行，是因为抛出了中断异常… …
*/