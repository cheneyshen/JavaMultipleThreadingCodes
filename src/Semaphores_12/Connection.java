package Semaphores_12;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();
	
	private Semaphore sem = new Semaphore(10, true);
	private int connections = 0;
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		try {
			sem.acquire();
			doConnect();
		} catch (InterruptedException e) {
			
		} finally {
			sem.release();
		}
	}

	private void doConnect() {
		synchronized(this) {
			connections++;
			System.out.println("Current connections (max 10 allowed): " + connections);
		}
		try {
			System.out.println("Working on connections " + Thread.currentThread().getName());
			Thread.sleep(2000);
		} catch (InterruptedException e) {} 
		synchronized (this) {
			connections--;
			System.out.println("I'm done " + Thread.currentThread().getName() + " Connection is released , connection count : " + connections) ;
		}
	}
}
