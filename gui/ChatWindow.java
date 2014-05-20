package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import interfaces.*;

import javax.swing.*;

public class ChatWindow extends JFrame implements ActionListener, NetworkGUI  {
	private NetworkHandler netHandler;
	private boolean isServer;
	private JTextArea txt, log;
	private JButton go;
	private String myIP;

	private static final long serialVersionUID = 1L;

	public ChatWindow(NetworkHandler nh, String ip, boolean server) {
		super();
		netHandler = nh;
		myIP = ip;
		isServer = server;
		
		String name = "networkChat";
		if (isServer) name += " (Server)";
		
		setTitle(name);
	    setBounds(100, 100, 480, 480);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    setupGui();
	}
	
	public String getText() {
		return txt.getText();
	}
	
	public void setText(String text) {
		txt.setText(text);
	}
	
	public String getLog () {
		return log.getText();
	}
	public void setLog(String text) {
		log.setText(text);
	}
	
	public void sendMsg () {
	    String text = getText();
	    netHandler.send(new Message(text, myIP));
	    setText("");
	    setLog(getLog() + "\n me: " + text);
	    if (text.equals("/break")) netHandler.close();
	}
	
	public void makeVisibiletoWorld () {
        setVisible(true);
	}
	
	  // **********************  GUI setup ********************************

	private void setupGui() {
		log = new JTextArea(10, 20);
		log.setEditable(false);
		log.setBackground(Color.LIGHT_GRAY);
		log.setLineWrap(true);
		log.setWrapStyleWord(true);
		JScrollPane originalPane = new JScrollPane(log,
				  ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		txt = new JTextArea(10, 20);
		txt.setLineWrap(true);
		txt.setWrapStyleWord(true);
		JScrollPane markedPane = new JScrollPane(txt,
				  ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		go = new JButton("Send Message");
		go.addActionListener(this);

		Box box1 = Box.createVerticalBox();
		box1.add(Box.createVerticalStrut(10));
		box1.add(originalPane);
		box1.add(Box.createVerticalStrut(10));
		box1.add(markedPane);
		box1.add(Box.createVerticalStrut(10));
		box1.add(go);

		Box box3 = Box.createHorizontalBox();
		box3.add(box1);
		box3.add(Box.createHorizontalStrut(20));

		Container c = getContentPane();
		c.setLayout (new FlowLayout());
		c.add(box3);
	}


	public void receive(Serializable s) {
		//System.out.println("Chat aqui");
		Message msg = (Message)s;
		
		setLog(getLog() + "\n other: " + msg.getText());
	}

	public void actionPerformed(ActionEvent arg0) {
		sendMsg();
	}

}
