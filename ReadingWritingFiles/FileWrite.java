package lab3;

import java.io.*;

public class FileWrite {
	
	public FileWrite(String fileName, String msg) throws IOException {
		
		PrintWriter output = new PrintWriter(new File(fileName));
		output.println(msg);
		
		//close file
		output.close();
	}

	
	public static void main(String[] args) throws IOException {
		FileWrite fw = new FileWrite("myfile.txt", "Go Falcons");
	}
}
