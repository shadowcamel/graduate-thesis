import java.util.*;
import java.util.concurrent.locks.*;

public class TaskThread{
	private static HashSet<Integer> hs=new HashSet<Integer>();
	private static Lock lock =new ReentrantLock();
	public static void main(String[] args){
		Runnable task1=new AddData();
		Runnable task2=new ShowData();
		Thread thread1=new Thread(task1);
		Thread thread2=new Thread(task2);
		thread1.start();
		thread2.start();
	}
	static class AddData implements Runnable{
		public void run(){
			Timer timer=new Timer();
			timer.schedule(new mytask(),0,1000);
		}
		
		class mytask extends TimerTask{
			public void run(){
				lock.lock();
				hs.add((int)(Math.random()*100));
				lock.unlock();
			}
		}
	}
	static class ShowData implements Runnable{
		public void run(){
			Timer timer=new Timer();
			timer.schedule(new mytask2(),0,1000);
		}
		class mytask2 extends TimerTask{
			public void run(){
				lock.lock();
				Iterator<Integer> iterator=hs.iterator();
				while(iterator.hasNext())
					System.out.println(iterator.next());
				lock.unlock();
			}
		}
	}
}