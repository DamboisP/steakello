package model;


import model.Chip;

/** 
 * 
 * @author Pierre
 *
 */
public class ChipPlacer {
	
	private int chipsToFlip[][] = new int[8][8];

	public boolean placeChip(Chip[][] chipArray,int boardSize, int x, int y, int player){
		if(checkPlacementValidity(chipArray, x, y, player)){
			if(checkForChipToFlip(chipArray, x, y, player)){
				chipArray[x][y].setSet(true);
				chipArray[x][y].setPlayer(player);
				return true;
			}
		}
		return false;
	}
	/**
	 * Vérifie si il y a au moins un jeton autour de la position
	 * pour éviter qu'un joueur en place un au milieu de nulle part.
	 * @param chipArray Tableau de jetons du plateau de jeu
	 * @param boardSize Taille du plateau de jeu
	 * @param x Position X du jeton pour lequel on vérifie si elle est valide
	 * @param y Position Y du jeton pour lequel on vérifie si elle est valide
	 * @param player 
	 * @return
	 */
	public boolean checkPlacementValidity(Chip[][] chipArray, int x, int y, int player){

		if(x >= 0 && x <= chipArray.length && y >= 0 && y <= chipArray.length && !chipArray[x][y].isSet()){
			for(int i = 0; i < chipArray.length; i++){
				for(int j = 0; j < chipArray.length; j++){
					if(chipArray[i][j].isSet() && chipArray[i][j].getPlayer() != player){
						if((i == x+1 || i == x-1) && (j == y+1 || j == y-1)){
							return true;
						}
						else if((j == y+1 || j == y-1) && i == x){
							return true;
						}
						else if((i == x+1 || i == x-1) && j == y){
							return true;
						}


					}
				}
			}
			
		}
		return false;
	}
	
	
	/**
	 * Permet de vérifier dans chaque direction s'il est possible de
	 * prendre des jetons à l'adversaire.
	 * @param chipArray Tableau de jetons du plateau de jeu
	 * @param x Position X du jeton placé
	 * @param y Position Y du jeton plaé	 
	 * @param player Joueur qui a jouÃ©Â Â¬e coup
	 * @return Si il est possible de prendre au moins un jeton à l'adversaire
	 */
	public boolean checkForChipToFlip(Chip[][] chipArray, int x, int y, int player){
		resetChipsToFlip();
		boolean capturedChip = false;
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				//Vers la droite
				if(i == x+1 && j == y){
					int k = 0;
					while(chipArray[i+k][j].getPlayer() != player && (i+k) < chipArray.length - 1){
						chipsToFlip[i+k][j] = 1;
						k++;
					}
					if(chipArray[i+k][j].getPlayer() != player){
						resetChipsToFlip();
					}
					else if(k == 0){
						resetChipsToFlip();
					}
					else{
						fillGameBoard(chipArray, player);
						capturedChip = true;
					}
				}
				//Vers la gauche
				else if(i == x-1 && j == y){
					int k = 0;
					while(chipArray[i-k][j].getPlayer() != player && (i-k) > 1){
						
						chipsToFlip[i-k][j] = 1;
						k++;
					}
					if(chipArray[i-k][j].getPlayer() != player){
						resetChipsToFlip();
					}
					else if(k == 0){
						resetChipsToFlip();
					}
					else{
						fillGameBoard(chipArray, player);
						capturedChip = true;
					}
				}
				//Vers le haut
				else if(i == x && j == y - 1){
					int k = 0;
					while(chipArray[i][j-k].getPlayer() != player && (j-k) > 1){
						
						chipsToFlip[i][j-k] = 1;
						k++;
					}
					if(chipArray[i][j-k].getPlayer() != player){
						resetChipsToFlip();
					}
					else if(k == 0){
						resetChipsToFlip();
					}
					else{
						fillGameBoard(chipArray, player);
						capturedChip = true;
					}
				}
				//Vers le bas
				else if(i == x && j == y + 1){
					int k = 0;
					while(chipArray[i][j+k].getPlayer() != player && (j+k) < chipArray.length - 1){
						
						chipsToFlip[i][j+k] = 1;
						k++;
					}
					if(chipArray[i][j+k].getPlayer() != player){
						resetChipsToFlip();
					}
					else if(k == 0){
						resetChipsToFlip();
					}
					else{
						fillGameBoard(chipArray, player);
						capturedChip = true;
					}
				}
				//Vers bas droite
				if(i == x+1 && j == y+1){
					int k = 0;
					while(chipArray[i+k][j+k].getPlayer() != player && (i+k) < chipArray.length - 1 && (j+k) < chipArray.length - 1){
						chipsToFlip[i+k][j+k] = 1;
						k++;
					}
					if(chipArray[i+k][j+k].getPlayer() != player){
						resetChipsToFlip();
					}
					else if(k == 0){
						resetChipsToFlip();
					}
					else{
						fillGameBoard(chipArray, player);
						capturedChip = true;
					}
				}
				//Vers bas gauche
				if(i == x-1 && j == y+1){
					int k = 0;
					while(chipArray[i-k][j+k].getPlayer() != player && (i-k) > 1 && (j+k) < chipArray.length - 1){
						chipsToFlip[i-k][j+k] = 1;
						k++;
					}
					if(chipArray[i-k][j+k].getPlayer() != player){
						resetChipsToFlip();
					}
					else if(k == 0){
						resetChipsToFlip();
					}
					else{
						fillGameBoard(chipArray, player);
						capturedChip = true;
					}
				}
				//Vers haut droite
				if(i == x+1 && j == y-1){
					int k = 0;
					while(chipArray[i+k][j-k].getPlayer() != player && (i+k) < chipArray.length - 1 && (j-k) > 1){
						chipsToFlip[i+k][j-k] = 1;
						k++;
					}
					if(chipArray[i+k][j-k].getPlayer() != player){
						resetChipsToFlip();
					}
					else if(k == 0){
						resetChipsToFlip();
					}
					else{
						fillGameBoard(chipArray, player);
						capturedChip = true;
					}
				}
				//Vers haut gauche
				if(i == x-1 && j == y-1){
					int k = 0;
					while(chipArray[i-k][j-k].getPlayer() != player && (i-k) > 1 && (j-k) > 1){
						chipsToFlip[i-k][j-k] = 1;
						k++;
					}
					if(chipArray[i-k][j-k].getPlayer() != player){
						resetChipsToFlip();
					}
					else if(k == 0){
						resetChipsToFlip();
					}
					else{
						fillGameBoard(chipArray, player);
						capturedChip = true;
					}
				}
				//

			}
		}
		return capturedChip;
	}
	
	
	
	/**
	 * Sert à retourner les jetons du plateau sur base de la 
	 * variable tampons qui a été remplit au péalable.
	 * 
	 * @param chipArray Tableau de jetons du plateau de jeu
	 * @param player Joueur ayant joué 	 */
	private void fillGameBoard(Chip[][] chipArray, int player){
		for(int i = 0; i < chipsToFlip.length; i++){
			for(int j = 0; j < chipsToFlip.length; j++){
				if(chipsToFlip[i][j] == 1){
						chipArray[i][j].setSet(true);
						chipArray[i][j].setPlayer(player);
				}
			}
		}
	}
	
	/**
	 * Rétablit la variable tampons des jetons à retourner.
	 */
	private void resetChipsToFlip(){
		for(int i = 0; i < chipsToFlip.length; i++){
			for(int j = 0; j < chipsToFlip.length; j++){
				chipsToFlip[i][j] = 0;
			}
		}
	}
	
}
