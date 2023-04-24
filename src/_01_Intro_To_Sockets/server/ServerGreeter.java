package _01_Intro_To_Sockets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import javax.swing.JOptionPane;


public class ServerGreeter extends Thread {
	//1. Create an object of the ServerSocket class
ServerSocket srsk;
	public ServerGreeter() throws IOException {
		//2. Initialize the ServerSocket object. In the parameters,
		//   you must define the port at which the server will listen for connections.
		srsk = new ServerSocket(3030, 65);
		//*OPTIONAL* you can set a time limit for the server to wait by using the 
		//  ServerSocket's setSoTimeout(int timeInMilliSeconds) method
	}

	public void run() {
		//3. Create a boolean variable and initialize it to true.
		boolean loop = true;
		//4. Make a while loop that continues looping as long as the boolean created in the previous step is true.
			while(loop) {
			//5. Make a try-catch block that checks for two types Exceptions: SocketTimeoutException and IOException.
			//   Put steps 8 - 15 in the try block.
		try {
				//8. Let the user know that the server is waiting for a client to connect.
			Socket sk = srsk.accept();
			JOptionPane.showMessageDialog(null, "Server is waiting for a client to connect.");
				//9. Create an object of the Socket class and initialize it to serverSocket.accept();
				//   Change serverSocket to match the ServerSocket member variable you created in step 1.
				//   The program will wait her until either a client connects or the timeout expires.
				//10. Let the user know that the client has connected.
				JOptionPane.showMessageDialog(null, "Client has connected to server.");
				//11. Create a DataInputStream object. When initializing it, use the Socket object you created in step 9 to call the getInputStream() method.
DataInputStream dis = new DataInputStream(sk.getInputStream());
				//12. Print the message from the DataInputStream object using the readUTF() method
				System.out.println(dis.readUTF());
				//13. Create a DataOutputStream object. When initializing it, use the Server object you created in step 9 to call the getOutputStream() method.
				DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
				//14. Use the DataOutputStream object to send a message to the client using the writeUTF(String message) method.
				dos.writeUTF("You(Client) have connected to the server.");
				//15. Close the client server
				sk.close();
		} catch(SocketTimeoutException e) {
			JOptionPane.showMessageDialog(null, "A SocketTimeoutException Occured.");
			loop = false;
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "An IOException Occured.");
			loop = false;
		}
			//6. If the program catches a SockeTimeoutException, let the user know about it and set loop's boolean variable to false.
//done
			//7. If the program catches a IOException, let the user know about it and set the loop's boolean variable to false.
//done
		
	}
	}

	public static void main(String[] args) {
		//16. In a new thread, create an object of the ServerGreeter class and start the thread. Don't forget the try-catch.
		Thread th = new Thread(()->{ServerGreeter sg = null;
		try {
			sg = new ServerGreeter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sg.run();});
		try {
		th.start();
		}catch(Exception e){
			
		}
	}
	
}
