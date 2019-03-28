package CallableAndFuture_13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class MyCallable implements Callable<Integer> {

	int value;
	
	public MyCallable(int i) {
		this.value = i;
	}
	
	public Integer call() throws Exception {
		Integer sum = 0;
		for (int i=0; i<value; i++) {
			sum += i;
		}
		System.out.println("Sum in Callable.Call() " + sum);
		return sum;
	}
}

public class App2 {
	public static void main(String[] args) throws InterruptedException {
		List<Integer> list = new ArrayList<>();
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future;
		
		for(int i=1; i<10; i++) {
			future = executor.submit(new MyCallable(i));
			try {
				list.add(future.get());
			} catch(ExecutionException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);

		for(int i=0; i<list.size(); i++) {
			System.out.println("List Values " + i + " Value: " + list.get(i));
		}
	}

}
