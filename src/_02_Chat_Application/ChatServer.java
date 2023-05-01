package _02_Chat_Application;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ChatServer {
private int port;

private ServerSocket server;
private Socket connection;

ObjectOutputStream os;
ObjectInputStream is;
public ChatServer(int port) {
	this.port = port;
}
public void start() {
	try {
		server = new ServerSocket(port, 100);
		connection = server.accept();
		os = new ObjectOutputStream(connection.getOutputStream());
		is = new ObjectInputStream(connection.getInputStream());
		os.flush();
		while(connection.isConnected()) {
			try {
					ChatApp.message.setText(is.readObject()+"");
				System.out.println(is.readObject());
			}catch(EOFException e) {
				JOptionPane.showMessageDialog(null, "Connection To Server Lost");
				System.exit(0);
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public String getIpAdress() {
	try {
		return InetAddress.getLocalHost().getHostAddress();
	} catch (UnknownHostException e) {
		return "ERROR!";
	}
}
public int getPort() {
	return port;
}
public void sendText(String send) {
	try {
		if(os != null) {
			os.writeObject(send);
			os.flush();
		}
	}catch(IOException e) {
		e.printStackTrace();
	}
}
//test
}
