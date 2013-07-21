package lab4;

public class MyThreadRunnable implements Runnable {
	//reps field
	int reps;
	
	//MyThreadRunnable constructor
	public MyThreadRunnable(int r) {
		reps = r;
	}
	
	public static void main(String[] args) {
		MyThreadRunnable mtr = new MyThreadRunnable(200);
		Thread t1 = new Thread(mtr);
		t1.start();
		for(int i = 0; i < 200; i++)
			System.out.println("Main running ... ");
	}
	
	@Override
	public void run(){
		for(int i = 0; i < reps; i++)
			System.out.println("Thread running ... " + reps);
	}
	
}