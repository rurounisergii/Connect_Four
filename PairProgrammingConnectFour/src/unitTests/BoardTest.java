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
		//make a move which will be applied to the board using mskeMove()
		Move firstMove = new Move(Player.RED, 3);
		testerBoard.makeMove(firstMove);
		//check that the move has been made
		assertEquals(Player.RED, testerBoard.getTile(5, 3));
		//do the same again - check multiple moves can be made
		Move secondMove = new Move(Player.YELLOW, 5);
		testerBoard.makeMove(secondMove);
		assertEquals(Player.YELLOW, testerBoard.getTile(5, 5));
		Move thirdMove = new Move(Player.RED, 3);
		testerBoard.makeMove(thirdMove);
		assertEquals(Player.RED, testerBoard.getTile(4,3));
	}

}
