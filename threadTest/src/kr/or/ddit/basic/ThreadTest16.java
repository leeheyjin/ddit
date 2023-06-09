package kr.or.ddit.basic;

// 은행의 입출금을 스레드로 처리하는 예제
public class ThreadTest16 { // 이 클래스의객체가 공통으로 사용할 객체가 된다
	private int balance; // 잔액이 저장될 변수

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금을 처리하는 메서드
	public void deposit(int money) {
		balance += money;
	}

	// 출금을 처리하는 메서드. 출금을 성공하면 true, 실패하면 false
	public synchronized boolean withdraw(int money) {
		if (balance >= money) {
			for (long i = 1l; i <= 1_000_000_000; i++) { // 시간 지연용 반복문
			}
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		ThreadTest16 account = new ThreadTest16();
		account.setBalance(10000); // 현재 잔액 10000원

		// 스레드는 익명 구현체로 구현
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				boolean result = account.withdraw(6000);
				System.out.println("스레드에서 result = " + result + ", balance = " + account.getBalance());
			}
		};
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		thread1.start();
		thread2.start();

	}

}
