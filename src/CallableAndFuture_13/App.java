package CallableAndFuture_13;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			public Integer call() throws TimeoutException {
				Random random = new Random();
				int duration = random.nextInt(4000);
				if(duration > 2000) {
					throw new TimeoutException("Sleeping for too long");
				}
				
				System.out.println("Starting ...");
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {}
				System.out.println("Finished.");
				return duration;
			}
		});
		
		executor.shutdown();
		try {
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
			
		} catch (ExecutionException e) {
			TimeoutException ex = (TimeoutException) e.getCause();
			System.out.println(ex.getMessage());
		}
		
	}

}
