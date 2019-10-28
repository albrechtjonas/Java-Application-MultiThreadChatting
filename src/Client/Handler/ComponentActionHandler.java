package Client.Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Client.Main.Client;

public class ComponentActionHandler implements ActionListener {
	
	private Client client;
	
	public ComponentActionHandler(Client client) {
		this.client=client;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		JTextField textField=client.getWindow().getTextField();
		
		if(textField.getText().equals("join")) {
			client.reset();
			String address=JOptionPane.showInputDialog(client.getWindow().getFrame(),"Address");
			
			int port=0;
			
			try {
				port=Integer.valueOf(JOptionPane.showInputDialog(client.getWindow().getFrame(),"Port"));
			}catch(Exception exception) {}
			
			client.setupSocket(address,port);
			textField.setText("");
		}else if(textField.getText().trim().equals("")){
			JOptionPane.showMessageDialog(client.getWindow().getFrame(),"Cannot send empty message");
			textField.setText("");
		}else {
			socketSendMessage(client.getName()+": "+textField.getText());
			textField.setText("");
		}
	}
	
	private void socketSendMessage(String message) {
		if(client.getSocket()!=null) {
			try {
				ObjectOutputStream output=new ObjectOutputStream(client.getSocket().getOutputStream());
				output.writeObject(message);
			}catch(Exception exception) {}
		}else {
			JOptionPane.showMessageDialog(client.getWindow().getFrame(),"You need to join a server");
		}
	}
}
