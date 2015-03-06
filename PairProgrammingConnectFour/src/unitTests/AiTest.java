package unitTests;

import static org.junit.Assert.*;
import org.junit.Test;
import connectFour.*;

/**
 * 
 * Contains unit tests for createGameTree(), minimax() and getMoves() methods
 *
 */
public class AiTest {

	@Test
	public void createGameTreeTester() {
		Board testerBoard = new Board();
		testerBoard.fillColumn(0, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(1, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(2, Board.NUM_ROWS -1, Player.RED);
		testerBoard.fillColumn(4, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(5, Board.NUM_ROWS, Player.RED);
		testerBoard.fillColumn(6, Board.NUM_ROWS, Player.RED);
		
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
		fail("Not yet implemented");
	}
	@Test
	public void getMovesTest(){
		
	}

}
