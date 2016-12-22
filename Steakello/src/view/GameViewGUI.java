package view;

import java.util.Observable;

import javax.swing.JFrame;

import controller.GameController;
import model.GameCore;

public class GameViewGUI extends GameView {
	private int windowWidth = 800;
	private int windowHeight = 600;

	private JFrame window;
	private Menu menu;
	private GameBoardView gameBoardView;
	ServerOrClientDialog srvOrClientDialog;
	public GameViewGUI(GameCore gameCore, GameController controller) {
		super(gameCore, controller);
		window = new JFrame();
		menu = new Menu(windowWidth, windowHeight, controller);
		gameBoardView = new GameBoardView(windowWidth, windowHeight, controller);
		window.setSize(windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Steakello");
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.add(menu);

		window.addMouseListener(gameBoardView);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(gameCore.getGameMode() == 0){
			menu.repaint();
		}
		else if(gameCore.getGameMode() == 2 && gameCore.getServerOrClient() == 0){
			if(srvOrClientDialog == null){
				srvOrClientDialog = new ServerOrClientDialog(controller);
			}
		}
		else if(gameCore.getGameMode() == 2 && gameCore.getServerOrClient() == 2 && gameCore.getClient().getConnected()){
			menu.setVisible(false);
			srvOrClientDialog.close();
			window.add(gameBoardView);
			gameBoardView.setGameBoard(gameCore.getGameBoard());
			gameBoardView.repaint();
		}
		else if(gameCore.getGameMode() == 2 && gameCore.getServerOrClient() == 1 && gameCore.getServer().getConnected()){
			menu.setVisible(false);
			srvOrClientDialog.close();
			window.add(gameBoardView);
			gameBoardView.setGameBoard(gameCore.getGameBoard());
			gameBoardView.repaint();
		}
		else if(gameCore.getGameMode() == 1){
			menu.setVisible(false);
			window.add(gameBoardView);
			gameBoardView.setGameBoard(gameCore.getGameBoard());
			gameBoardView.repaint();
		}
	}

	@Override
	public void displayMenu() {
		
		
	}

	@Override
	public void displayGame() {
		// TODO Auto-generated method stub
		
	}
	

}
