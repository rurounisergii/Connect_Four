package unitTests;

import static org.junit.Assert.*;
import connectFour.*;

import org.junit.*;

/**
 * 
 * Contains tests for initializeChildren() method
 *
 */
public class StateTest {

	@Before
	public void buildUp(){
		String str = "Do something here possibly later";
	}
	
	
	/**
	 * A board is made and 6 of its 7 columns are filled in such a way that neither player has won the game
	 * The only next possible move is Player.YELLOW inserting into the 7th column
	 * Therefore State.initializeChildren() should only return that state
	 */
	@Test
	public void initializeChildrenTest() {
		Board testerBoard = new Board();
		testerBoard.fillColumn(0, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(1, Board.NUM_ROWS, Player.YELLOW);
		testerBoard.fillColumn(2, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(3, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(4, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(5, Board.NUM_ROWS, Player.YELLOW);
		State testerState = new State(Player.YELLOW, testerBoard, null);
		testerState.initializeChildren();
		//only one state is possible:
		assertEquals(Player.YELLOW, testerState.getChildren()[0].getBoard().getTile(5, 6));
		assertEquals(null, testerState.getChildren()[0].getBoard().getTile(4, 6));
		assertEquals(1, testerState.getChildren().length);
	}

	/**
	 * Same as the test above, but this time, the board is set up so there are 2 possible moves
	 * for the next player and therefore 2 children states should be initialised
	 */
	@Test
	public void initializeChildrenTest2() {
		Board testerBoard = new Board();
		testerBoard.fillColumn(0, Board.NUM_ROWS - 1, Player.RED); // a possible move to add to column 0
		testerBoard.fillColumn(1, Board.NUM_ROWS, Player.YELLOW);
		testerBoard.fillColumn(2, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(3, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(4, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(5, Board.NUM_ROWS, Player.YELLOW);
		State testerState = new State(Player.YELLOW, testerBoard, null);
		testerState.initializeChildren();
		//2 possible states:
		assertEquals(2, testerState.getChildren().length);
		assertEquals(Player.YELLOW, testerState.getChildren()[0].getBoard().getTile(0, 0));
		assertEquals(Player.YELLOW, testerState.getChildren()[1].getBoard().getTile(5, 6));
		assertEquals(null, testerState.getChildren()[0].getBoard().getTile(4, 6));
		
	}

}
