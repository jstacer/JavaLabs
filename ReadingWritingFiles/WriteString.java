package lab3;

import java.io.*;

public class WriteString {

	public WriteString(String fileName, String msg) throws IOException {
		PrintStream output = new PrintStream(new FileOutputStream(fileName));
		output.println(msg);
	}
	
	public static void main(String[] args) throws IOException {
		WriteString ws = new WriteString("f1.txt", "Hello world");
	}
}