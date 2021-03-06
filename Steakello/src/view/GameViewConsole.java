package view;

import java.net.InetAddress;
import java.util.Observable;

import controller.Client;
import controller.GameController;
import controller.Server;
import model.Chip;
import model.GameCore;

public class GameViewConsole extends GameView{

	private int localPort;
	private String localAddress;
	private InputThread inputThread;
	
	public GameViewConsole(GameCore gameCore, GameController controller) {
		super(gameCore, controller);
		inputThread = new InputThread(controller);
		inputThread.start();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(gameCore.getGameMode() == 0){
			displayMenu();
		}
		
		else if(gameCore.getGameMode() == 2 && gameCore.getServerOrClient() == 0){
			System.out.println("Voulez-vous �tre serveur (1) ou client (2) ?");
		}
		
		else if(gameCore.getGameMode() == 2){
			if(gameCore.getServerOrClient() == 1 && !gameCore.getServer().getConnected()){
				while(!gameCore.getServer().getIsPortSet()){
					System.out.print(".");
				}
				System.out.println("");
				System.out.println("Vous serez le joueur 1 (Steaks Crus)");
				System.out.println("L'adresse locale est: "+ gameCore.getServer().getLocalAddress());
				System.out.println("Le port est: "+ gameCore.getServer().getPort());
				System.out.println("En attente de connexion...");
			}
			else if(gameCore.getServerOrClient() == 2 && gameCore.getClient().getIpAddress() == null){
				System.out.println("Vous serez le joueur 2 (Steaks Cuits)");
				System.out.println("Veuillez entrer l'IP du serveur pour vous connecter: ");
			}
			
			else if(gameCore.getServerOrClient() == 2 && gameCore.getClient().getIpAddress() != null && gameCore.getClient().getPort() == 0){
				System.out.println("Veuillez entrer le port du serveur: ");
			}
			else if(gameCore.getServerOrClient() == 1 && gameCore.getServer().getConnected()){
				displayGame();
			}
			else if(gameCore.getServerOrClient() == 2 && gameCore.getClient().getConnected()){
				displayGame();
			}
		}
		else{
			displayGame();
		}
		
	}

	@Override
	public void displayMenu() {
		System.out.println("Choississez un mode de jeu");
		System.out.println("1 - 2 joueurs local");
		System.out.println("2 - 2 joueurs en ligne");
		
	}

	@Override
	public void displayGame() {
		if(gameCore.getGameBoard().getWinner() == 0){
			Chip[][] chipArray = gameCore.getGameBoard().getChipArray();
			if(gameCore.getGameBoard().getX() == -1){
				System.out.println("---------------------------------");
				System.out.print("[ ][1][2][3][4][5][6][7][8]");
				for(int i = 0; i < chipArray.length; i++){
					System.out.print("\n["+(i+1)+"]");
					for(int j = 0; j <chipArray.length; j++){
						System.out.print(chipArray[j][i]);
					}
				}
				System.out.println("\n---------------------------------");
				System.out.println("C'est au tour du joueur "+ gameCore.getGameBoard().getPlayer());
				System.out.println("X: ");
			}
			else{
				System.out.println("Y: ");
			}
		}
		else{
			System.out.println("Player " + gameCore.getGameBoard().getWinner() + " wins !");
		}
	}

	public void setLocalPort(int port) {
		this.localPort = port;
	}

	public void setLocalAddress(InetAddress localAddress) {
		this.localAddress = localAddress.toString();
		
	}

}
