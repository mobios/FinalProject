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
import coachingTools.SoccerTeam;
import coachingTools.SoccerTeam.FormationType;
import coachingTools.Team;

public class GameTests {
	Game game;
	//not exactly sure what these dimension values are going to be yet
	public static final float FIELD_LENGTH = 1;
	public static final float FIELD_HEIGHT = 1;

	@Before
	public void setUp(){
		game = new SoccerGame();
		game.getGameField().setHeight(FIELD_HEIGHT);
		game.getGameField().setWidth(FIELD_LENGTH);
	}

	// to test moving to various locations on the field
	@Test
	public void testMove() {

		Player testPlayer = new SoccerPlayer(10, 10);

		testPlayer.setX((float) 0.5);
		testPlayer.setY((float) 0.5);
		testPlayer.move(0, 0, 10);// move to the origin

		assertEquals(0, (long)testPlayer.getX());
		assertEquals(0, (long)testPlayer.getY());
		assertEquals(0, testPlayer.getStamina());


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
		assertEquals(noBallCount, (SoccerTeam.NUMBER_OF_PLAYERS*2)-1);
		for(int i=1; i < SoccerTeam.NUMBER_OF_PLAYERS; i++){
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
		Player throwingPlayer = players.get(0);

		((SoccerGame) game).throwIn(players, throwingPlayer);
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
		assertEquals(1, numPlayersWithBall);

	}

	// to test that the game recognizes when the ball/players are out of bound
	@Test
	public void testBounds() {
		Rectangle field = game.getGameField().bounds;
		float width = game.getGameField().getHeight();
		float height = game.getGameField().getHeight();
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

	// tests to make sure that the field has the correct dimensions
	@Test
	public void testFieldDimensions() {
		assertEquals(FIELD_LENGTH, game.getGameField().getWidth(), 0.001);
		assertEquals(FIELD_HEIGHT, game.getGameField().getHeight(), 0.001);
	}

	// tests that the players have been loaded
	@Test
	public void testLoadPlayerImage() {
		fail("Not yet implemented");
	}

	// tests the Game can make a formation of players 
	@Test
	public void testGetInFormation() {

		((SoccerTeam) game.getTeam1()).getInFormation(FormationType.RUSH);
		assertTrue(((SoccerTeam) game.getTeam1()).isInFormation(FormationType.RUSH));

		((SoccerTeam) game.getTeam2()).getInFormation(FormationType.DEFEND);
		assertTrue(((SoccerTeam) game.getTeam2()).isInFormation(FormationType.DEFEND));

		((SoccerTeam) game.getTeam2()).getInFormation(FormationType.RUSH);
		assertTrue(((SoccerTeam) game.getTeam2()).isInFormation(FormationType.RUSH));

		((SoccerTeam) game.getTeam1()).getInFormation(FormationType.DEFEND);
		assertTrue(((SoccerTeam) game.getTeam1()).isInFormation(FormationType.DEFEND));

		assertFalse(((SoccerTeam) game.getTeam1()).isInFormation(FormationType.RUSH));
		assertFalse(((SoccerTeam) game.getTeam2()).isInFormation(FormationType.DEFEND));
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

	@Test
	public void testOffside() {
		ArrayList<Player> players1 = game.getTeam1().getPlayers();
		ArrayList<Player> players2 = game.getTeam2().getPlayers();

		int number = 0;

		for(Player p1 : players1){
			for (Player p2: players2){
				if(p1.getX()<p2.getX()){
					number++;
				}
			}

			if(number > 10){
				assertTrue(((SoccerPlayer)p1).isOffside());
			} else {
				assertFalse(((SoccerPlayer)p1).isOffside());
			}



		}
	}


	@Test
	public void testNumberOfPlayers(){
		ArrayList<Player> players1 = game.getTeam1().getPlayers();
		ArrayList<Player> players2 = game.getTeam2().getPlayers();

		assertEquals(11, players1.size());
		assertEquals(11, players2.size());
	}

	@Test
	public void testGoalKick(){
		ArrayList<Player> players = game.getTeam1().getPlayers();

		for(Player p:players){
			if(((SoccerPlayer)p).isGoalie()){
				((SoccerGame)game).goalKick(p, players);
				assertFalse(p.hasBall());
			}
		}

	}


	@Test
	public void testKickOff(){
		ArrayList<Player> players = game.getTeam1().getPlayers();



		((SoccerGame)game).kickOff(players.get(6), players);

		assertFalse(players.get(6).hasBall());

		boolean other = false;
		for(Player p:players){
			if(p.hasBall() && p != players.get(6)){
				other = true;
			}
		}

		assertTrue(other);

	}



}


