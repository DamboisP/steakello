package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import controller.GameController;

public class Menu extends JPanel{

	private GameController controller;
	
	private int windowWidth;
	private int windowHeight;
	
	private Image background;
	private Image logo;
	
	private int logoWidth;
	private int logoHeight;
	
	
	private JButton local2p;
	private JButton online2p;
	private JButton rules;
	
	private int buttonWidth;
	private int buttonHeight;
	
	public Menu(int windowWidth, int windowHeight, GameController controller){
		
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.controller = controller;
		this.setLayout(null);
		try {
			background = ImageIO.read(new File("src/images/background.png"));
			logo = ImageIO.read(new File("src/images/logo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buttonWidth = windowWidth/5;
		buttonHeight = windowWidth/10;
		logoWidth = windowWidth/5;
		logoHeight = windowHeight/7;
		
		ActionListener local2plistener = new ActionListener(){
		    public void actionPerformed(ActionEvent e)
		    {

		        System.out.println(1);
		        controller.setInput(1);
		    }
		};
		ActionListener online2plistener = new ActionListener(){
		    public void actionPerformed(ActionEvent e)
		    {

		        System.out.println(2);
		        controller.setInput(2);
		    }
		};
		ActionListener ruleslistener = new ActionListener(){
			 public void actionPerformed(ActionEvent e)
			 {
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
	             JLabel label =new JLabel();  //créer un label
	             ImageIcon icon = new ImageIcon("src/images/Steakello_Rules.PNG"); 
	             label.setIcon(icon);
	             p1.add(label);  //l'ajoute au panel
	             frame.setContentPane(panel);  //defini le panel de la JFrame
	             frame.setVisible(true); //affiche la JFrame
			 }
		};
		
		local2p = new JButton("Local 2P");
		online2p = new JButton("Online 2P");
		rules = new JButton("Rules");
		local2p.setBounds(windowWidth/2 - buttonWidth/2, windowHeight/5 * 3, buttonWidth, buttonHeight);
		online2p.setBounds(windowWidth/2 - buttonWidth/2, windowHeight/5 * 4, buttonWidth, buttonHeight);
		rules.setBounds(windowWidth/2 - buttonWidth/2, windowHeight/5 * 2, buttonWidth, buttonHeight);
		local2p.addActionListener(local2plistener);
		online2p.addActionListener(online2plistener);
		rules.addActionListener(ruleslistener);
		this.add(local2p);
		this.add(online2p);
		this.add(rules);
	}

	@Override
    public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, windowWidth, windowHeight, null);
		g.drawImage(logo, windowWidth/2 - logoWidth/2, 50, logoWidth, logoHeight, null);
		
	 }
	 
}
