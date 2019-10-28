package Client.Runner;

import java.io.ObjectInputStream;

import Client.Main.Client;

public class InputListener implements Runnable {
	
	private Thread thread;
	
	private Client client;
	
	public InputListener(Client client) {
		thread=new Thread(this);
		this.client=client;
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
			if(client.getSocket()!=null) {
				try {
					ObjectInputStream input=new ObjectInputStream(client.getSocket().getInputStream());
					client.getWindow().setText(client.getWindow().getText()+String.valueOf(input.readObject())+"\n");
					client.getWindow().getTextArea().setText(client.getWindow().getText());
				}catch(Exception exception) {
					System.out.println("Server is closed");
					System.exit(0);
				}
			}
		}
	}
}
