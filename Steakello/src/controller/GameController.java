package controller;

import java.net.InetAddress;

import model.GameCore;
import view.GameView;
import controller.Client;


public class GameController {

	private GameCore gameCore;
	private GameView gameView;
	
	public GameController(GameCore gameCore) {
		this.setGameCore(gameCore);
	}
	
	public void setInput(String input) {
		int inputInt;
		/*
		 * Si on est en train de demander l'adresse IP du serveur
		 * l'entrée n'est exceptionnellement pas convertie en entier
		*/
		if(getGameCore().askingForAddress){
			getGameCore().userInput(input);
		}

		//Sinon..
		else{

			if(input != ""){
				try{
					inputInt = Integer.parseInt(input);
					if(getGameCore().getGameMode() == 0){
						if(inputInt >= 1 && inputInt <= 2){
							getGameCore().userInput(inputInt);
						}
					}
					else if(getGameCore().getGameMode() == 2 && getGameCore().serverOrClient == 0){
						if(inputInt >= 1 && inputInt <= 2){
							getGameCore().userInput(inputInt);
						}
					}
					else if(getGameCore().getGameMode() == 2 && getGameCore().serverOrClient == 2 && getGameCore().client.getIpAddress() != null && getGameCore().client.getPort() == 0){
						if(inputInt >= 1 && inputInt <= 66000){
							getGameCore().userInput(inputInt);
						}
					}
					else if(getGameCore().getGameMode() == 2 && getGameCore().serverOrClient == 1 && getGameCore().server.getConnected()){
						if(getGameCore().getGameBoard().getPlayer() == 1){
							getGameCore().server.sendInput(inputInt);
							
						}
						getGameCore().userInput(inputInt);
					}
					else if(getGameCore().getGameMode() == 2 && getGameCore().serverOrClient == 2 && getGameCore().client.getConnected()){
						if(getGameCore().getGameBoard().getPlayer() == 2){
							getGameCore().client.sendInput(inputInt);
						}
						getGameCore().userInput(inputInt);
						
					}
					else if(getGameCore().getGameMode() == 1 || (getGameCore().getGameMode() == 2 && ((getGameCore().serverOrClient == 2 && getGameCore().client.getConnected()) || (getGameCore().serverOrClient == 1 && getGameCore().server.getConnected()))) ){
						if(inputInt >= 1 && inputInt <= getGameCore().getGameBoard().getSize()){
							getGameCore().userInput(inputInt);
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



	public GameCore getGameCore() {
		return gameCore;
	}

	public void setGameCore(GameCore gameCore) {
		this.gameCore = gameCore;
	}

}
