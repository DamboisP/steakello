package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import controller.GameController;
import model.GameBoard;

public class GameBoardView extends JPanel implements MouseListener{
	private int windowWidth;
	private int windowHeight;
	private GameController controller;
	private GameBoard gameBoard;

	//Images

	private Image background;
	private Image border;
	private Image rawSteak;
	private Image cookedSteak;
	
	//Taille des images
	private int cellSize;
	
	//Position des images
	private int gameBoardX;
	private int gameBoardY;
	
	
	private File rawMeatSound = new File("src/sounds/rawMeat.wav");
	private File cookedMeatSound = new File("src/sounds/cookedMeat.wav");
	
	private Font font;
	public GameBoardView(int windowWidth, int windowHeight, GameController controller) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.controller = controller;
		font = new Font("Arial", Font.PLAIN, 30);
		try {
			background = ImageIO.read(new File("src/images/background.png"));
			rawSteak = ImageIO.read(new File("src/images/rawSteak.png"));
			cookedSteak = ImageIO.read(new File("src/images/cookedSteak.png"));
			border = ImageIO.read(new File("src/images/border.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setPositions();
		setSizes();
	}

	@Override
    public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, windowWidth, windowHeight, null);
		
		g.setFont(font);
		if(gameBoard.winner != 0){
			g.drawString("Player " + gameBoard.winner + " wins !", gameBoardX, 60);
		}
		else{
			g.drawString("C'est au tour du joueur "+gameBoard.getPlayer(), gameBoardX, 60);
		}
		g.drawString("Joueur 1: "+gameBoard.score()[0], gameBoardX + (gameBoard.getSize() * cellSize) + 30, windowHeight/2);
		g.drawString("Joueur 2: "+gameBoard.score()[1], gameBoardX + (gameBoard.getSize() * cellSize) + 30, windowHeight/2 + 60);

		
		g.setColor(Color.WHITE);
		g.drawImage(border, gameBoardX, gameBoardY, gameBoardX + gameBoard.getSize()*cellSize, gameBoardY + gameBoard.getSize()*cellSize, 0, 0, border.getWidth(this), border.getWidth(this), this);
	
		for(int i = 0; i < gameBoard.getChipArray().length;i++){
			for(int j = 0; j < gameBoard.getChipArray().length;j++){
				g.setColor(Color.BLACK);
				g.drawRect(gameBoardX + i*cellSize,gameBoardY + j*cellSize, cellSize, cellSize);
				if(gameBoard.getChipArray()[i][j].isSet()){
					if(gameBoard.getChipArray()[i][j].getPlayer() == 1){
						g.drawImage(rawSteak, (gameBoardX + i*cellSize), (gameBoardY + j*cellSize), (gameBoardX + i*cellSize)+cellSize,(gameBoardY + j*cellSize)+cellSize, 0, 0, rawSteak.getWidth(this), rawSteak.getWidth(this), this);
					}
					 else if(gameBoard.getChipArray()[i][j].getPlayer() == 2){
						 g.drawImage(cookedSteak, (gameBoardX + i*cellSize), (gameBoardY + j*cellSize), (gameBoardX + i*cellSize)+cellSize,(gameBoardY + j*cellSize)+cellSize, 0, 0, rawSteak.getWidth(this), rawSteak.getWidth(this), this);  
					 }
				 }
			  }
		  }
	 }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int[] coords = getCoordsFromClick(e.getX(), e.getY());
		if(controller.getGameCore().getGameMode() == 1){
			controller.setInput(Integer.toString(coords[0]+1));
			controller.setInput(Integer.toString(coords[1]+1));
		}
		else if(controller.getGameCore().getGameBoard().getPlayer() == controller.getGameCore().serverOrClient){
			controller.setInput(Integer.toString(coords[0]+1));
			controller.setInput(Integer.toString(coords[1]+1));
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	public void setPositions(){
		gameBoardX = windowWidth/8;
		gameBoardY = 100;
	}
	
	public void setSizes(){
		cellSize = windowWidth/15;
	}
	public int[] getCoordsFromClick(int x, int y){
		int[] coords = new int[2];
			for(int i = 0; i < gameBoard.getChipArray().length; i++){
				for(int j = 0; j < gameBoard.getChipArray().length; j++){
					if(x > gameBoardX + i*cellSize && x < gameBoardX + (i+1)*cellSize){
						if(y > gameBoardY + j*cellSize && y < gameBoardY + (j+1)*cellSize){
							System.out.println("X:"+i+"Y:"+j);
							coords[0] = i;
							coords[1] = j;
						}
					
					}
						
				}
			}
		return coords;
		
	}
	/*
	public void playSound(File sound){
		try {
			Clip audio = AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(sound));
			audio.start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
