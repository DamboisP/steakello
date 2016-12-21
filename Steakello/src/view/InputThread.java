package view;

import java.util.Scanner;

import controller.GameController;

public class InputThread extends Thread {
	public boolean stopThread;
	Scanner scanner;
	private GameController controller;
	public InputThread(GameController controller){
		scanner = new Scanner(System.in);
		this.controller = controller;
	}
	public void run(){
		while(!stopThread){
			if(scanner.hasNextLine()){
				if(controller.getGameCore().getGameMode() != 2){
					controller.setInput(scanner.nextLine());
				}
				else if(controller.getGameCore().getGameMode() == 2){
					if(controller.getGameCore().gameStarted){
						if(controller.getGameCore().serverOrClient == controller.getGameCore().getGameBoard().getPlayer()){
							controller.setInput(scanner.nextLine());
						}
					}
					else{
						controller.setInput(scanner.nextLine());
					}
				}

			}
		}
	}
}
