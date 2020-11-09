package Basic.CreateAndRun;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallerTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "hello";
    }

    public static void main(String[] args) throws InterruptedException {

        //创建异步任务
        FutureTask<String> futureTask=new FutureTask<>(new CallerTask());

        //启动线程
        new Thread(futureTask).start();

        try
        {
            //等待任务执行完毕，并返回结果
            String result=futureTask.get();
            System.out.println(result);
        }
        catch (ExecutionException e)
        {e.printStackTrace();}
    }
}

/*
*   CallTask 类实现了 Callable 接口的 call() 方法，
*   在 main 函数中首先创建了一个 FutureTask 对象，构造函数为 CallerTask 的实例，
*   然后使用创建的 FutureTask 对象作为任务创建了一个线程并启动它，
*   最后通过 futureTask.get() 等待任务执行完毕并返回结果。*/
