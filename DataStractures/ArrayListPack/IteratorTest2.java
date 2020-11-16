package DataStractures.ArrayListPack;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class IteratorTest2 {
    private static volatile CopyOnWriteArrayList<String> arrayList=new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        arrayList.add("hello");
        arrayList.add("jia");
        arrayList.add("welcome");
        arrayList.add("to");
        arrayList.add("ohh");

        Thread threadOne=new Thread(new Runnable() {
            @Override
            public void run() {

                // 修改 list 中下标为1 的元素为 bin
                arrayList.set(1,"bin");

                // 删除元素
                arrayList.remove(2);
                arrayList.remove(3);
            }
        });

        // 保证在 修改线程 启动前 获取迭代器
        Iterator<String> itr=arrayList.iterator();

        // 启动 修改线程
        threadOne.start();

        // 等待子线程执行完毕
        threadOne.join();

        // 迭代元素
        while (itr.hasNext()){
            System.out.println(itr.next());
        }

    }
}
