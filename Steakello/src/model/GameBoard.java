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
	private int x;
	private int y;
	private ChipPlacer chipPlacer = new ChipPlacer();
	
	/**
	 * Constructeur GameBoard initialise le plateau de jeux
	 * et place les 4 premieres pi�ces
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
	 * M�thode setCoords
	 * Attribue la coordonn�e � la pi�ce du joueur dont le tour est en cours
	 * Donne la main � l'autre joueur quand le tour se termine
	 */
	public void setCoords(int input) {
		if(isFull()){
			if(score()[0] > score()[1]){
				System.out.println("Player 1 wins");
			}else{
				System.out.println("Player 2 wins");
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
			player = 2;
		}
		else if(player == 2){
			player = 1;
		}
		
	}
	
	/**
	 * M�thode addChip ajoute une pi�ce 
	 * dans l'Array chipArray
	 * V�rifie si la position dans l'array chipArray est libre
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
}
