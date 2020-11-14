package ThreadLocalRandomPack;

import sun.misc.Unsafe;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class RandomTest2 {
    public static void main(String[] args) {

        // 获取一个随机数生成器
        ThreadLocalRandom random=ThreadLocalRandom.current();
        for(int i=0;i<10;i++)
        {
            // 输出 10 个在 0~5 （包含0，不包含5）之间的随机数
            System.out.println(random.nextInt(5));
        }
    }

}
