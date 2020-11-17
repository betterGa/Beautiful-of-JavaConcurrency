package DataStractures.ArrayListPack;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.LockSupport;

/**
 * 迭代器的使用
 */

public class IteratorTest1 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> arrayList=new CopyOnWriteArrayList<>();
        arrayList.add("hello");
        arrayList.add("jia");
        Iterator<String> itr=arrayList.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
