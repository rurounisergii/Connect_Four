package unitTests;

import static org.junit.Assert.*;
import org.junit.*;
import connectFour.*;

/**
 * 
 * Contains unit tests for makeMove() and getPossibleMoves() methods
 *
 */
public class BoardTest {

	@Test
	public void makeMoveTest(){
		//make an empty board
		Board testerBoard = new Board();
		//make a move which will be applied to the board using makeMove()
		Move firstMove = new Move(Player.RED, 3);
		testerBoard.makeMove(firstMove);
		//check that the move has been made
		assertEquals(Player.RED, testerBoard.getTile(5, 3));
		//do the same again with 2 other moves - to check multiple moves can be made
		Move secondMove = new Move(Player.YELLOW, 5);
		testerBoard.makeMove(secondMove);
		assertEquals(Player.YELLOW, testerBoard.getTile(5, 5));
		Move thirdMove = new Move(Player.RED, 3); //add a piece ontop of another piece in column 3
		testerBoard.makeMove(thirdMove);
		assertEquals(Player.RED, testerBoard.getTile(4,3));
	}
	@Test (expected = IllegalArgumentException.class)
	public void makeMoveOnFullColumnTest(){
		Board testerBoard = new Board();
		testerBoard.fillColumn(2, Board.NUM_ROWS, Player.RED); //fill up column 2
		Move errorMove = new Move(Player.RED, 2); 
		testerBoard.makeMove(errorMove); // make a move to add to the filled up column, should be an exception
	}
	
	@Test
	public void getPossibleMovesTest(){
		
	}

}
