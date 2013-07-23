package lab2;

public class TestExceptions {

	public static void main(String[] args) throws ArithmeticException {
		
		int myArr[ ] = new int[10];
		int x,y,z;
		x=0;
		y=10;
		z=y/x;
			
		myArr[10] = 0;
		System.out.println("The end.");
	}
}
