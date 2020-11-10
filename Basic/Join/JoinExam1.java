/*
 * 例1：    在主线程中启动两个子线程，然后分别调用它们的 join() 方法，
 * 那么主线程首先会在调用 threadOne.join 方法后阻塞，
 * 等待 threadOne 执行完毕后返回，然后主线程调用 threadTwo.join 方法后在此被阻塞，
 * 同样也是 等待 threadTwo 执行完毕后返回。
 *
 */
package Basic.Join;

public class JoinExam1 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne=new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(1000);}
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("child threadOne over!");
            }
        });

        Thread threadTwo=new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadTwo over!");
            }
        });

        //启动子线程
        threadOne.start();
        threadTwo.start();

        System.out.println("wait all child thread over!");

        //等待子线程执行完毕，返回
        threadOne.join();
        threadTwo.join();

        System.out.println("all child thread over!");
    }
}



/*
*     ❓为什么 threadOne 和 threadTwo 都需要先 sleep 1s呢❓
*     我的理解是：如果没有 sleep 主线程先运行 threadOne.join，
*     这时主线程阻塞，而 CPU 可能给 threadOne，也可能给 threadTwo，
*     要是给了 threadTwo，就会先执行完 threadTwo 的 run()，再执行 threadOne 的，
*     就看不到之后 threadTwo.join 的效果了。
*/