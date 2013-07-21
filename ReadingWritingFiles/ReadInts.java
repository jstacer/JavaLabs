package lab3;

import java.io.*;

public class ReadInts {

	public ReadInts(String fileName) throws IOException {
		DataInputStream input = new DataInputStream(new FileInputStream(fileName));
		int token = input.readInt();
		try{
			while(true){
				System.out.println(token);
				token = input.readInt();
			}
		}catch(EOFException ex){
			System.out.println("All data were read.");
		}
		//close file stream
		input.close();
	}
	
	public static void main(String[] args) throws IOException {
		ReadInts ri = new ReadInts("mydata.dat");
	}
}
