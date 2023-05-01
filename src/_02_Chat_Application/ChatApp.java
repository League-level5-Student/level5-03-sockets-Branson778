package _02_Chat_Application;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	JTextField input = new JTextField();
	static public JLabel message = new JLabel();
	ChatServer server;
	ChatClient client;
	public static void main(String[] args) {
		new ChatApp();
	}
	public ChatApp(){
 int hosting = JOptionPane.showConfirmDialog(null, "Would You Like To Host A Chat Application Server?", "Server Activator", JOptionPane.YES_NO_OPTION);
if(hosting == JOptionPane.YES_OPTION) {
	server = new ChatServer(3148);
	setTitle("SERVER");
	JOptionPane.showMessageDialog(null, "Server started at: "+server.getIpAdress()+"\nPort: "+ server.getPort());
    input.setEditable(true);
    message.setBounds(0,0,775,200);
    input.setBounds(0,250,775,200);
    input.addActionListener((e)->{
    	server.sendText(input.getText());
    });
    add(message);
    add(input);
    setLayout(null);
	setVisible(true);
    setSize(800,500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	server.start();
}else {
	setTitle("CLIENT");
	String ipAdd = JOptionPane.showInputDialog("Enter The IP Adress of Your Desired Server");
	String servPrt = JOptionPane.showInputDialog("Enter The Port Number of Your Desired Server");
	int port = Integer.parseInt(servPrt);
	client = new ChatClient(ipAdd,port);
	 input.setEditable(true);
	    message.setBounds(0,0,775,200);
	    input.setBounds(0,250,775,200);
	    input.addActionListener((e)->{
	    	client.sendText(input.getText());
	    });
	    add(message);
	    add(input);
	    setLayout(null);
	setVisible(true);
	setSize(800,500);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	client.start();
}
	}
}
