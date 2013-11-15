package tests;

import static org.junit.Assert.*;


import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import coachingTools.Game;
import coachingTools.Player;

public class GameTests {
	Game game;
	
	
	@Before
	public void setUp(){
		game = new Game();
		
		
	}
	
	// to test moving to various locations on the field
	@Test
	public void testMove() { 
		fail("Not yet implemented");
	}
	
	// to test passing the ball to other players
	@Test
	public void testPass() {
		
		int ballCount = 0;
		int noBallCount = 0;
		
		game.getTeam1().get(5).setBall(true);
		game.getTeam1().get(5).pass(game.getTeam1().get(0));
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		players.addAll(game.getTeam1());
		players.addAll(game.getTeam2());
		
		for(Player p: players){
			if(p.isBall()){
				ballCount++;
			}else{
				noBallCount++;
			}
		}
		
		assertTrue(game.getTeam1().get(0).isBall());
		assertEquals(ballCount, 1);
		assertEquals(noBallCount, (Game.getNumberOfPlayers()*2)-1);
		for(int i=1; i < Game.getNumberOfPlayers(); i++){
			assertFalse(game.getTeam1().get(i).isBall());
			assertFalse(game.getTeam2().get(i).isBall());
		}
		
	}
	
	// to test that strategy paths are chosen randomly
	@Test
	public void testRandomPath() { 
		fail("Not yet implemented");
	}
	
	// to test throw in functionality is working as expected
	@Test
	public void testThrowIn() { 
		fail("Not yet implemented");
	}
	
	// to test that the game recognizes when the ball/players are out of bound
	@Test
	public void testBounds() {
		//int rowOutOfBoundsLeft = game.getField().getLeftBound() - 1;
		//int columnOutOfBoundsAbove = game.getField().getLowerBound() -1;
		//int rowOutOfBoundsRight = game.getField().getRightBound() + 1;
		//int columnOutOfBoundsBelow = game.getField().getUpperBound() + 1;
		Rectangle field = new Rectangle(game.getField());
		float width = field.getWidth();
		float length = field.getLength();
		
		//check that areas that should be in bounds are
		//check middle of field
		assertTrue(field.contains(width/2, length/2));
		//check corners
		assertTrue(field.contains(0, 0));
		assertTrue(field.contains(width, 0));
		assertTrue(field.contains(0, length));
		assertTrue(field.contains(width, length));
		//check a few miscellaneous points
		assertTrue(field.contains(width/4, length/2));
		assertTrue(field.contains(width/2, length/4));
		assertTrue(field.contains(5, 5));
		assertTrue(field.contains(width - 5, length - 5));
		assertTrue(field.contains(width/4, length/4));
		assertTrue(field.contains(3*width/4, 3*length/4));
		
		//check that areas that shouldn't be in bounds aren't
		//check to the left of the field
		assertFalse(field.contains(-10, length/2));
		//check to the right of the field
		assertFalse(field.contains(width + 10, length/2));
		//check above the field
		assertFalse(field.contains(width/2, -10));
		//check below the field
		assertFalse(field.contains(width/2, length + 10));
	}
	
	// tests to make sure that the field has loaded properly
	@Test
	public void testLoadField() { 
		fail("Not yet implemented");
	}
	
	// tests that the players have been loaded
	@Test
	public void testLoadPlayerImage() {
		fail("Not yet implemented");
	}
	
	// tests the Game can make a formation of players 
	@Test
	public void testMakeFormation() { 
		fail("Not yet implemented");
	}
	
	// tests to see if the player will know if there in the penalty area, goal area, ect.
	@Test
	public void testLocationFinder() { 
		fail("Not yet implemented");
	}
	
	//tests that the program knows when a goal has been made
	@Test
	public void testGoal() {
		fail("Not yet implemented");
	}
	
	
}
