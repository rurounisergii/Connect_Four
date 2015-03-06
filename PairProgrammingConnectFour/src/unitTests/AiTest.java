package unitTests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import connectFour.*;

/**
 * 
 * Contains unit tests for createGameTree(), minimax() and getMoves() methods
 *
 */
public class AiTest {

	/*
	 * Fill up a board in a certain manner, pass the board to a State
	 * and check that State.createGameTree() produces the expected result
	 */
	@Test
	public void createGameTreeTester() {
		Board testerBoard = new Board();
		testerBoard.fillColumn(0, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(1, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(2, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(4, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(5, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(6, Board.NUM_ROWS - 1, Player.RED);
		testerBoard.fillColumn(3, Board.NUM_ROWS -2, Player.YELLOW);
		State testerState = new State(Player.YELLOW, testerBoard, null);
		AI.createGameTree(testerState, 3);
		//check that testerState's children and descendants have been initalized as expected
		assertEquals(2,testerState.getChildren().length); //there should be 2 possible moves from the board
		assertEquals(Player.RED, testerState.getChildren()[0].getPlayer()); //children state should be opposite player's turn
		//check that the children have been initialized as expected
		assertEquals(Player.YELLOW, testerState.getChildren()[0].getBoard().getTile(1, 3));
		assertEquals(null, testerState.getChildren()[0].getBoard().getTile(0, 3));
		assertEquals(null, testerState.getChildren()[0].getBoard().getTile(0, 6));
		assertEquals(Player.YELLOW, testerState.getChildren()[1].getBoard().getTile(0,6));
		assertEquals(null, testerState.getChildren()[1].getBoard().getTile(1,3));
		//check that the children's children, i.e. descendants have been initialized correctly
		assertEquals(2, testerState.getChildren()[0].getChildren().length);
		assertEquals(1, testerState.getChildren()[1].getChildren().length);
		assertEquals(Player.RED, testerState.getChildren()[0].getChildren()[0].getLastMove().getPlayer());
		assertEquals(3, testerState.getChildren()[0].getChildren()[0].getLastMove().getColumn());
		assertEquals(Player.RED, testerState.getChildren()[0].getChildren()[1].getBoard().getTile(0, 6));
		assertEquals(6, testerState.getChildren()[0].getChildren()[1].getLastMove().getColumn());
		//check childrens, childrens, children
		assertEquals(1, testerState.getChildren()[0].getChildren()[0].getChildren().length);
		assertEquals(6, testerState.getChildren()[0].getChildren()[0].getChildren()[0].getLastMove().getColumn());
		assertEquals(1, testerState.getChildren()[0].getChildren()[1].getChildren().length);
		assertEquals(3, testerState.getChildren()[0].getChildren()[1].getChildren()[0].getLastMove().getColumn());
		assertEquals(0, testerState.getChildren()[1].getChildren()[0].getChildren().length);//no children as winning state	
	}
	
	/**
	 * fills up a board in a certain way. Tests that minimax and getMoves get the best possible move
	 */
	@Test
	public void testMiniMaxAndGetMoves(){
		Board testerBoard = new Board();
		testerBoard.fillColumn(0, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(1, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(2, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(4, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(5, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(6, Board.NUM_ROWS - 1, Player.RED);
		testerBoard.fillColumn(3, Board.NUM_ROWS -2, Player.YELLOW);
		AI aiTester = new AI(Player.YELLOW, 3);
		Move[] result = aiTester.getMoves(testerBoard);
		//Based on the way the board has been set up, there should be 1 optimal move
		//where Player.YELLOW inserts into column 3 to block RED from winning in the next round
		assertEquals(1, result.length);
		assertEquals(3, result[0].getColumn());
		
		Board testerBoard2 = new Board();
		testerBoard2.fillColumn(0, Board.NUM_ROWS, Player.RED);
		testerBoard2.fillColumn(1, Board.NUM_ROWS, Player.RED);
		testerBoard2.fillColumn(2, Board.NUM_ROWS, Player.RED);
		testerBoard2.makeMove(new Move(Player.RED, 6));
		AI aiTester2 = new AI(Player.YELLOW, 4);
		Move[] result2 = aiTester2.getMoves(testerBoard2);
		//There should be 1 optimal move for Player.YELLOW which is to insert into
		//column 3 to prevent Player.RED from winning
		assertEquals(3, result2[0].getColumn());	
		assertEquals(1, result2.length);
		
	}
	
	@Test
	public void minimaxTester() {
		Board testerBoard = new Board();
		Game.fillColumn(testerBoard, 0, Board.NUM_ROWS, Player.RED);
		Game.fillColumn(testerBoard, 1, Board.NUM_ROWS, Player.YELLOW);
		Game.fillColumn(testerBoard, 4, Board.NUM_ROWS, Player.RED);
		Game.fillColumn(testerBoard, 5, Board.NUM_ROWS, Player.YELLOW);
		State testerState = new State(Player.YELLOW, testerBoard, null);
		AI.minimax(new AI(Player.YELLOW, 6), testerState);
		testerState.writeToFile();
		AI testerAI = new AI(Player.YELLOW, 6);
		testerAI.getMoves(testerState.getBoard());
		// assertEquals(Player.YELLOW,
		// testerState.getChildren()[0].getBoard().getTile(5, 6));
	
	}


}
