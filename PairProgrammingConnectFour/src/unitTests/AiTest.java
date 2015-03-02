package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import connectFour.AI;
import connectFour.Board;
import connectFour.Game;
import connectFour.Player;
import connectFour.State;

public class AiTest {

	@Test
	public void createGameTreeTester() {
		Board testerBoard = new Board();
		State testerState = new State(Player.YELLOW, testerBoard, null);
		AI.createGameTree(testerState, 4);
		testerState.writeToFile();
		// assertEquals(Player.YELLOW,
		// testerState.getChildren()[0].getBoard().getTile(5, 6));
		fail("Not yet implemented");
	}
	

}
