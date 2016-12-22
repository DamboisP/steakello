package view;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Rules {
	public Rules(){
		JFrame frame = new JFrame("Rules");
		JPanel panel =new JPanel(); //Panel
		JPanel p1 = new JPanel();
		JPanel newPanel = new JPanel();
		newPanel.add(p1);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JScrollPane scrollPanel = new JScrollPane(newPanel, 
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPanel);  
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		p1.setLayout(fl);
		JLabel label =new JLabel();
		ImageIcon icon = new ImageIcon("src/images/Steakello_Rules.PNG"); 
		label.setIcon(icon);
		p1.add(label);
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
