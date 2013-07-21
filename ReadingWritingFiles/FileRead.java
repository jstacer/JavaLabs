package lab3;

import java.io.*;
import java.util.*;

public class FileRead {

	public FileRead(String fileName) throws IOException {
		
		Scanner input = new Scanner(new File(fileName));
		String data = input.nextLine();
		System.out.println(data);
		
		//close file
		input.close();
	}
	
	public static void main(String[] args) throws IOException {
		FileRead fr = new FileRead("myfile.txt");
	}
}
