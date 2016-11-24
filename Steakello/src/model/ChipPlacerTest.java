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

	
	/**
	 *  Test testPlaceChip() vérifie le fonctionnement de la méthode PlaceChip()
	 *  Les assertEquals true sont les placements valides donc les jetons sont placées  
	 *  les assertEquals false sont les placements invalides donc le placement n'est pas permis
	 */
	
	@Test
	public void testPlaceChip() {
		ChipPlacer PCtest = new ChipPlacer();
		Chip[][] chipArray = new Chip[8][8];
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				chipArray[i][j] = new Chip();
			}
		}
		// Placement des 4 jetons de base 
		chipArray[3][3].setPlayer(1); 
		chipArray[3][3].setSet(true);
		chipArray[4][3].setPlayer(2);
		chipArray[4][3].setSet(true);
		chipArray[3][4].setPlayer(2);
		chipArray[3][4].setSet(true);
		chipArray[4][4].setPlayer(1);
		chipArray[4][4].setSet(true);
		// Vérifications et placements valides 
		assertEquals(true, PCtest.placeChip(chipArray, 8, 5, 3, 1)); // Vérifie qu'il y a un jeton adversaire vers la gauche
		assertEquals(true, PCtest.placeChip(chipArray, 8, 5, 2, 2)); // vers le bas à gauche
		assertEquals(true, PCtest.placeChip(chipArray, 8, 4, 2, 1)); // vers le bas
		assertEquals(true, PCtest.placeChip(chipArray, 8, 3, 2, 2)); // vers le haut 
		assertEquals(true, PCtest.placeChip(chipArray, 8, 2, 3, 1)); // vers la droite  
		assertEquals(true, PCtest.placeChip(chipArray, 8, 5, 4, 2)); // vers le haut à gauche 
		assertEquals(true, PCtest.placeChip(chipArray, 8, 5, 5, 1)); 
		assertEquals(true, PCtest.placeChip(chipArray, 8, 3, 5, 2)); // vers le haut à droite 
		assertEquals(true, PCtest.placeChip(chipArray, 8, 1, 2, 2)); // vers le bas à droite 
		
		// Vérification de placements invalides 
		assertEquals(false, PCtest.placeChip(chipArray, 8, 3, 1, 2));
		assertEquals(false, PCtest.placeChip(chipArray, 8, 6, 5, 1)); 
		assertEquals(false, PCtest.placeChip(chipArray, 8, 4, 1, 2)); 
		assertEquals(false, PCtest.placeChip(chipArray, 8, 5, 6, 1)); 
		
	}
	
	/**
	 * Test testCheckPlacementValidity () vérfie le fontionnement de la méthode checkPlacementValidity()
	 * Les assertEquals true veulent dire qu'il y a bel et bien un jeton adverse dans une direction autour 
	 * de la case sur laquelle on effecue la vérification
	 * Les assertEquals false signifient qu'il n'y a pas de jetons aux alentours 
	 * et donc que le placement serait invalide
	 */
	@Test
	 public void testCheckPlacementValidity() {
		ChipPlacer CheckPValTest = new ChipPlacer();
		Chip[][] chipArray = new Chip[8][8];
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				chipArray[i][j] = new Chip();
			}
		}
		// Placement des 4 jetons de base 
		chipArray[3][3].setPlayer(1);
		chipArray[3][3].setSet(true);
		chipArray[4][3].setPlayer(2);
		chipArray[4][3].setSet(true);
		chipArray[3][4].setPlayer(2);
		chipArray[3][4].setSet(true);
		chipArray[4][4].setPlayer(1);
		chipArray[4][4].setSet(true);
		// Vérification de placements valides 
		assertEquals(true, CheckPValTest.checkPlacementValidity(chipArray, 4, 2, 1)); // Vérifie qu'il y a un jeton adversaire vers le bas 
		assertEquals(true, CheckPValTest.checkPlacementValidity(chipArray, 5, 4, 2)); // vers la gauche
		assertEquals(true, CheckPValTest.checkPlacementValidity(chipArray, 3, 5, 1)); // vers le haut
		assertEquals(true, CheckPValTest.checkPlacementValidity(chipArray, 2, 3, 2)); // vers la droite
		// Ajout d'un jeton pour pouvoir vérifier les placements en diagonale
		chipArray[3][2].setPlayer(2);
		chipArray[3][2].setSet(true);
		assertEquals(true, CheckPValTest.checkPlacementValidity(chipArray, 2, 2, 1)); // vers le bas à droite
		assertEquals(true, CheckPValTest.checkPlacementValidity(chipArray, 5, 5, 2)); // vers le haut à gauche
		// Ajout d'un jeton pour pouvoir vérifier les placements en diagonale
		chipArray[4][2].setPlayer(1);
		chipArray[4][2].setSet(true);
		assertEquals(true, CheckPValTest.checkPlacementValidity(chipArray, 5, 1, 2)); // vers le bas à gauche 
		assertEquals(true, CheckPValTest.checkPlacementValidity(chipArray, 2, 4, 1)); // vers le haut à droite
		// Vérification de placements invalides 
		assertEquals(false, CheckPValTest.checkPlacementValidity(chipArray, 6, 2, 1)); 
		assertEquals(false, CheckPValTest.checkPlacementValidity(chipArray, 1, 0, 2)); 
		assertEquals(false, CheckPValTest.checkPlacementValidity(chipArray, 2, 6, 1)); 
		assertEquals(false, CheckPValTest.checkPlacementValidity(chipArray, 6, 5, 2)); 
		
	}
	
	/**
	 * Test testCheckForChipToFlip() vérifie le fontionnement de la méthode checkForChipToFlip()
	 * Les assertEquals true assure qu'il y a en effet un jeton à retourner et le retourne
	 * les assertEquals false veulent dire qu'il n'y a pas de jetons à retourner
	 */
	@Test
	public void testCheckForChipToFlip() {
		ChipPlacer CheckChipFlipTest = new ChipPlacer();
		Chip[][] chipArray = new Chip[8][8];
		for(int i = 0; i < chipArray.length; i++){
			for(int j = 0; j < chipArray.length; j++){
				chipArray[i][j] = new Chip();
			}
		}
		// Placement des 4 jetons de base 
		chipArray[3][3].setPlayer(1);
		chipArray[3][3].setSet(true);
		chipArray[4][3].setPlayer(2);
		chipArray[4][3].setSet(true);
		chipArray[3][4].setPlayer(2);
		chipArray[3][4].setSet(true);
		chipArray[4][4].setPlayer(1);
		chipArray[4][4].setSet(true);
		assertEquals(true, CheckChipFlipTest.checkForChipToFlip(chipArray, 2, 4, 1)); // Vérifie qu'il y a un ou plusieurs jetons à retourner vers la droite 
		assertEquals(true, CheckChipFlipTest.checkForChipToFlip(chipArray, 4, 5, 2)); // vers le haut 
		assertEquals(true, CheckChipFlipTest.checkForChipToFlip(chipArray, 5, 3, 1)); // vers la gauche
		assertEquals(true, CheckChipFlipTest.checkForChipToFlip(chipArray, 4, 5, 1)); // vers le bas 
		// Replacement des 4 jetons de base 
		chipArray[4][3].setPlayer(2);
		chipArray[4][3].setSet(true);
		chipArray[3][4].setPlayer(2);
		chipArray[3][4].setSet(true);
		// Changement d'un jeton pour vérification des diagonales 
		chipArray[4][3].setPlayer(1);
		chipArray[4][3].setSet(true);
		assertEquals(true, CheckChipFlipTest.checkForChipToFlip(chipArray, 5, 2, 2)); // vers le bas à gauche
		// Changement d'un jeton pour vérification des diagonales 
		chipArray[3][4].setPlayer(1);
		chipArray[3][4].setSet(true);
		assertEquals(true, CheckChipFlipTest.checkForChipToFlip(chipArray, 2, 5, 2)); // vers le haut à droite 
		// Changement d'un jeton pour vérification des diagonales 
		chipArray[3][3].setPlayer(2);
		chipArray[3][3].setSet(true);
		assertEquals(true, CheckChipFlipTest.checkForChipToFlip(chipArray, 2, 2, 1)); // vers le bas à droite 
		// Changement d'un jeton pour vérification des diagonales 
		chipArray[4][4].setPlayer(2);
		chipArray[4][4].setSet(true);
		assertEquals(true, CheckChipFlipTest.checkForChipToFlip(chipArray, 5, 5, 1)); // vers le haut à gauche
		// Vérification quand il n'y a pas de jetons à retourner 
		assertEquals(false, CheckChipFlipTest.checkForChipToFlip(chipArray, 5, 5, 2));
		assertEquals(false, CheckChipFlipTest.checkForChipToFlip(chipArray, 2, 3, 1));
		assertEquals(false, CheckChipFlipTest.checkForChipToFlip(chipArray, 3, 2, 1));
		assertEquals(false, CheckChipFlipTest.checkForChipToFlip(chipArray, 5, 2, 2));
		
	}
	
	

}
