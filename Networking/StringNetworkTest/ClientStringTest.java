package lab6;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientStringTest {
	
	public ClientStringTest() {

		try {
			// Create a socket to connect to the server
			Socket socket = new Socket("localhost", 8000);

			// Create an input stream reader to receive string from the server
			InputStreamReader fromServer = new InputStreamReader(socket.getInputStream());

			// Create an output stream to send data to the server
			//DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
			
			System.out.println("Waiting for string ...\n");
			
			//wrap InputStreamReader in a BufferedReader, read line, and print
			System.out.println("String from server: " + new BufferedReader(fromServer).readLine());
		}
		catch (IOException ex) {
			System.out.println(ex.toString() + '\n');
		}
	} // end constructor
	
	public static void main(String[] args) {
		new ClientStringTest();
		System.out.println();
		System.out.println("Press any key to exit");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
} // end class ClientStringTest
