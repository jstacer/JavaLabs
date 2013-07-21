package lab3;
import java.io.*;

public class FileDetails extends File {
	
	public FileDetails(String file){
		super(file);
		System.out.println("File size is: " + length());
		System.out.println("File is readable: " + canRead());
		System.out.println("File is writeable: " + canWrite());
	}
	
	public static void main(String[] args) {
		FileDetails fd = new FileDetails("anyfile.doc");
	}
}
