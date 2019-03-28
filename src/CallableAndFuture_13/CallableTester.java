package CallableAndFuture_13;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class CallableImpl implements Callable<Integer> {
	private int myName;
	CallableImpl(int i) {
		myName = i;
	}
	public Integer call() {
		for(int i=0; i<10; i++) {
			System.out.println("Thread : " + getMyName() + " value is : " + i);
		}
		return getMyName();
	}
	
	public int getMyName() {
		return myName;
	}
	
	public void setMyName() {
		this.myName = myName;
	}
}
public class CallableTester {

	public static void main(String[] args) throws InterruptedException {
		Callable<Integer> callable = new CallableImpl(2);
		ExecutorService executor = new ScheduledThreadPoolExecutor(1);
		Future<Integer> future = executor.submit(callable);
		try {
			System.out.println("Future value: " +  future.get());
		} catch (Exception e) {}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	}

}
