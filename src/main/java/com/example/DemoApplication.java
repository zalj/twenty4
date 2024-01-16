package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class DemoApplication {

	static ConcurrentHashMap map = new ConcurrentHashMap();

	static class A {
		String val = "A";
	}

	public static void main(String[] args) {
		WeakReference<A> weakReference = new WeakReference<>(new A());
		System.out.println(weakReference.get());
		System.gc();
		System.out.println(weakReference.get());
		ThreadLocal threadLocal = new ThreadLocal();
		threadLocal.remove();
		map.put(1, 2);
	}

	public static int t() {
		int x;
		try {
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			return x;
		} finally {
			x = 3;
		}
	}

	private static void extracted() {
		Thread t1 = new Thread(new CharPrinter('A'));
		Thread t2 = new Thread(new CharPrinter('B'));
		Thread t3 = new Thread(new CharPrinter('C'));

		t1.start();
		t2.start();
		t3.start();
	}

	static volatile int curState = 0;
	static final Object lock = new Object();

	static class CharPrinter implements Runnable {

		char c;
		int state;

		public CharPrinter(char c) {
			this.c = c;
			this.state = c - 'A';
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				synchronized (lock) {
					while (curState != state) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.print(c);
					curState = curState == 2 ? 0 : curState + 1;
					lock.notifyAll();
				}
			}
		}
	}
}