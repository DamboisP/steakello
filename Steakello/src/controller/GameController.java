package controller;

import model.GameCore;
import view.GameView;

public class GameController {

	private GameCore gameCore;
	private GameView gameView = null;
	
	public GameController(GameCore gameCore) {
		this.gameCore = gameCore;
	}

	public void setInput(int input) {
		if(gameCore.getGameMode() == 0){
			if(input >= 1 && input <= 2){
				gameCore.userInput(input);
			}
		}
		else if(gameCore.getGameMode() == 2){
			gameCore.userInput(input);
		}
		else if(gameCore.getGameMode() >= 1){
			if(input >= 1 && input <= gameCore.getGameBoard().getSize()){
				gameCore.userInput(input);
			}
		}
	}
	
	public void addView(GameView view){
		this.gameView = view;
	}

}
