package controller;

import java.net.InetAddress;

import model.GameCore;
import view.GameView;
import controller.Client;

public class GameController {

	private GameCore gameCore;
	private GameView gameView = null;
	public Client client;
	
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
			System.out.println(input +" test"); // test	
			gameCore.userInput(input);
			if(input != 2 && input != 1) {
			try {
					// Convert from integer to an IPv4 address
					InetAddress ipAddr = InetAddress.getByName(Integer.toString(input));
					//String address = ipAddr.getHostAddress();
					System.out.println(ipAddr);	
					client.setIpAddress(ipAddr);
					} catch (Exception e) {
					    e.printStackTrace();
					}
			}
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
