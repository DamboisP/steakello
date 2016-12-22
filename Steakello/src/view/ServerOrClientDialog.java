package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.GameController;

public class ServerOrClientDialog extends JDialog implements ActionListener{
	
	public int choice;
	private GameController controller;
	private JLabel ip;
	private JLabel port;
	private JPanel panel;
	private JTextField ip_server;
	private JTextField port_server;
	JFrame dialog;
	
	public ServerOrClientDialog(GameController controller) {
		this.controller = controller;
		dialog = new JFrame("Choix serveur - client");
		dialog.setSize(300, 130);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(null);
		panel = new JPanel();
		JRadioButton server = new JRadioButton("Server");
		JRadioButton client = new JRadioButton("Client");
		JButton confirm = new JButton("Confirmer");

		panel.add(server);
		panel.add(client);
		panel.add(confirm);
		
		ActionListener serverLst = new ActionListener(){
		    public void actionPerformed(ActionEvent e)
		    {
		    	client.setSelected(false);
		    }
		};
		ActionListener clientLst = new ActionListener(){
		    public void actionPerformed(ActionEvent e)
		    {
		    	server.setSelected(false);
		    }
		};
		ActionListener confirmLst = new ActionListener(){
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(controller.getGameCore().serverOrClient == 0){
			    	if(server.isSelected()){
			    		controller.setInput("1");
			    		displayServerDialog();
			    		server.setEnabled(false);
			    		client.setEnabled(false);
			    		confirm.setEnabled(false);
			    	}
			    	else if(client.isSelected()){
			    		controller.setInput("2");
			    		displayClientDialog();
			    		server.setEnabled(false);
			    		client.setEnabled(false);
			    	}
		    	}
		    	else if(controller.getGameCore().serverOrClient == 2){
		    		controller.setInput(ip_server.getText());
		    		controller.setInput(port_server.getText());
		    	}
		    }
		};
		
		server.addActionListener(serverLst);
		client.addActionListener(clientLst);
		confirm.addActionListener(confirmLst);
		dialog.setContentPane(panel);
		dialog.setVisible(true);
	}

	protected void displayServerDialog() {
		while(!controller.getGameCore().server.getIsPortSet()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ip = new JLabel("Adresse IP : " + controller.getGameCore().server.getLocalAddress());
		port = new JLabel("Port : " + controller.getGameCore().server.getPort());
		panel.add(ip);
		panel.add(port);
		panel.revalidate();
		
	}
	protected void displayClientDialog() {

		ip_server = new JTextField(16);
		ip_server.setText("Adresse IP");
		port_server = new JTextField(5);
		port_server.setText("Port");
		panel.add(ip_server);
		panel.add(port_server);

		panel.revalidate();

	}
	public void close(){
		dialog.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
