package model;

/** 
 * 
 * @author Bonhomme Jean-Baptiste
 * Groupe8 2Tl1
 *
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {

	@Test
	public void testIsFull() {
		GameBoard GBTest = new GameBoard();
		assertEquals(false , GBTest.isFull());
	}

	@Test
	public void testScore() {
		GameBoard GBTest = new GameBoard();
		int[] expectedResult = new int[2];
		expectedResult[0] = 2;
		expectedResult[1] = 2;
		assertArrayEquals(expectedResult, GBTest.score());
		expectedResult[0] = 4;
		expectedResult[1] = 1;
		GBTest.addChip(4,2,1);
		GBTest.addChip(4,3,1);
		assertArrayEquals(expectedResult, GBTest.score());
		expectedResult[0] = 3;
		expectedResult[1] = 5;
		GBTest.addChip(4,4,2);
		GBTest.addChip(5,4,2);
		GBTest.addChip(5,3,2);
		GBTest.addChip(5,2,2);
		assertArrayEquals(expectedResult, GBTest.score());
	}
}
