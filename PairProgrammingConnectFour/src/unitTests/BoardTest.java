package unitTests;

import static org.junit.Assert.*;

import java.util.Arrays;

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

	@Test public void getPossibleMoves(){
		Board testerBoard = new Board();
		//fully fill up column 0 and 6
		testerBoard.fillColumn(0, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(6,Board.NUM_ROWS, Player.RED);
		//As 2 of the 7 columns have been filled, Player.RED should have 5 possible moves,
		//i.e. inserting into columns 1,2,3,4 or 5
		Move[] expected = {new Move(Player.RED, 1), new Move(Player.RED, 2), new Move(Player.RED, 3), new Move(Player.RED, 4), new Move(Player.RED, 5)};
		Move[] result = testerBoard.getPossibleMoves(Player.RED);
		assertEquals(expected.length, result.length); //there should be 5 possible moves
		//determine if they are the expected moves:
		boolean sameArrays = true;
		for (int x = 0; x < expected.length; x++){
			if (expected[x].getColumn() != result[x].getColumn() ||expected[x].getPlayer() != result[x].getPlayer()){
				sameArrays = false;
			}
		}
		assertEquals(true, sameArrays);
	
	}
	
	@Test
	public void getPossibleMovesTestWithNoPossibleMoves(){
		Board testerBoard = new Board();
		testerBoard.fillColumn(1, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(2, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(3, Board.NUM_ROWS, Player.RED);
		//3 columns have been filled up. There are 3 red pieces at the bottom
		//so by adding one more red piece to column 4, red wins
		testerBoard.makeMove(new Move(Player.RED, 4));
		//As there is a winner, Player.YELLOW has no possible moves
		//and getPossibleMoves should return an empty array
		assertEquals(0, (testerBoard.getPossibleMoves(Player.YELLOW)).length);
		
	}
	@Test
	public void getPossibleMovesTestWithBoardFull(){
		Board testerBoard = new Board();
		//fill up the whole board
		testerBoard.fillColumn(0, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(1, Board.NUM_ROWS, Player.RED);
		//the board is filled up in such a way that there are no winners
		testerBoard.fillColumn(2, Board.NUM_ROWS - 1, Player.RED); 
		testerBoard.fillColumn(3, Board.NUM_ROWS, Player.YELLOW);
		testerBoard.makeMove(new Move(Player.YELLOW, 2));
		testerBoard.fillColumn(4, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(5, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(6, Board.NUM_ROWS, Player.RED);
		//getPossibleMoves should return an empty array as the board is full
		assertEquals(0, testerBoard.getPossibleMoves(Player.YELLOW).length);
	}
}
