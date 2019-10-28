package Client.Main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class Window {
	private String title;
	
	private int width;
	
	private int height;
	
	private JFrame frame;
	
	private JTextArea textArea;
	
	private String text="Chatting History: \n";
	
	private JTextField textField;
	
	private JScrollPane scrollPane;
	
	private DefaultCaret defaultCaret;
	
	public Window(String title,int width,int height) {
		this.title=title;
		this.width=width;
		this.height=height;
	}
	
	public void createWindow() {
		frame=new JFrame();
		frame.setTitle(title);
		
		textArea=new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText(text);
		scrollPane=new JScrollPane(textArea);
		defaultCaret=(DefaultCaret)textArea.getCaret();
		defaultCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
		
		textField=new JTextField();
		frame.getContentPane().add(textField,BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(width,height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public void setText(String text) {
		this.text=text;
	}
	
	public String getText() {
		return text;
	}
	
	public JTextField getTextField() {
		return textField;
	}
}
