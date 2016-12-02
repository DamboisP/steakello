package model;

/**
 * 
 * groupe 8
 * 2TL1
 *
 */
public class Chip {
	
	private boolean set;
	private int player;
	
	/**
	 * Méthode Chip fait que la case est vide par défaut,
	 * le jeton n'est pas placée par défaut
	 */
	public Chip(){
		this.set = false;
	}
	
	/**
	 * Méthode toString
	 * @return la représentation graphique de la case 
	 * qu'elle soit occupée par le joueur 1, le joueur 2 ou libre
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
	 * Methode isSet 
	 * @return renvoie si le jeton est plac� ou pas
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
