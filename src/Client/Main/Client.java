package Client.Main;

import java.net.Socket;

import javax.swing.JOptionPane;

import Client.Handler.ComponentActionHandler;
import Client.Runner.InputListener;

public class Client {
	
	private final int width=400;
	
	private final int height=400;
	
	private String name="";
	
	private Window window;
	
	private ComponentActionHandler componentActionHandler;
	
	private Socket socket;
	
	private InputListener inputListener;
	
	public static void main(String[]args) {
		new Client();
	}
	
	private Client() {
		while(name.equals("")) {
			name=JOptionPane.showInputDialog("Your Name");
		}
		
		window=new Window(name+" 's Chatting Window",width,height);
		window.createWindow();
		window.getTextField().requestFocus();
		componentActionHandler=new ComponentActionHandler(this);
		window.getTextField().addActionListener(componentActionHandler);
	}
	
	public void setupSocket(String address,int port) {
		try {
			socket=null;
			socket=new Socket(address,port);
		}catch(Exception exception) {
			JOptionPane.showMessageDialog(window.getFrame(),"Invalid Address");
		}
		inputListener=new InputListener(this);
		inputListener.start();
	}
	
	public void reset() {
		socket=null;
		if(inputListener!=null) {
			inputListener.stop();
		}
		inputListener=null;	
	}
	
	public String getName() {
		return name;
	}
	
	public Window getWindow() {
		return window;
	}
	
	public Socket getSocket() {
		return socket;
	}
}
