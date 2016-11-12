package view;

import java.util.Observer;

import controller.GameController;
import model.GameCore;

public abstract class GameView implements Observer{
	
	protected GameCore gameCore;
	protected GameController controller;
	
	GameView(GameCore gameCore, GameController controller){
		this.gameCore = gameCore;
		this.controller = controller;
		gameCore.addObserver(this);
	}
	
	public abstract void displayMenu();
	public abstract void displayGame();
	
}
