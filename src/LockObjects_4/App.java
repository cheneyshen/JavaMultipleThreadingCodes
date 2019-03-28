package LockObjects_4;

public class App {

	public static void main(String[] args) {
		System.out.println("Synchronized Objects: ");
		Worker worker = new Worker();
		worker.main();
		System.out.println("Synchronized Methods: ");
		WorkerMethodsSynchronized wms = new WorkerMethodsSynchronized();
		wms.main();
	}

}
