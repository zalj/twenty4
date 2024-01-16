package redis;

import java.util.LinkedList;

public class Redis {
    public static void main(String[] args) throws InterruptedException {
        int i = 0b111100001111111111111110;
        bitCount(i);
    }

    public static int bitCount(int i) {
        print(i);
        i = i - ((i >>> 1) & 0x55555555);
        print(i);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        print(i);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        print(i);
        i = i * 0x01010101;
        print(i);
        i >>>= 24;
        print(i);
        return i;
    }

    public static void print(int i) {
        String s = Integer.toBinaryString(i);
        while (s.length() < 32) {
            s = '0' + s;
        }
        for (int j = 0; j < 4; j++) {
            System.out.print(s.substring(j * 8, j * 8 + 8) + "  ");
        }
        System.out.println();
    }
}

class ACID {
    /* 原子性 */
    String Atomicity = "undolog"; // 通过 undolog 回滚失败的事务
    /* 一致性 */
    String Consistency = "业务数据最终要符合操作的最终一致性";      // 其他三个特性+业务代码保证
    /* 隔离性 */
    String Isolation = "多个事务并发执行，内部操作不能互相干扰"; // 事务的四个隔离级别
    /* 持久性 */
    String Durability = "";
}

class IsolationLevel {
    // 脏读
    String READ_UNCOMMITED = "读未提交";
    // 不可重复读，在一个事务内，多次读同一数据值不同 select * from table where id = 1;
    String READ_COMMITED   = "读已提交";
    // 可重复读，在一个事务内，多次读同一数据值值相同，解决了读已提交的不可重复度问题
    // 脏写问题，幻读问题
    String REPEATABLE_READ = "可重复读";
    // 串行化，效率低，不存在其他问题
    String SERIALIZEABLE   = "串行化";
}

class MyQueue<T> {
    final Object notFull = new Object();
    final Object notEmpty = new Object();
    LinkedList<T> queue = new LinkedList<>();
    int capacity;

    public void add(T t) throws InterruptedException {
        synchronized (notFull) {
            while (queue.size() == capacity) {
                notFull.wait();
            }
            queue.add(t);
            synchronized (notEmpty) {
                notEmpty.notifyAll();
            }
        }
    }

    public T take() {
        synchronized (notEmpty) {
            return null;
        }
    }


}

class MyThread extends Thread {
    boolean stop = false;
    public void run() {
        while (!stop) {
            try {
                System.out.println(getName() + " is Running");
                sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("wake up in sleep.");
                e.printStackTrace();
                return;
            }
        }
    }
}
