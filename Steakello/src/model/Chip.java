package model;

/**
 * 
 * @author 
 * groupe 8
 * 2TL1
 *
 */
public class Chip {
	
	private boolean set;
	private int player;
	
	/**
	 * La pi�ce n'est pas plac�e par d�faut
	 */
	public Chip(){
		this.set = false;
	}
	
	/**
	 * Retourne la repr�sentation graphique de la pi�ce
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
