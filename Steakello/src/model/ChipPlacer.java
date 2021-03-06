package model;


import model.Chip;

/** 
 * 
 * @author Steffi Baugnies
 * Groupe 8 
 * 2TL1
 *
 */
public class ChipPlacer {
	
	private int chipsToFlip[][] = new int[8][8];

	/**
	 * M�thode placeChip place le jeton du joueur
	 * si le placement choisi est legal/valide.
	 * @param chipArray est un tableau de jetons du plateau de jeu
	 * @param boardSize est la taille du plateau de jeu
	 * @param x est la position X du jeton � placer		
	 * @param y est la position Y du jeton � placer
	 * @param player est le joueur dont c'est actuellement le tour
	 * @return true si le placement est accept�, qu'il fait retourner des jetons
	 * false si le placement est refus�
	 */
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
	 * M�thode checkPlacementValidity v�rifie s'il y a un jeton autour de la position choisie
	 * par le joueur pour �viter un placement au milieu de nulle part.
	 * @param chipArray est un tableau de jetons du plateau de jeu
	 * @param boardSize est la taille du plateau de jeu
	 * @param x est la position X du jeton dont on v�rifie la validit�
	 * @param y est la position Y du jeton dont on v�rifie la validit�
	 * @param player est le joueur dont qui tente de placer le jeton
	 * @return true si le jeton que le joueur essaye de placer est autour d'un (ou plusieurs) jetons du joueur adverse
	 * false si il n'y a aucuns jetons du joueur adverse autour de la position choisie
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
	 * M�thode checkForChipToFlip v�rifie, dans chaque direction,
	 * s'il est possible de prendre/retourner certains jetons 
	 * appartenant � (de la couleur de) l'adversaire. 
	 * @param chipArray est un tableau de jetons du plateau de jeu
	 * @param x est la position X du jeton que le joueur veut placer
	 * @param y est la position Y du jeton que le joueur veut placer 
	 * @param player est le joueur dont c'est actuellement le tour
	 * @return true si il y a au moins un jeton adversaire � retourner
	 * false s'il n'y en a pas
	 */
	public boolean checkForChipToFlip(Chip[][] chipArray, int x, int y, int player){
		resetChipsToFlip();
		boolean capturedChip = false;
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				//Vers la droite
				if(i == x+1 && j == y){
					int k = 0;
					while(chipArray[i+k][j].getPlayer() != player && (i+k) < chipArray.length - 1 && chipArray[i+k][j].isSet() ){
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
					while(chipArray[i-k][j].getPlayer() != player && (i-k) > 0 && chipArray[i-k][j].isSet()){
						
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
					while(chipArray[i][j-k].getPlayer() != player && (j-k) > 0 && chipArray[i][j-k].isSet()){
						
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
					while(chipArray[i][j+k].getPlayer() != player && (j+k) < chipArray.length - 1 && chipArray[i][j+k].isSet()){
						
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
					while(chipArray[i+k][j+k].getPlayer() != player && (i+k) < chipArray.length - 1 && (j+k) < chipArray.length - 1 && chipArray[i+k][j+k].isSet()){
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
					while(chipArray[i-k][j+k].getPlayer() != player && chipArray[i-k][j+k].isSet() &&(i-k) > 0 && (j+k) < chipArray.length - 1){
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
					while(chipArray[i+k][j-k].getPlayer() != player && chipArray[i+k][j-k].isSet() && (i+k) < chipArray.length - 1 && (j-k) > 0){
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
					while(chipArray[i-k][j-k].getPlayer() != player && chipArray[i-k][j-k].isSet() && (i-k) > 0 && (j-k) > 0){
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
	 * M�thode fillGameBoard retourne les jetons de l'adversaire
	 * sur base de la variable tampon
	 * qui a �t� remplie au p�alable.
	 * @param chipArray est le tableau de jetons du plateau de jeu
	 * @param player est le joueur qui vient de jouer
	 * */
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
	 * M�thode resetChipstoFlip 
	 * R�tablit la variable tampon des jetons � retourner.
	 */
	private void resetChipsToFlip(){
		for(int i = 0; i < chipsToFlip.length; i++){
			for(int j = 0; j < chipsToFlip.length; j++){
				chipsToFlip[i][j] = 0;
			}
		}
	}
	
	
	/**
	 * M�thode checkIfFlips v�rifie, dans chaque direction,
	 * s'il est possible de prendre/retourner certains jetons 
	 * appartenant � (de la couleur de) l'adversaire. 
	 * Cette m�thode est une copie de checkForChipsToFlip qui ne modifie pas le tableau de jeu. 
	 * Elle a �t� rajout�e pour les cas o� un joueur passe son tour (n'a plus de coups valides)
	 * mais que l'autre joueur peut encore jouer. 
	 * @param chipArray est un tableau de jetons du plateau de jeu
	 * @param x est la position X du jeton que le joueur veut placer
	 * @param y est la position Y du jeton que le joueur veut placer 
	 * @param player est le joueur dont c'est actuellement le tour
	 * @return true si il y a au moins un jeton adversaire � retourner
	 * false s'il n'y en a pas
	 */
	public boolean checkIfFlips(Chip[][] chipArray, int x, int y, int player){
		resetChipsToFlip();
		boolean capturedChip = false;
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				//Vers la droite
				if(i == x+1 && j == y){
					int k = 0;
					while(chipArray[i+k][j].getPlayer() != player && (i+k) < chipArray.length - 1 && chipArray[i+k][j].isSet() ){
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
						capturedChip = true;
					}
				}
				//Vers la gauche
				else if(i == x-1 && j == y){
					int k = 0;
					while(chipArray[i-k][j].getPlayer() != player && (i-k) > 0 && chipArray[i-k][j].isSet()){
						
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
						capturedChip = true;
					}
				}
				//Vers le haut
				else if(i == x && j == y - 1){
					int k = 0;
					while(chipArray[i][j-k].getPlayer() != player && (j-k) > 0 && chipArray[i][j-k].isSet()){
						
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
						capturedChip = true;
					}
				}
				//Vers le bas
				else if(i == x && j == y + 1){
					int k = 0;
					while(chipArray[i][j+k].getPlayer() != player && (j+k) < chipArray.length - 1 && chipArray[i][j+k].isSet()){
						
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
						capturedChip = true;
					}
				}
				//Vers bas droite
				if(i == x+1 && j == y+1){
					int k = 0;
					while(chipArray[i+k][j+k].getPlayer() != player && (i+k) < chipArray.length - 1 && (j+k) < chipArray.length - 1 && chipArray[i+k][j+k].isSet()){
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
						capturedChip = true;
					}
				}
				//Vers bas gauche
				if(i == x-1 && j == y+1){
					int k = 0;
					while(chipArray[i-k][j+k].getPlayer() != player && chipArray[i-k][j+k].isSet() &&(i-k) > 0 && (j+k) < chipArray.length - 1){
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
						capturedChip = true;
					}
				}
				//Vers haut droite
				if(i == x+1 && j == y-1){
					int k = 0;
					while(chipArray[i+k][j-k].getPlayer() != player && chipArray[i+k][j-k].isSet() && (i+k) < chipArray.length - 1 && (j-k) > 0){
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
						capturedChip = true;
					}
				}
				//Vers haut gauche
				if(i == x-1 && j == y-1){
					int k = 0;
					while(chipArray[i-k][j-k].getPlayer() != player && chipArray[i-k][j-k].isSet() && (i-k) > 0 && (j-k) > 0){
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
						capturedChip = true;
					}
				}
				//

			}
		}
		return capturedChip;
	}
	
	
}
