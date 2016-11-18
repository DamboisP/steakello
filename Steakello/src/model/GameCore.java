package model;

import java.util.Observable;

public class GameCore extends Observable{

	private GameBoard gameBoard;
	private int gameMode = 0;

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getGameMode() {
		return gameMode;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}

	public void userInput(int input) {
		if(gameMode == 0){
			gameMode = input;
			gameBoard = new GameBoard();
		}
		else if(gameMode >= 1){
			gameBoard.setCoords(input-1);
		}
		setChanged();
		notifyObservers();
		
	}
	

	
}