package controller;

import model.GameCore;
import view.GameView;


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
		if(getGameCore().getAskingForAddress()){
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
					else if(getGameCore().getGameMode() == 2 && getGameCore().getServerOrClient() == 0){
						if(inputInt >= 1 && inputInt <= 2){
							getGameCore().userInput(inputInt);
						}
					}
					else if(getGameCore().getGameMode() == 2 && getGameCore().getServerOrClient() == 2 && getGameCore().getClient().getIpAddress() != null && getGameCore().getClient().getPort() == 0){
						if(inputInt >= 1 && inputInt <= 66000){
							getGameCore().userInput(inputInt);
						}
					}
					else if(getGameCore().getGameMode() == 2 && getGameCore().getServerOrClient() == 1 && getGameCore().getServer().getConnected()){
						if(getGameCore().getGameBoard().getPlayer() == 1){
							getGameCore().getServer().sendInput(inputInt);
							
						}
						getGameCore().userInput(inputInt);
					}
					else if(getGameCore().getGameMode() == 2 && getGameCore().getServerOrClient() == 2 && getGameCore().getClient().getConnected()){
						if(getGameCore().getGameBoard().getPlayer() == 2){
							getGameCore().getClient().sendInput(inputInt);
						}
						getGameCore().userInput(inputInt);
						
					}
					else if(getGameCore().getGameMode() == 1 || (getGameCore().getGameMode() == 2 && ((getGameCore().getServerOrClient() == 2 && getGameCore().getClient().getConnected()) || (getGameCore().getServerOrClient() == 1 && getGameCore().getServer().getConnected()))) ){
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
