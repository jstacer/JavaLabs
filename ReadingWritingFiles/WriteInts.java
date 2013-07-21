package lab3;

import java.io.*;

public class WriteInts {

	public WriteInts(String fileName, int[] myArr) throws IOException {
		DataOutputStream output = new DataOutputStream(new FileOutputStream(fileName));
		int i;
		for(i = 0; i < myArr.length; i++){
			output.writeInt(myArr[i]);
		}
		output.close();
	}
	
	public static void main(String[] args) throws IOException {
		int myArr[] = {16, 31, 90, 45, 89};
		WriteInts wi = new WriteInts("mydata.dat", myArr);
	}
}
