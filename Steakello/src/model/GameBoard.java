package model;

/** 
 * 
 * @author Bonhomme Jean-Baptiste
 * Groupe8 2Tl1
 *
 */

public class GameBoard {
	private int size = 8;
	private Chip[][] chipArray;
	private int player;
	private int winner;
	private int x;
	private int y;
	private ChipPlacer chipPlacer = new ChipPlacer();
	
	/**
	 * Constructeur GameBoard initialise le plateau de jeu
	 * et place les 4 premiers Chip
	 */
	public GameBoard(){
		chipArray = new Chip[size][size];
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				chipArray[i][j] = new Chip();
			}
		}
		addChip(3,3,1);
		addChip(4,3,2);
		addChip(3,4,2);
		addChip(4,4,1);
		player = 1;
		x = -1;
		y = -1;
	}
	
	/**
	 * Méthode isFull vérifie
	 * si l'array chipArray est plein
	 * @return true si oui 
	 * sinon 
	 * @return false
	 */
	public boolean isFull() {
		int chips = 0;
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				if(chipArray[i][j].isSet()){
					chips++;
				}
			}
		}
		if(chips == Math.pow(size, 2)){
			return true;
		}
	
	return false;
	}
	
	/**
	 * Méthode score met à jour le score de chaque joueur
	 * en comptant le nombre de Chip que possède chaque player
	 * par itération du tableau chipArray
	 * @return le score de chaque joueur 
	 * sous la forme d'un tableau de 2 entiers
	 */
	public int[] score() {
		int[] score = new int[2];
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				if(chipArray[i][j].getPlayer() == 1){
					score[0]++;
				}
				else if(chipArray[i][j].getPlayer() == 2){
					score[1]++;
				}
			}
		}
		return score;
	}

	/**
	 * Méthode setCoords vérifie si Gameboard est plein 
	 * si oui 
	 * compare les deux scores et détermine le gagnant
	 * sinon si
	 * le Gameboard n'est pas plein, continue le tour normalement
	 * sinon si 
	 * x n'est pas attribué, l'attribue
	 * sinon si
	 * x est attribué et y ne l'est pas, attribue y et place le Chip
	 * passe ensuite le tour  et donne la main au joueur adverse
	 */
	public void setCoords(int input) {
		if(isFull() || (!checkForMoves(1) && !checkForMoves(2) )){
			if(score()[0] > score()[1]){
				this.winner = 1;
			}else{
				this.winner = 2;
			}

		} 
		
		else if(x == -1){
			x = input;
		}
		else if(x != -1 && y == -1){
			y = input;
			if(chipPlacer.placeChip(chipArray, size, x, y, player)){
				switchPlayer();
			}

			x = -1;
			y = -1;
		}
	}
	
	/**
	 * Méthode switchPlayer 
	 * Donne la main au joueur adverse quand le tour se termine
	 */
	public void switchPlayer() {

		if(player == 1){
			if(checkForMoves(2)){
				player = 2;
			}
			
		}
		else if(player == 2){
			if(checkForMoves(1)){
				player = 1;
			}
		}

		

		
	}
	
	public boolean checkForMoves(int player){
		int moves = 0;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				 if(chipPlacer.checkPlacementValidity(chipArray, i, j, player)){
					 moves++;
				 }
			}
		}
		if(moves > 0){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	/**
	 * Méthode addChip ajoute une pièce 
	 * dans l'Array chipArray
	 * Attribue cette position au joueur dont le tour est en cours.
	 */
	public void addChip(int x, int y, int player){
		chipArray[x][y].setSet(true);
		chipArray[x][y].setPlayer(player);;
	}
	
	/**
	 * Méthode getChipArray ramène les données de l'Array chipArray
	 * @return un array en 2 dimensions nommé chipArray contenant n steaks
	 */
	public Chip[][] getChipArray() {
		return chipArray;
	}
	
	/**
	 * Méthode setChipArray
	 * @param Chip[][] chipArray un array en deux dimensions
	 * place l'Array ChipArray
	 */
	public void setChipArray(Chip[][] chipArray) {
		this.chipArray = chipArray;
	}
	
	/**
	 * Méthode getPlayer ramène le numéros de player
	 * @return le numéros de player
	 */
	public int getPlayer() {
		return player;
	}
	
	/**
	 * Méthode setPlayer
	 * attribue un numéros à la variable player
	 */
	public void setPlayer(int player) {
		this.player = player;
	}
	
	/**
	 * Méthode getSize
	 * @return la taille size de l'array
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Méthode setSize
	 * attribue une taille à la variable size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/** 
	 * Méthode getX
	 * @return la valeur de x
	 */
	public int getX() {
		return x;
	}
	
	public int getWinner(){
		return winner;
	}
	
	public void setWinner(int winner){
		this.winner = winner; 
	}
}
