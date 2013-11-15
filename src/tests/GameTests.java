package tests;

import static org.junit.Assert.*;


import util.Rectangle;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import coachingTools.Game;
import coachingTools.Player;
import coachingTools.Field;

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
		
		
		game.getTeam1().get(1).pass(game.getTeam1().get(2));
		ArrayList<Player> players = new ArrayList<Player>();
		
		
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
		Rectangle field = game.getGameField().bounds;
		float width = field.width;
		float height = field.height;
		float[] vertices = field.get2dVertices();
		
		
		//check that areas that should be in bounds are
		//check middle of field
		assertTrue(field.contains(width/2, height/2));
		//check corners
		assertTrue(field.contains(0, 0));
		assertTrue(field.contains(width, 0));
		assertTrue(field.contains(0, height));
		assertTrue(field.contains(width, height));
		//check a few miscellaneous points
		assertTrue(field.contains(width/4, height/2));
		assertTrue(field.contains(width/2, height/4));
		assertTrue(field.contains(5, 5));
		assertTrue(field.contains(width - 5, height - 5));
		assertTrue(field.contains(width/4, height/4));
		assertTrue(field.contains(3*width/4, 3*height/4));
		
		//check that areas that shouldn't be in bounds aren't
		//check to the left of the field
		assertFalse(field.contains(-10, height/2));
		//check to the right of the field
		assertFalse(field.contains(width + 10, height/2));
		//check above the field
		assertFalse(field.contains(width/2, -10));
		//check below the field
		assertFalse(field.contains(width/2, height + 10));
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
