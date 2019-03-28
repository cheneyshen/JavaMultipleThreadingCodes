package ThreadsPools_5;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Worker implements Runnable {
	private Random random = new Random();
	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	
	public List<Integer> list1 = new ArrayList<>();
	public List<Integer> list2 = new ArrayList<>();
	
	public void run() {
		process();
	}
	private void process() {
		for(int i=0; i<1000; i++) {
			stageOne();
			stageTwo();
		}
	}
	
	private void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	
	private void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}
}

public class WorkerThreadPool {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		System.out.println("Starting ...");
		long start = System.currentTimeMillis();
		Worker worker = new Worker();
		for(int i=0; i<2; i++) {
			executor.submit(worker);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
		long end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end-start));
		
		System.out.println("List1: " + worker.list1.size() + "; List 2: " + worker.list2.size());
	}
}
