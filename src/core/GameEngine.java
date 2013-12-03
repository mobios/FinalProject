package core;

import graphics.frontend.BackgroundImage;
import graphics.frontend.BallModel;
import graphics.frontend.Button; 
import graphics.frontend.ScoreDisplay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import util.Clamp;
import util.MouseEvent;
import util.Point;
import util.Rectangle;
import coachingTools.Game;
import coachingTools.Player;
import coachingTools.Team;

public class GameEngine {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;

	public static List<Button> buttons;
	public static Team team;
	public static Game game;
	public static Random rgen;
	
	public static Button passButton;

	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File("native").getAbsolutePath());
		setup();
		RenderEngine.test();
		run();
	}

	//creates a window with a pixelFormat and the correct contextAtrributes, sets the display mode and title
	//and handles errors.

	public static void OpenGL3(){
		PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAtrributes = new ContextAttribs(3,2)
		.withProfileCore(true).withForwardCompatible(true);

		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("FIFA World Cup 2020 -- Quatar Edition");
			Display.create(pixelFormat, contextAtrributes);

			GL11.glViewport(0, 0, WIDTH, HEIGHT);
		} catch(LWJGLException e){
			e.printStackTrace();
			System.exit(1);
		}

		GL11.glClearColor(0f, 0f, 0f, 0f);
	}


	
	// sets up the game by calling the setup fnx (in the renderEngin), openGL3 fnx, and creating an ArrayList of buttons 

	public static void setup(){
		rgen = new Random();
		OpenGL3();
		//GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);
		RenderEngine.setup();
		buttons = new ArrayList<Button>();
		createBackgroundandButtons();
	}


	// checks for errors then runs the program in a loop until a close request is made.

	private static void createBackgroundandButtons() {
		createButtons(game);
		
		game = new Game();
		team = game.getTeam1();
		game.getTeam1().getInFormation(Team.FormationType.FourFourTwo);
		game.getTeam2().getInFormation(Team.FormationType.genRnd());
		game.getTeam1().getPlayers().get(8).setBall(game.ball);
		game.ball.connect(game.getTeam1().getPlayers().get(8));
	}

	public static void run(){
		while(!Display.isCloseRequested()){			//	This is the core logic loop... so let's check if the program is ready to shut down.
			handleMouse();							//	handleMouse() checks for mouse activity, and if there is any, dispatches the events to the GUI
			RenderEngine.render();					//	Draws final output onto the back framebuffer
			Display.sync(60);						//	Limits the flip speed to prevent tearing and to sync with the diplay's Hertz
			Display.update();						//	Copies the back framebuffer into the front framebuffer
		}

		Display.destroy();

	}

	
	// used to get and deal with mouse clicks
	public static float getMouseX(){
		return Clamp.clampX(Mouse.getX());
	}
	
	// used to get and deal with mouse clicks
	public static float getMouseY(){
		return Clamp.clampY(Mouse.getY());
	}
	
	// used to get and deal with mouse clicks

	public static void handleMouse(){
		MouseEvent event;

		int mx = Mouse.getEventX();
		int my = Mouse.getEventY();

		
		while(Mouse.next()){
			if(Mouse.getEventButton() == 0){
				if(Mouse.isButtonDown(0)){
					if(game.duringPass){
						for(Player p : game.getAllPlayers()){
							if(p.getDisplay().getRect().contains(Clamp.clampX(mx), Clamp.clampY(my))){
								game.ball.targetPlayer(p);
								game.interceptPlayer = p;
								game.duringPass = false;
								break;
							}
						}
					}
					event = MouseEvent.DOWN;
				}
				else
					event = MouseEvent.UP;
			}

			else{
				event = MouseEvent.MOVE;
			}

			for(Button button : buttons){
				if(button.handleMouse(event, mx, my))
					break;
			}
		}
	}

	
	//Creates buttons for control of the game
	private static void createButtons(final Game game) {
		//pass buttons
		passButton = new Button(new Rectangle(.85f, -.05f, .25f, .13f), "resources/Passbutton.png", "resources/Passbutton_down.png", "resources/Passbutton.png", (new util.PressAction(){public void fire(){pass();};}));
		passButton.setSticky(true);
		//Go buttons
		new Button(new Rectangle(.85f, -.25f, .25f, .13f), "resources/GObutton.png", "resources/GObutton_down.png", "resources/GObutton.png", (new util.PressAction(){public void fire(){move();};}));

		//formation buttons
		new Button(new Rectangle(.85f, .75f, .25f, .13f), "resources/bluebutton442.png", "resources/redbutton442.png", "resources/bluebutton442.png", (new util.PressAction(){public void fire(){
				team.getInFormation(Team.FormationType.FourFourTwo);
				game.getTeam2().getInFormation(Team.FormationType.genRnd());};}));
		new Button(new Rectangle(.85f, .55f, .25f, .13f), "resources/bluebutton433.png", "resources/redbutton433.png", "resources/bluebutton433.png", (new util.PressAction(){public void fire(){
				team.getInFormation(Team.FormationType.FourThreeThree);
				game.getTeam2().getInFormation(Team.FormationType.genRnd());};}));
		new Button(new Rectangle(.85f, .35f, .25f, .13f), "resources/bluebutton343.png", "resources/redbutton343.png", "resources/bluebutton343.png", (new util.PressAction(){public void fire(){
				team.getInFormation(Team.FormationType.ThreeFourThree);
				game.getTeam2().getInFormation(Team.FormationType.genRnd());};}));
		new Button(new Rectangle(.85f, .15f, .25f, .13f), "resources/bluebutton352.png", "resources/redbutton352.png", "resources/bluebutton352.png", (new util.PressAction(){public void fire(){
				team.getInFormation(Team.FormationType.ThreeFiveTwo);
				game.getTeam2().getInFormation(Team.FormationType.genRnd());};}));
	}




	// used to give the pass button functionality

	private static void pass(){
		Player ballHolder = game.getPlayerWithBall();
		Team teamWithBall = game.getTeamWithBall();

		ballHolder.setBall(null);
		game.duringPass = true;

	}

	//give the move button functionality
	private static void move(){
		
		
		game.go();
		
	}



}
