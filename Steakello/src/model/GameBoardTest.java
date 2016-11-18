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
	public final void testIsFull() {
		GameBoard GBTest = new GameBoard();
		assertEquals(false , GBTest.isFull());
	}

	@Test
	public final void testScore() {
		GameBoard GBTest = new GameBoard();
		int[] expectedResult = new int[2];
		//int[] result = new int[2];
		//int[] result = GBTest.score();
		//assertNotNull(GBTest.score());
		assertArrayEquals(expectedResult, GBTest.score());
	}
}
