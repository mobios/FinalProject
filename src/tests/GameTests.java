package tests;

import static org.junit.Assert.*;
import util.Rectangle;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import coachingTools.Game;
import coachingTools.Player;
import coachingTools.Field;
import coachingTools.SoccerGame;
import coachingTools.SoccerPlayer;
import coachingTools.Team;

public class GameTests {
	Game game;
	
	@Before
	public void setUp(){
		game = new SoccerGame();
		
		
	}
	
	// to test moving to various locations on the field
	@Test
	public void testMove() {
		
		Player testPlayer = new SoccerPlayer(10);
		testPlayer.setX((float) 1.34234);
		testPlayer.setY((float) 2.23223);
		game.getTeam1().getPlayers().get(0).move(0, 0, 10);// move the the origin
		assertTrue((float)-0.0001 <= testPlayer.getX() && testPlayer.getX() <= (float)0.0001);
		assertTrue((float)-0.0001 <= testPlayer.getY() && testPlayer.getY() <= (float)0.0001);
		
	}
	
	// to test passing the ball to other players
	@Test
	public void testPass() {
		
		int ballCount = 0;
		int noBallCount = 0;
		
		game.getTeam1().getPlayers().get(5).setBall(true);
		game.getTeam1().getPlayers().get(5).pass(game.getTeam1().getPlayers().get(0));
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		players.addAll(game.getTeam1().getPlayers());
		players.addAll(game.getTeam2().getPlayers());
		
		for(Player p: players){
			if(p.hasBall()){
				ballCount++;
			}else{
				noBallCount++;
			}
		}
		assertTrue(game.getTeam1().getPlayers().get(0).hasBall());
		assertEquals(ballCount, 1);
		assertEquals(noBallCount, (Team.getNumberOfPlayers()*2)-1);
		for(int i=1; i < Team.getNumberOfPlayers(); i++){
			assertFalse(game.getTeam1().getPlayers().get(i).hasBall());
			assertFalse(game.getTeam2().getPlayers().get(i).hasBall());
		}
		
	}
	
	// to test that strategy paths are chosen randomly
	@Test
	public void testRandomPath() { 
		game.setDebugSeed();
		int gaCount=0, paCount=0, ccCount=0, lhCount=0, rhCount=0;
		for(int i=0; i < 200; i++){
				
		}
	}
	
	// to test throw in functionality is working as expected
	@Test
	public void testThrowIn() {
		ArrayList<Player> players = game.getTeam1().getPlayers();
		players.addAll(game.getTeam2().getPlayers());
		Player throwingPlayer = players.get(0);
		
		for (int i = 0; i < 100; i++) {
			throwingPlayer.setBall(true);
			throwingPlayer.throwInBall();
			boolean someoneHASSTheRock = false;
			int numPlayersWithBall = 0;
			
			for (Player p : players) {
				if (p.hasBall()) {
					someoneHASSTheRock = true;
					numPlayersWithBall++;
				}
			}
			
			assertFalse(throwingPlayer.hasBall());
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
		Rectangle field = game.getGameField().bounds;
		for (Player p : game.getAllPlayers()) {
			assertTrue(field.contains(p.getX(), p.getY()));
		}
		
		//need to add tests that checks known player locations
	}
	
	//tests that the program knows when a goal has been made
	@Test
	public void testGoal() {		
		((SoccerPlayer)game.getTeam1().getPlayers().get(7)).scoreGoal();
		((SoccerPlayer)game.getTeam1().getPlayers().get(0)).scoreGoal();
		((SoccerPlayer)game.getTeam1().getPlayers().get(9)).scoreGoal();
		((SoccerPlayer)game.getTeam1().getPlayers().get(3)).scoreGoal();
		
		((SoccerPlayer)game.getTeam2().getPlayers().get(1)).scoreGoal();
		((SoccerPlayer)game.getTeam2().getPlayers().get(1)).scoreGoal();
		((SoccerPlayer)game.getTeam2().getPlayers().get(5)).scoreGoal();
		
		assertEquals(game.getTeam1Score(), 4);
		assertEquals(game.getTeam2Score(), 3);
	}
	
	
}
