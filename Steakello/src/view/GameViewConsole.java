package view;

import java.util.Observable;
import java.util.Scanner;

import controller.GameController;
import model.Chip;
import model.GameCore;

public class GameViewConsole extends GameView{

	private Scanner scanner;
	
	public GameViewConsole(GameCore gameCore, GameController controller) {
		super(gameCore, controller);
		scanner = new Scanner(System.in);
		new Thread (new InputReader()).start();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(gameCore.getGameMode() == 0){
			displayMenu();
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
	
	private class InputReader implements Runnable{
        public void run() {
            while(true){
                int input = scanner.nextInt();
                controller.setInput(input);
            }
        }
    }

}