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

	/**
	 * Test testIsFull v�rifie si le GameBoard est plein ou non
	 * assertFalse v�rifie si le GameBoard n'est pas plein
	 * assertTrue v�rifie si le GameBoard est plein
	 */
	@Test
	public void testIsFull() {
		GameBoard GBTest = new GameBoard();
		assertFalse(GBTest.isFull());
		//2 boucles for imbriqu�es l'une dans l'autre pour remplir 
		//l'array de Chip automatiquement
		for(int i = 0; i < 8 ; i++) {
			for(int j = 0; j < 8; j++) {
				GBTest.addChip(i, j, 1);
			}
		}
		assertTrue(GBTest.isFull());
	}
	
	/**
	 * Test testScore v�rifie le score retourn�
	 * Compare un array de deux entiers expectedResult(r�sultat attendu)
	 * avec l'array score retourn� par la m�thode
	 */
	@Test
	public void testScore() {
		GameBoard GBTest = new GameBoard();
		int[] expectedResult = new int[2];
		//test 1: score player1 = score player2
		expectedResult[0] = 2;
		expectedResult[1] = 2;
		assertArrayEquals(expectedResult, GBTest.score());
		//test 2: score player1 > score player2
		expectedResult[0] = 4;
		expectedResult[1] = 1;
		GBTest.addChip(4,2,1);
		GBTest.addChip(4,3,1);
		assertArrayEquals(expectedResult, GBTest.score());
		//test 3: score player1 < score player2
		expectedResult[0] = 3;
		expectedResult[1] = 5;
		GBTest.addChip(4,4,2);
		GBTest.addChip(5,4,2);
		GBTest.addChip(5,3,2);
		GBTest.addChip(5,2,2);
		assertArrayEquals(expectedResult, GBTest.score());
	}
	@Test
	public void testcheckForMoves() {
		GameBoard GBTest = new GameBoard();
		//test 1: v�rifie si player1 a un coup possible
		assertTrue(GBTest.checkForMoves(1));
		//test 2: v�rifie si player2 a un coup possible
		GBTest.addChip(4,2,1);
		GBTest.addChip(4,3,1);
		assertTrue(GBTest.checkForMoves(2));
		//test 3: v�rifie si player1 n'a plus de coup possible
		for(int i = 0; i < 8 ; i++) {
			for(int j = 0; j < 8; j++) {
				GBTest.addChip(i, j, 2);
			}
		}
		assertFalse(GBTest.checkForMoves(1));
		//test 4: v�rifie si player2 n'a plus de coup possible
		for(int i = 0; i < 8 ; i++) {
			for(int j = 0; j < 8; j++) {
				GBTest.addChip(i, j, 1);
			}
		}
		assertFalse(GBTest.checkForMoves(2));
	}
}
