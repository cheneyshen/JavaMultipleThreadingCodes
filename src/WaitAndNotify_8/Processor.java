package WaitAndNotify_8;

import java.util.Scanner;

public class Processor {
	public void produce() throws InterruptedException {
		synchronized(this) {
			System.out.println("Porducer thread running ...");
			wait();
			System.out.println("Resume.");
		}
	}
	
	public void consumer() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized(this) {
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key pressed");
			notify();
			Thread.sleep(5000);
			System.out.println("Consumption done.");
		}
	}
}
