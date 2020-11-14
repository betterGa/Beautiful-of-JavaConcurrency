package ThreadLocalRandomPack;
import java.util.Random;

public class RandomTest1 {
    public static void main(String[] args) {

        // 创建一个默认种子的 随机数生成器
        Random random=new Random();

        // 输出 10 个在 0~5 （包含0，不包含5）之间的随机数
        for(int i=0;i<10;i++){
            System.out.println(random.nextInt(5));
        }
    }
}
