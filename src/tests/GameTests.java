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
		ArrayList<Player> players = game.getTeam1();
		players.addAll(game.getTeam2());
		Player throwingPlayer = players.get(0);
		
		for (int i = 0; i < 100; i++) {
			throwingPlayer.setBall(true);
			throwingPlayer.throwInBall();
			boolean someoneHASSTheRock = false;
			int numPlayersWithBall = 0;
			
			for (Player p : players) {
				if (p.isBall()) {
					someoneHASSTheRock = true;
					numPlayersWithBall++;
				}
			}
			
			assertFalse(throwingPlayer.isBall());
			assertTrue(someoneHASSTheRock);
			assertEquals(numPlayersWithBall, 1);
		}
		
	}
	
	// to test that the game recognizes when the ball/players are out of bound
	@Test
	public void testBounds() {
		Rectangle field = game.getGameField().bounds;
		float width = field.width;
		float height = field.height;
		float[][] vertices = field.get2dVertices();
		
		
		//check that areas that should be in bounds are
		//check middle of field
		assertTrue(field.contains(0, 0));
		//check corners
		//top left corner
		assertTrue(field.contains(vertices[0][0], vertices[0][1]));
		//bottom left corner
		assertTrue(field.contains(vertices[1][0], vertices[1][1]));
		//bottom right corner
		assertTrue(field.contains(vertices[2][0], vertices[2][1]));
		//top right corner
		assertTrue(field.contains(vertices[3][0], vertices[3][1]));
		
		//check each quadrant
		//quadrant 1
		assertTrue(field.contains(width/4, height/4));
		//quadrant 2
		assertTrue(field.contains(-width/4, height/4));
		//quadrant 3
		assertTrue(field.contains(-width/4, -height/4));
		//quadrant 4
		assertTrue(field.contains(width/4, -height/4));
		
		//check that areas that shouldn't be in bounds aren't
		//check to the left of the field
		assertFalse(field.contains(vertices[0][0] - 0.1f, 0));
		//check to the right of the field
		assertFalse(field.contains(vertices[2][0] + 0.1f, 0));
		//check above the field
		assertFalse(field.contains(0, vertices[0][1] + 0.1f));
		//check below the field
		assertFalse(field.contains(0, vertices[1][1] - 0.1f));
	}
	
	// tests to make sure that the field has loaded properly
	@Test
	public void testLoadField() {
		assertNotNull(game.getGameField());
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
