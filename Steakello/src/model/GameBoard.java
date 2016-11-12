package model;

public class GameBoard {
	private int size = 8;
	private Chip[][] chipArray;
	private int player;
	private int x;
	private int y;
	private ChipPlacer chipPlacer = new ChipPlacer();
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
	public Chip[][] getChipArray() {
		return chipArray;
	}
	public void setChipArray(Chip[][] chipArray) {
		this.chipArray = chipArray;
	}
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void addChip(int x, int y, int player){
		chipArray[x][y].setSet(true);
		chipArray[x][y].setPlayer(player);;
	}
	public void switchPlayer() {
		if(player == 1){
			player = 2;
		}
		else if(player == 2){
			player = 1;
		}
		
	}
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
	public int getX() {
		return x;
	}
}
