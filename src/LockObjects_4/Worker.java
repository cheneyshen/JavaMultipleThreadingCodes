package LockObjects_4;
import java.util.*;

public class Worker {
	private Random random = new Random();
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	
	public void stageOne() {
		synchronized(lock1) {
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	
	public void stageTwo() {
		synchronized(lock2) {
			try {
				Thread
			}
		}
	}

	private void synchronized(Object lock22) {
		// TODO Auto-generated method stub
		
	}
}
