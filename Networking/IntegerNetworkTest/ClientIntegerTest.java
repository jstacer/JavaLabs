package lab6;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientIntegerTest {
	
	public ClientIntegerTest() {

		try {
			// Create a socket to connect to the server
			Socket socket = new Socket("localhost", 8000);

			// Create an output stream to send integers
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());
			
			System.out.println("Press enter to start sending integers \n");
			
			Scanner in = new Scanner(System.in);
			in.nextLine();
			
			os.writeInt(18);
			os.writeInt(25);
			os.writeInt(32);
			os.writeInt(40);
			os.writeInt(0);
		}
		catch (IOException ex) {
			System.out.println(ex.toString() + '\n');
		}
	} // end constructor
	
	public static void main(String[] args) {
		new ClientIntegerTest();
		System.out.println();
	}
} // end class ClientIntegerTest
