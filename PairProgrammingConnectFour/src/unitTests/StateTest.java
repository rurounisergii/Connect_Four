package unitTests;

import static org.junit.Assert.*;
import connectFour.*;

import org.junit.*;

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
		Game.fillColumn(testerBoard, 0, Board.NUM_ROWS, Player.RED);
		Game.fillColumn(testerBoard, 1, Board.NUM_ROWS, Player.YELLOW);
		Game.fillColumn(testerBoard, 2, Board.NUM_ROWS, Player.RED);
		Game.fillColumn(testerBoard, 3, Board.NUM_ROWS, Player.RED);
		Game.fillColumn(testerBoard, 4, Board.NUM_ROWS, Player.RED);
		Game.fillColumn(testerBoard, 5, Board.NUM_ROWS, Player.YELLOW);
		State testerState = new State(Player.YELLOW, testerBoard, null);
		testerState.initializeChildren();
		assertEquals(Player.YELLOW, testerState.getChildren()[0].getBoard().getTile(5, 6));
	}

}
