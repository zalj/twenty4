package spittr;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {

    public static final int MAX_TRY_TIMES = 100;
    static int CASH = 0;
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Object bank = new Object();
        Thread consumer = new Thread(() -> {
            System.out.println("consumer start");
            try {
                synchronized (bank) {
                    while (CASH <= 50) {
                        bank.wait();
                    }
                    while (CASH > 50) {
                        CASH -= 50;
                        System.out.println("花钱了，花了 50，还剩" + CASH + "元。");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consumer finish");
        });
        consumer.setName("consumer");
        Thread producer = new Thread(() -> {
            System.out.println("producer start");
            synchronized (bank) {
                while (CASH <= 50) {
                    int salary = ThreadLocalRandom.current().nextInt(200) + 1;
                    CASH += salary;
                    System.out.println("挣钱了，挣了" + salary + "块, 现在有" + CASH + "块了。");
                }
                bank.notify();
            }
        });
        producer.setName("producer");
        consumer.start();
        producer.start();
    }

    private static void doTest(Runnable r1, Runnable r2) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(CASH);
        long entTime = System.currentTimeMillis();
        System.out.println(r1.getClass().getSimpleName() + " cost " + (entTime - startTime) + " ms");
    }

    private static void testSynchronized() throws InterruptedException {
        doTest(new SynchronizedTask(), new SynchronizedTask());
    }

    private static void testReentrantLock() throws InterruptedException {
        doTest(new ReentrantLockTask(), new ReentrantLockTask());
    }

    static class SynchronizedTask extends Counter implements Runnable {

        public SynchronizedTask() {
        }

        public SynchronizedTask(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < getCount(); i++) {
                synchronized (SynchronizedTask.class) {
                    CASH++;
                }
            }
        }
    }

    static class ReentrantLockTask extends Counter implements Runnable {

        public ReentrantLockTask() {
        }

        public ReentrantLockTask(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < getCount(); i++) {
                lock.lock();
                CASH++;
                lock.unlock();
            }
        }
    }

    static class Counter  {
        int count = MAX_TRY_TIMES;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    static class TestInterrupt implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < MAX_TRY_TIMES; i++) {
                CASH++;
                if (i == 10) {
                    Thread currentThread = Thread.currentThread();
                    System.out.println("[run]threadName: " + currentThread.getName());
                    currentThread.interrupt();
                }
            }
        }
    }
}
