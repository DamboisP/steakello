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
	 * M�thode isFull v�rifie
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
	 * M�thode score met � jour le score de chaque joueur
	 * en comptant le nombre de Chip que poss�de chaque player
	 * par it�ration du tableau chipArray
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
	 * M�thode setCoords v�rifie si Gameboard est plein 
	 * si oui 
	 * compare les deux scores et d�termine le gagnant
	 * sinon si
	 * le Gameboard n'est pas plein, continue le tour normalement
	 * sinon si 
	 * x n'est pas attribu�, l'attribue
	 * sinon si
	 * x est attribu� et y ne l'est pas, attribue y et place le Chip
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
	 * M�thode switchPlayer 
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
	 * M�thode addChip ajoute une pi�ce 
	 * dans l'Array chipArray
	 * Attribue cette position au joueur dont le tour est en cours.
	 */
	public void addChip(int x, int y, int player){
		chipArray[x][y].setSet(true);
		chipArray[x][y].setPlayer(player);;
	}
	
	/**
	 * M�thode getChipArray ram�ne les donn�es de l'Array chipArray
	 * @return un array en 2 dimensions nomm� chipArray contenant n steaks
	 */
	public Chip[][] getChipArray() {
		return chipArray;
	}
	
	/**
	 * M�thode setChipArray
	 * @param Chip[][] chipArray un array en deux dimensions
	 * place l'Array ChipArray
	 */
	public void setChipArray(Chip[][] chipArray) {
		this.chipArray = chipArray;
	}
	
	/**
	 * M�thode getPlayer ram�ne le num�ros de player
	 * @return le num�ros de player
	 */
	public int getPlayer() {
		return player;
	}
	
	/**
	 * M�thode setPlayer
	 * attribue un num�ros � la variable player
	 */
	public void setPlayer(int player) {
		this.player = player;
	}
	
	/**
	 * M�thode getSize
	 * @return la taille size de l'array
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * M�thode setSize
	 * attribue une taille � la variable size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/** 
	 * M�thode getX
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
