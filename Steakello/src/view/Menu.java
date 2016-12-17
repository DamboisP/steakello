package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

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
	             frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	             try 
	             {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	             } catch (Exception e1) {
	                e1.printStackTrace();
	             }
				 JPanel panel = new JPanel();
	             panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
	             panel.setOpaque(true);
	             JTextArea textArea = new JTextArea(35, 75);
	             textArea.setWrapStyleWord(true);
	             textArea.setEditable(false);
	             textArea.setFont(Font.getFont(Font.SANS_SERIF));
				 JScrollPane scroller = new JScrollPane(textArea);
	             scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	             scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	             panel.add(scroller);
	             frame.getContentPane().add(BorderLayout.CENTER, panel);
	             frame.pack();
	             frame.setLocationByPlatform(true);
	             frame.setVisible(true);
	             frame.setResizable(true);
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
