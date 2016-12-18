package model;

import java.net.InetAddress;
import java.net.UnknownHostException;

import controller.Client;

/**
 * Groupe 8
 * 2TL1
 */

import controller.GameController;
import controller.Server;
import view.GameViewConsole;
import view.GameViewGUI;

public class Steakello {

	public static void main(String[] args) {

		GameCore gameCore = new GameCore();
		
		GameController consoleController = new GameController(gameCore);
		GameController GUIController = new GameController(gameCore);
		
		GameViewConsole gameViewConsole = new GameViewConsole(gameCore, consoleController);
		GameViewGUI gameViewGUI = new GameViewGUI(gameCore, consoleController);
		
		consoleController.addView(gameViewConsole);
		GUIController.addView(gameViewGUI);
		
		gameViewConsole.displayMenu();
		gameViewGUI.displayMenu();
		
		Server server = new Server(consoleController);
		server.start();
		Client client = null;
		try {
			while(!server.isPortSet){
				try {
					client.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(server.isPortSet){
				client = new Client(InetAddress.getLocalHost(), server.getPort());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		client.start();
		
		gameViewConsole.setLocalAddress(server.localAddress);
		gameViewConsole.setLocalPort(server.getPort());

		
	}

}
