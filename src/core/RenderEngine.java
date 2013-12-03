package core;

import graphics.backend.TextureManager;
import graphics.frontend.BackgroundImage;
import graphics.frontend.Button;
import graphics.frontend.GuiElement;
import graphics.frontend.Placeable;
import graphics.frontend.PlayerModel;
import graphics.quadrangle.Quad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import coachingTools.Game;
import coachingTools.Team;
import util.Rectangle;

public class RenderEngine {
	public static Quad quads;
	public static PlayerModel dquadStaticHandle;
	public static BackgroundImage squadStaticHandle;
	public static GuiElement guiStaticHandle;
	
	public static int ProgramID;
	public static int fragmentShaderID;
	public static int vertexShaderID;
	public static Team team = new Team();
	
	public static void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		squadStaticHandle.render();
		dquadStaticHandle.render();
		guiStaticHandle.render();
	}
	
	public static void setup(){
		TextureManager.setup();
		dquadStaticHandle = new PlayerModel();
		dquadStaticHandle.staticSetup();

		squadStaticHandle = new BackgroundImage();
		squadStaticHandle.staticSetup();
		
		guiStaticHandle = new GuiElement();
		guiStaticHandle.staticSetup();

		initializeShaders();
		initializeProgram();
	}
	
	public static void test(){
		
		new BackgroundImage(new Rectangle(-.15f, .0f, 1.7f, 2.0f), "resources/field.png");
		
		final Game game = new Game();
		team = game.getTeam1();
		game.getTeam1().getInFormation(Team.FormationType.FourFourTwo);
		game.getTeam2().getInFormation(Team.FormationType.ThreeFourThree);
		game.getTeam2().getPlayers().get(8).setBall(true);

		//team selection buttons
		new Button(new Rectangle(.775f, .91f, .1f, .1f), "resources/bluebuttonteam1.png", "resources/redbuttonteam1.png", "resources/bluebuttonteam1.png", (new util.PressAction(){public void fire(){team = game.getTeam1();};}));
		new Button(new Rectangle(.92f, .91f, .1f, .1f), "resources/bluebuttonteam2.png", "resources/redbuttonteam2.png", "resources/bluebuttonteam2.png", (new util.PressAction(){public void fire(){team = game.getTeam2();};}));
		
		
		//formation buttons
		new Button(new Rectangle(.85f, .75f, .25f, .13f), "resources/bluebutton442.png", "resources/redbutton442.png", "resources/bluebutton442.png", (new util.PressAction(){public void fire(){team.getInFormation(Team.FormationType.FourFourTwo);};}));
		new Button(new Rectangle(.85f, .55f, .25f, .13f), "resources/bluebutton433.png", "resources/redbutton433.png", "resources/bluebutton433.png", (new util.PressAction(){public void fire(){team.getInFormation(Team.FormationType.FourThreeThree);};}));
		new Button(new Rectangle(.85f, .35f, .25f, .13f), "resources/bluebutton343.png", "resources/redbutton343.png", "resources/bluebutton343.png", (new util.PressAction(){public void fire(){team.getInFormation(Team.FormationType.ThreeFiveTwo);};}));
		new Button(new Rectangle(.85f, .15f, .25f, .13f), "resources/bluebutton352.png", "resources/redbutton352.png", "resources/bluebutton352.png", (new util.PressAction(){public void fire(){team.getInFormation(Team.FormationType.ThreeFourThree);};}));

		Placeable.setup();
		guiStaticHandle.populate();
	}
	
	@SuppressWarnings("deprecation")
	public static int loadShader(String source, int type){
		int shaderID;
		StringBuilder shader = new StringBuilder();
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(source));
			String line;
			while(( line = reader.readLine()) != null){
				shader.append(line).append("\n");
			}
			
			reader.close();
		} catch (IOException e){
			e.printStackTrace();
			System.exit(2);
		}
		
		shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shader);
		GL20.glCompileShader(shaderID);
		
		System.out.println("Shader ID:" +shaderID + "\nInfo: "+ GL20.glGetShaderInfoLog(shaderID, 1024));
		
		if (GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Could not compile shader.");
			System.exit(-1);
			}
		return shaderID;
	}
	
	public static void initializeShaders(){
		vertexShaderID = loadShader("src/graphics/backend/vertex.glsl", GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader("src/graphics/backend/fragment.glsl", GL20.GL_FRAGMENT_SHADER);
	}
	
	public static void initializeProgram(){
		ProgramID = GL20.glCreateProgram();
		GL20.glAttachShader(ProgramID, vertexShaderID);
		GL20.glAttachShader(ProgramID, fragmentShaderID);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		//GL20.glBindAttribLocation(ProgramID, 0, "position");
		//GL20.glBindAttribLocation(ProgramID, 1, "uv");
		//GL20.glBindAttribLocation(ProgramID, 2, "tint");
		
		GL20.glLinkProgram(ProgramID);
		GL20.glValidateProgram(ProgramID);
		GL20.glUseProgram(ProgramID);
	}
}
