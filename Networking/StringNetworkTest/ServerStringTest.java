package lab6;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStringTest {

	public ServerStringTest() {

		try {
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("Server started at " + new Date() + '\n');

			// Listen for a connection request
			Socket socket = serverSocket.accept();

			// Create data input and output streams
			
			//DataInputStream inputFromClient = new DataInputStream(
				//socket.getInputStream());
			
			
			DataOutputStream outputToClient = new DataOutputStream(
				socket.getOutputStream());

//			while (true) {
//				// Receive radius from the client
//				double radius = inputFromClient.readDouble();
//
//				// Compute area
//				double area = radius * radius * Math.PI;
//
//				// Send area back to the client
//				outputToClient.writeDouble(area);
//
//			}
			PrintStream ps = new PrintStream(outputToClient);
			System.out.println("Press enter to send string");
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			ps.println("This is a test string.");
		}
		catch(IOException ex) {
			System.err.println(ex);
		}
	} //end constructor
	
	public static void main(String[] args) {
		new ServerStringTest();
		System.out.println();
		System.out.println("Press any key to exit.");
		Scanner end = new Scanner(System.in);
		end.nextLine();
	}
	
} //end class ServerStringTest
