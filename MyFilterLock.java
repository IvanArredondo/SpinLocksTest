import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyFilterLock implements Lock{
	//since 
	volatile int[] level;
	volatile int[] victim;
	
	int n;

	public MyFilterLock(int n) {
		level = new int[n];
		victim = new int[n];
		this.n = n;
		
		for (int i = 0; i < n; i++) {
			level[i] = 0;
		}
	}

	@Override
	public void lock() {
		int me = ThreadID.get();
		
		for (int i = 1; i < n; i++) {
			level[me] = i;
			victim[i] = me;
			for (int j = 0; j < n; j++) {
				while((j != me) && (level[j] >= i && victim[i] == me));
			}
		}
		
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg1) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		int me = ThreadID.get();
		level[me] = 0;
		
	}

}