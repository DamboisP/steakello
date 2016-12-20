package model;

import java.net.InetAddress;
import java.nio.ByteBuffer;

/**
 * @author Pierre Dambois
 * Groupe 8
 * 2TL1
 */

import java.util.Observable;

import controller.Client;
import controller.Server;

public class GameCore extends Observable {

	private GameBoard gameBoard;
	private int gameMode = 0;

	
	public Server server;
	public Client client;
	public boolean askingForAddress;
	public int serverOrClient = 0;
	
	/**
	 * Traite les inputs de l'utitlisateur et fait l'action requise en fonction du 
	 * mode de jeu en cours. Si "gameMode" est �gal � 0, on consid�re que l'input
	 * sert � choisir le mode de jeu, sinon il s'agit d'une coordonn�e du joueur
	 * pour placer le jeton. Ensuite on notifie la vue du changement d'�tat du jeu.
	 * @param input Input de l'utilisateur
	 */
	public void userInput(int input) {
		if(gameMode == 0){
			gameMode = input;
			gameBoard = new GameBoard();
		}
		else if(gameMode == 2 && serverOrClient == 0){
			serverOrClient = input;
			
			if(serverOrClient == 1){
				server = new Server();
				server.start();
			}
			else if(serverOrClient == 2){
				askingForAddress = true;
				client = new Client();
				client.start();
			}
		}
		
		else if(gameMode == 2 && serverOrClient == 2 && client.getIpAddress() != null){
			client.port = input;
			client.ready = true;
		}
		else if(gameMode >= 1){
			gameBoard.setCoords(input-1);
		}

		setChanged();
		notifyObservers();
		
	}
	
	public void userInput(String input){
		client.setIpAddress(input);
		askingForAddress = false;
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
