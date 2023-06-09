package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
 * Vector, Hashtable 등 예전부터 존재하던 Collections객체들은 내부에 동기화 처리가 되어 이ㅓㅆ다. 
 */
public class ThreadTest17 {
	private static Vector<Integer> vector = new Vector<>();

	// 동기화 처리가 되지 않은 list
	private static List<Integer> list = new ArrayList<>();

	// 동기화 처리를 할 list
	private static List<Integer> list2 = Collections.synchronizedList(list);

	public static void main(String[] args) {
		// 익명 구현체로 스레드 구현
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
//					vector.add(i);
					list2.add(i);
				}
			}
		};

		Thread[] threads = new Thread[] { new Thread(runnable), new Thread(runnable), new Thread(runnable),
				new Thread(runnable), new Thread(runnable) };

		for (Thread thread : threads) {
			thread.start();
		}

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		System.out.println("vector의 개수: " + vector.size());
//		System.out.println("array list의 개수: " + list.size());
		System.out.println("synchronized list의 개수: " + list2.size());
	}

}
