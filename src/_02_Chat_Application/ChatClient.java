package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ChatClient {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	public void start() {
		try {
			connection = new Socket(ip,port);
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());	
			os.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
		while(connection.isConnected()) {
			try {
				ChatApp.message.setText(is.readObject()+"");
				System.out.println(is.readObject());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
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
}
