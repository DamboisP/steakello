package model;

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

	
	private Server server;
	private Client client;
	private boolean askingForAddress;
	private boolean gameStarted;
	private int serverOrClient = 0;
	
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
			
			if(serverOrClient == 1){
				server = new Server(this);
				server.start();
			}
			else if(serverOrClient == 2){
				askingForAddress = true;
				client = new Client(this);
				client.start();
			}
		}
		
		else if(gameMode == 2 && serverOrClient == 2 && client.getIpAddress() != null && client.getPort() == 0){
			client.setPort(input);
			client.setReady(true);
		}
		else if(gameMode >= 1){
			gameBoard.setCoords(input-1);
		}

		refreshView();
		
	}
	
	public void userInput(String input){
		client.setIpAddress(input);
		askingForAddress = false;

		refreshView();
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

	public void refreshView(){
		setChanged();
		notifyObservers();
	}
	public Server getServer(){
		return server; 
	}
	
	public void setServer(Server server){
		this.server = server; 
	}
	
	public Client getClient() {
		return client; 
	}
	
	public void setClient(Client client) {
		this.client = client; 
	}
	
	public boolean getAskingForAddress(){
		return askingForAddress;
	}
	
	public void setAskingForAddress(boolean askingForAddress){
		this.askingForAddress = askingForAddress;
	}
	
	public boolean getGameStarted(){
		return gameStarted; 
	}
	
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}
	
	public int getServerOrClient(){
		return serverOrClient;
	}
	
	public void setServerOrClient(int serverOrClient){
		this.serverOrClient = serverOrClient;
	}

	
}
