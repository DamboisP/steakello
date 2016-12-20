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
				controller.setInput(scanner.nextLine());
			}
		}
	}
}
