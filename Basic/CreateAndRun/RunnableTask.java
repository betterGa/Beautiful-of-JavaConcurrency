package Basic.CreateAndRun;
public class RunnableTask implements Runnable{

    @Override
    public void run()
    {
        System.out.println("I am a child thread.");
    }

    public static void main(String[] args) {

        RunnableTask runnableTask=new RunnableTask();

        new Thread(runnableTask).start();
        new Thread(runnableTask).start();
    }
}
