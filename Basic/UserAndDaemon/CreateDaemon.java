package Basic.UserAndDaemon;
/**
 * 创建守护线程
 * */
public class CreateDaemon {
    public static void main(String[] args) {
        Thread daemonThread=new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){
                }
            }
        });

        // 设置为守护线程
      daemonThread.setDaemon(true);
        daemonThread.start();
        System.out.println("main thread is over");
    }
}
