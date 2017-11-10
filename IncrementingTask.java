import java.util.concurrent.locks.Lock;

public class IncrementingTask implements Runnable{

	Lock lock;
	int number;
	boolean usingLock;

	public IncrementingTask(Lock lock, int number, boolean usingLock) {

		this.lock = lock;
		this.number = number;
		this.usingLock = usingLock;
	}

	@Override
	public void run() {
		if(usingLock) {
			lock.lock();
			for (int i = 0; i < 10; i++) {
				number++;
				System.out.print(" " + number);

			}

			lock.unlock();
		}else {
			for (int i = 0; i < 10; i++) {
				number++;
				System.out.print(" " + number);

			}
		}
	}

}
