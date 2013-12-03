package core;

import graphics.frontend.BackgroundImage;
import graphics.frontend.Button; 

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import coachingTools.Game;
import coachingTools.Player;
import coachingTools.Team;
import util.MouseEvent;
import util.Rectangle;

public class GameEngine {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	public static List<Button> buttons;
	public static Team team;
	public static Game game;
	
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
		OpenGL3();
		//GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);
		RenderEngine.setup();
		buttons = new ArrayList<Button>();
		createBackgroundandButtons();
		
	}

	// checks for errors then runs the program in a loop until a close request is made.
	private static void createBackgroundandButtons() {
		new BackgroundImage(new Rectangle(-.15f, .0f, 1.7f, 2.0f), "resources/field.png");
		game = new Game();
		team = game.getTeam1();
		game.getTeam1().getInFormation(Team.FormationType.FourFourTwo);
		game.getTeam2().getInFormation(Team.FormationType.ThreeFourThree);
		game.getTeam2().getPlayers().get(8).setBall(true);

		createButtons(game);


		
	}

	public static void run(){
		int error = GL11.glGetError();
		if(error > 0)
			return;
		while(!Display.isCloseRequested()){
			handleMouse();
			RenderEngine.render();
			Display.sync(60);
			Display.update();
		}
		
		Display.destroy();
		
	}
	
	// used to get and deal with mouse clicks
	public static void handleMouse(){
		MouseEvent event;
		
		int mx = Mouse.getEventX();
		int my = Mouse.getEventY();
		
		while(Mouse.next()){
			if(Mouse.getEventButton() == 0){
				if(Mouse.isButtonDown(0))
					event = MouseEvent.DOWN;
				else
					event = MouseEvent.UP;
			}
			else{
				event = MouseEvent.MOVE;
			}
			for(Button button : buttons)
				if(button.handleMouse(event, mx, my))
					break;
			
//			if(Mouse.getEventButton() == 0){
//				if(Mouse.isButtonDown(0)){
//					for(Button button : buttons){
//						button.mouseDown(Mouse.getEventX(), Mouse.getEventY());
//					}
//					return;
//				}
//
//				for(Button button : buttons){
//					button.mouseUp(Mouse.getEventX(), Mouse.getEventY());
//				}
//			}
//			else{
//				for(Button button : buttons){
//					button.mouseMove(Mouse.getEventX(), Mouse.getEventY());
//				}
//			}
		}
	}
	
	//Creates buttons for control of the game
	private static void createButtons(final Game game) {
		//team selection buttons
		new Button(new Rectangle(.775f, .91f, .1f, .1f), "resources/bluebuttonteam1.png", "resources/redbuttonteam1.png", "resources/bluebuttonteam1.png", (new util.PressAction(){public void fire(){team = game.getTeam1();};}));
		new Button(new Rectangle(.92f, .91f, .1f, .1f), "resources/bluebuttonteam2.png", "resources/redbuttonteam2.png", "resources/bluebuttonteam2.png", (new util.PressAction(){public void fire(){team = game.getTeam2();};}));
		
		//pass buttons
		passButton = new Button(new Rectangle(.85f, -.25f, .25f, .13f), "resources/Passbutton.png", "resources/Passbutton_down.png", "resources/Passbutton.png", (new util.PressAction(){public void fire(){pass();};}));
		
		//formation buttons
		new Button(new Rectangle(.85f, .75f, .25f, .13f), "resources/bluebutton442.png", "resources/redbutton442.png", "resources/bluebutton442.png", (new util.PressAction(){public void fire(){team.getInFormation(Team.FormationType.FourFourTwo);};}));
		new Button(new Rectangle(.85f, .55f, .25f, .13f), "resources/bluebutton433.png", "resources/redbutton433.png", "resources/bluebutton433.png", (new util.PressAction(){public void fire(){team.getInFormation(Team.FormationType.FourThreeThree);};}));
		new Button(new Rectangle(.85f, .35f, .25f, .13f), "resources/bluebutton343.png", "resources/redbutton343.png", "resources/bluebutton343.png", (new util.PressAction(){public void fire(){team.getInFormation(Team.FormationType.ThreeFiveTwo);};}));
		new Button(new Rectangle(.85f, .15f, .25f, .13f), "resources/bluebutton352.png", "resources/redbutton352.png", "resources/bluebutton352.png", (new util.PressAction(){public void fire(){team.getInFormation(Team.FormationType.ThreeFourThree);};}));
	}
	
	// used to give the pass button functionality
	private static void pass(){
		passButton.setSticky(true);
		boolean test = true;
		Player ballHolder = game.getPlayerWithBall();
		
		
		while(test){
			test = false;
		}
		
		
	}
	
	
	
	
}
