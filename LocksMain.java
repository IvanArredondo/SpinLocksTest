import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LocksMain {

	public static void main(String[] args){ 

		//right now all hard coded for 4 threads, maybe if i have time switch that to n threads
		//testFilterLock();

		//wait 3 seconds
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		testBakeryLock();


	}

	public static void testFilterLock() {

		MyFilterLock myFilterLock = new MyFilterLock(4);

		int sharedInt1 = 0;
		int sharedInt2 = 0;
		boolean usingLock = true;
	
		ExecutorService executor = Executors.newFixedThreadPool(4);

		IncrementingTask incrementingTask = new IncrementingTask(myFilterLock, sharedInt1, usingLock);
		IncrementingTask incrementingTaskNoLocks = new IncrementingTask(null, sharedInt2, !usingLock);
		

		System.out.println("Using the filter lock to increment shared variable:");
		for (int i = 0; i < 4; i++) {
			executor.execute(new Thread(incrementingTask));
		}
		try {
			executor.shutdown();  //shutting threads down
			executor.awaitTermination(3, TimeUnit.SECONDS);  //gives threads time to finish
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExecutorService executor2 = Executors.newFixedThreadPool(4);

		System.out.println("Using no lock to increment shared variable:");
		for (int i = 0; i < 4; i++) {
			executor2.execute(new Thread(incrementingTaskNoLocks));
		}
		try {
			executor2.shutdown();  //shutting threads down
			executor2.awaitTermination(3, TimeUnit.SECONDS);  //gives threads time to finish
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testBakeryLock() {

		MyBakeryLock myBakeryLock = new MyBakeryLock(4);

		int sharedInt1 = 0;
		int sharedInt2 = 0;
		boolean usingLock = true;

		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		IncrementingTask incrementingTask = new IncrementingTask(myBakeryLock, sharedInt1, usingLock);
		IncrementingTask incrementingTaskNoLocks = new IncrementingTask(null, sharedInt2, !usingLock);

		System.out.println("Using the filter lock to increment shared variable:");
		for (int i = 0; i < 4; i++) {
			executor.execute(new Thread(incrementingTask));
		}
		try {
			executor.shutdown();  //shutting threads down
			executor.awaitTermination(3, TimeUnit.SECONDS);  //gives threads time to finish
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExecutorService executor2 = Executors.newFixedThreadPool(4);

		System.out.println("Using no lock to increment shared variable:");
		for (int i = 0; i < 4; i++) {
			executor2.execute(new Thread(incrementingTaskNoLocks));
		}
		try {
			executor2.shutdown();  //shutting threads down
			executor2.awaitTermination(3, TimeUnit.SECONDS);  //gives threads time to finish
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}