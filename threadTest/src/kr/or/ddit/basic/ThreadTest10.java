package kr.or.ddit.basic;


// yield()메서드 예제

public class ThreadTest10 {
   public static void main(String[] args) {
      YieldThread th1 = new YieldThread("1번 스레드");
      YieldThread th2 = new YieldThread("2번 스레드");
      
      th1.start();
      th2.start();
      
      try {
         Thread.sleep(1);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
      }
      System.out.println("--------------------------------- 111111111");
      
      th1.setWork(false);
      
      try {
         Thread.sleep(1);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
      }
      System.out.println("--------------------------------- 222222222");
      
      th1.setWork(true);
      
      try {
         Thread.sleep(1);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
      }
      System.out.println("--------------------------------- 333333333");
      
      th1.setStop(true);
      th2.setStop(true);
      
   }
}


// yield()메서드 연습용 스레드
class YieldThread extends Thread {
   private boolean stop = false;
   private boolean work = true;
   
   public YieldThread(String name) {
      super(name);   // 스레드의 이름을 설정
   }
   
   @Override
   public void run() {
      while(!stop) {
         if(work) {
            System.out.println(getName() + " 작업 중.");
         } else {
            System.out.println(getName() + " 양보.");
            Thread.yield();
         }
      }
   }

   public boolean isStop() {
      return stop;
   }

   public void setStop(boolean stop) {
      this.stop = stop;
   }

   public boolean isWork() {
      return work;
   }

   public void setWork(boolean work) {
      this.work = work;
   }
   
}