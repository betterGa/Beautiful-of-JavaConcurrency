package LockPack;

import java.util.concurrent.locks.LockSupport;

public class ParkTest4 {
    public void testPark(){
        Thread
        LockSupport.park(this);
    }
    public static void main(String[] args) {
        ParkTest4 parkTest4=new ParkTest4();
        parkTest4.testPark();
    }
}
