package Server.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private ServerSocket serverSocket;
	
	private ArrayList<Socket> sockets=new ArrayList<Socket>();
	
	public static void main(String[]args) {
		new Server();
	}
	
	private Server() {
		try {
			serverSocket=new ServerSocket((int)(Math.random()*6000)+4000);
			System.out.println("Address: "+serverSocket.getInetAddress());
			System.out.println("Port: "+serverSocket.getLocalPort());
		}catch(Exception exception) {
			System.out.println("Device does not support hosting");
		}
		
		new Connecter().start();
	}
	
	public void sendToAll(String message) {
		for(int i=0;i<sockets.size();i++) {
			try {
				ObjectOutputStream output=new ObjectOutputStream(sockets.get(i).getOutputStream());
				output.writeObject(message);
			}catch(Exception exception) {}
		}
	}
	
	
	class Connecter implements Runnable {
		
		private Thread thread;
		
		public Connecter() {
			thread=new Thread(this);
		}
		
		public synchronized void start() {
			thread.start();
		}
		
		@SuppressWarnings("deprecation")
		public synchronized void stop() {
			thread.stop();
		}
		
		public void run() {
			while(true) {
				try {
					Socket socket=serverSocket.accept();
					sockets.add(socket);
					new InputListener(socket).start();
					System.out.println("Connection from "+socket.getLocalAddress());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class InputListener implements Runnable {
		
		private Thread thread;
		
		private Socket socket;
		
		public InputListener(Socket socket) {
			thread=new Thread(this);
			this.socket=socket;
		}
		
		public synchronized void start() {
			thread.start();
		}
		
		@SuppressWarnings("deprecation")
		public synchronized void stop() {
			thread.stop();
		}
		
		public void run() {
			while(true) {
				
				try {
					ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
					String text=String.valueOf(input.readObject());
					sendToAll(text);
					System.out.println(text);
				}catch(Exception exception) {
					stop();
				}
				
			}
		}
	}
	
}
