package view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Online2P extends JDialog{
	
	public Online2P() {
		JFrame socket = new JFrame("server_client");
		socket.setSize(300, 400);
		socket.setResizable(false);
		socket.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		final JPanel panel = new JPanel();
		final JRadioButton isServer = new JRadioButton("Server");
		final JRadioButton isClient = new JRadioButton("Client");
		final JLabel ip = new JLabel("IP address : ");
		final  JTextField ip_server = new JTextField(12);
		final JLabel port = new JLabel("Port number : ");
		final JTextField port_server = new JTextField(5);
		final JButton start_game = new JButton("Start");
		final ButtonGroup bgrp = new ButtonGroup();
		bgrp.add(isServer);
		bgrp.add(isClient);
		panel.add(isServer); 
		panel.add(isClient);
		//ip_server.setText("127.0.0.1");
		panel.add(ip);
		panel.add(ip_server);
		//port_server.setText("5555");
		panel.add(port);
		panel.add(port_server);
		panel.add(start_game);
   
		socket.setContentPane(panel);
		socket.setVisible(true);
	}
	

}
