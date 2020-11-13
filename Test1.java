public class Test1 {
    public static void main(String[] args) {
        Object[] array=new Object[3];
        System.out.println(array==null);
        array[0]=null;
        array[1]=null;
        array[2]=null;
        System.out.println(array==null);
    }
}
