package CountDownLatch_6;
import java.util.concurrent.*;

class Processor implements Runnable {

	private CountDownLatch latch;
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}
	public void run() {
		System.out.println("Started.");
		try {
			Thread.sleep(3000);
		} catch(InterruptedException ignored) {}
		latch.countDown();
	}
}

public class App {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i=0; i<3; i++) {
			executor.submit(new Processor(latch));
		}
		executor.shutdown();
		try {
			latch.await();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed.");
	}
}
