package lab6;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerIntegerTest {

	public ServerIntegerTest() {

		try {
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("Server started at " + new Date() + '\n');

			// Listen for a connection request
			Socket socket = serverSocket.accept();

			// Create data input stream
			DataInputStream is = new DataInputStream(socket.getInputStream());
			System.out.println("Client connected!");
	
			int i = 1;
	
			while(i != 0){
				i = is.readInt();
				System.out.println(i);
			}
		}
		catch(IOException ex) {
			System.err.println(ex);
		}
	} //end constructor
	
	public static void main(String[] args) {
		new ServerIntegerTest();
		System.out.println();
	}
	
} //end class ServerIntegerTest
