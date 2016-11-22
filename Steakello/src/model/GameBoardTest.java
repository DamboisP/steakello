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
	}
}
