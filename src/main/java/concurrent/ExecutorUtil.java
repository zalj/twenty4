package concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ExecutorUtil {

    public static void main(String[] args) throws Exception{
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("t1 finish");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            while (lock.isLocked()) {

            }
            System.out.println("t2 finish");
        });
        t2.start();
        t1.start();
    }
}
