package model;

/**
 * 
 * @author Steffi Baugnies
 * groupe 8
 * 2TL1
 *
 */
public class Chip {
	
	private boolean set;
	private int player;
	
	/**
	 * M�thode Chip fait que la case est vide par d�faut,
	 * le jeton n'est pas plac�e par d�faut
	 */
	public Chip(){
		this.set = false;
	}
	
	/**
	 * M�thode toString
	 * @return la repr�sentation graphique de la case 
	 * qu'elle soit occup�e par le joueur 1, le joueur 2 ou libre
	 */
	public String toString(){
		if(set && player == 1){
			return "[x]";
		}
		else if(set && player == 2){
			return "[o]";
		}
		return "[ ]";
	}
	
	/**
	 * M�thode isSet 
	 * @return renvoie si le jeton est plac�e ou pas
	 */
	public boolean isSet() {
		return set;
	}

	public void setSet(boolean set) {
		this.set = set;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
	
	
}
