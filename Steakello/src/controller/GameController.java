package controller;

import java.net.InetAddress;

import model.GameCore;
import view.GameView;
import controller.Client;

public class GameController {

	private GameCore gameCore;
	private GameView gameView;
	public Client client;
	
	public GameController(GameCore gameCore) {
		this.gameCore = gameCore;
	}

	public void setInput(String input) {
		int inputInt;
		/*
		 * Si on est en train de demander l'adresse IP du serveur
		 * l'entrée n'est exceptionnellement pas convertie en entier
		*/
		System.out.println("Input: " + input);
		if(gameCore.askingForAddress){
			gameCore.userInput(input);
		}

		//Sinon..
		else{

			System.out.println("toastesbis");
			if(input != ""){
				System.out.println("toastes");
				try{
					inputInt = Integer.parseInt(input);
					if(gameCore.getGameMode() == 0){
						if(inputInt >= 1 && inputInt <= 2){
							gameCore.userInput(inputInt);
						}
					}
					else if(gameCore.getGameMode() == 2 && gameCore.serverOrClient == 0){
						if(inputInt >= 1 && inputInt <= 2){
							gameCore.userInput(inputInt);
						}
					}
					else if(gameCore.getGameMode() == 2 && gameCore.serverOrClient == 2 && gameCore.client.getIpAddress() != null){
						if(inputInt >= 1 && inputInt <= 66000){
							gameCore.userInput(inputInt);
						}
					}
					else if(gameCore.getGameMode() == 2 && gameCore.serverOrClient == 1 && gameCore.server.connected){
						if(gameCore.getGameBoard().getPlayer() == 1){
							gameCore.server.sendInput(inputInt);
						}
						gameCore.userInput(inputInt);
					}
					else if(gameCore.getGameMode() == 2 && gameCore.serverOrClient == 2 && gameCore.client.connected){
						if(gameCore.getGameBoard().getPlayer() == 2){
							gameCore.client.sendInput(inputInt);
						}
						System.out.println("toast");
						gameCore.userInput(inputInt);
					}
					else if(gameCore.getGameMode() == 1 || (gameCore.getGameMode() == 2 && ((gameCore.serverOrClient == 2 && gameCore.client.connected) || (gameCore.serverOrClient == 1 && gameCore.server.connected))) ){
						if(inputInt >= 1 && inputInt <= gameCore.getGameBoard().getSize()){
							gameCore.userInput(inputInt);
						}
					}
				}
				catch(NumberFormatException e){
					
				}
				
			}

		}

	}
	
	public void addView(GameView view){
		this.gameView = view;
	}

	public void setInput(int inputInt) {
		if(inputInt >= 1 && inputInt <= gameCore.getGameBoard().getSize()){
			gameCore.userInput(inputInt);
		}
		
	}

}
