package model;

/**
 * 
 * @author Steffi Baugnies
 * Groupe 8
 * 2TL1
 * 
 */


import static org.junit.Assert.*;

import org.junit.Test;

public class ChipPlacerTest {

	@Test
	public void testPlaceChip() {
		ChipPlacer PCtest = new ChipPlacer();
		Chip[][] chipArray = new Chip[8][8];
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				chipArray[i][j] = new Chip();
			}
		}
		chipArray[3][3].setPlayer(1);
		chipArray[3][3].setSet(true);
		chipArray[4][3].setPlayer(2);
		chipArray[4][3].setSet(true);
		chipArray[3][4].setPlayer(2);
		chipArray[3][4].setSet(true);
		chipArray[4][4].setPlayer(1);
		chipArray[4][4].setSet(true);
		assertEquals(true, PCtest.placeChip(chipArray , 8, 5, 3, 1));	
	}

	@Test
	public void testCheckPlacementValidity() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckForChipToFlip() {
		fail("Not yet implemented");
	}

}
