package lab4;

public class MyThread extends Thread {
	//reps field
	int reps;
	
	//MyThread constructor
	public MyThread(int r) {
		reps = r;
	}
	
	public static void main(String[] args) {
		MyThread mt1 = new MyThread(500);
		MyThread mt2 = new MyThread(400);
		MyThread mt3 = new MyThread(300);
		MyThread mt4 = new MyThread(200);
		
		mt1.start();
		mt2.start();
		mt3.start();
		mt4.start();
	}
	
	@Override
	public void run(){
		for(int i = 0; i < reps; i++)
			System.out.println("Thread running ... " + reps);
	}
	
}
