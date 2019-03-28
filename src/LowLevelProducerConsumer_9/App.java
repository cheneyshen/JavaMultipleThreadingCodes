package LowLevelProducerConsumer_9;

public class App {

	public static void main(String[] args) throws InterruptedException {
		final Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					
				}
			}
		});
		
		t1.start();
		t2.start();
		Thread.sleep(3000);
		System.exit(0);
	}

}
