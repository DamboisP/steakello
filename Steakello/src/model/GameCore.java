package model;

/**
 * @author Pierre Dambois
 * Groupe 8
 * 2TL1
 */

import java.util.Observable;

import controller.Client;
import controller.Server;

public class GameCore extends Observable{

	private GameBoard gameBoard;
	private int gameMode = 0;
	public int serverOrClient = 0;
	
	public Server server;
	public Client client;
	
	/**
	 * Traite les inputs de l'utitlisateur et fait l'action requise en fonction du 
	 * mode de jeu en cours. Si "gameMode" est égal à 0, on considère que l'input
	 * sert à choisir le mode de jeu, sinon il s'agit d'une coordonnée du joueur
	 * pour placer le jeton. Ensuite on notifie la vue du changement d'état du jeu.
	 * @param input Input de l'utilisateur
	 */
	public void userInput(int input) {
		if(gameMode == 0){
			gameMode = input;
			gameBoard = new GameBoard();
		}
		else if(gameMode == 2 && serverOrClient == 0){
			serverOrClient = input;
		}
		else if(gameMode >= 1){
			gameBoard.setCoords(input-1);
		}
		setChanged();
		notifyObservers();
		
	}

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

	

	
}
