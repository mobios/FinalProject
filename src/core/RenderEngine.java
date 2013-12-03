package core;

import graphics.backend.TextureManager;
import graphics.frontend.BackgroundImage;
import graphics.frontend.Button;
import graphics.frontend.GuiElement;
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
		new Button(new Rectangle(.3f, .58f, .4f, .4f), "resources/schmile.png", "resources/schmile_down.png", "resources/schmile_over.png", (new util.PressAction(){public void fire(){System.exit(0);};}));
		
		//team selection buttons
		new Button(new Rectangle(.775f, .91f, .1f, .1f), "resources/button.png", "resources/button.png", "resources/button.png", (new util.PressAction(){public void fire(){System.exit(0);};}));
		new Button(new Rectangle(.92f, .91f, .1f, .1f), "resources/button.png", "resources/button.png", "resources/button.png", (new util.PressAction(){public void fire(){System.exit(0);};}));
		
		
		
		//formation buttons
		new Button(new Rectangle(.85f, .75f, .25f, .13f), "resources/button.png", "resources/button.png", "resources/button.png", (new util.PressAction(){public void fire(){System.exit(0);};}));
		new Button(new Rectangle(.85f, .55f, .25f, .13f), "resources/button.png", "resources/button.png", "resources/button.png", (new util.PressAction(){public void fire(){System.exit(0);};}));
		new Button(new Rectangle(.85f, .35f, .25f, .13f), "resources/button.png", "resources/button.png", "resources/button.png", (new util.PressAction(){public void fire(){System.exit(0);};}));
		new Button(new Rectangle(.85f, .15f, .25f, .13f), "resources/button.png", "resources/button.png", "resources/button.png", (new util.PressAction(){public void fire(){System.exit(0);};}));
		
		Team team1 = new Team("BestTeamEver", new float[] {0.6f, 1.0f, 1.0f, 1.0f}, Team.FieldHalf.Left);
		team1.getInFormation(Team.FormationType.FourThreeThree);
		Team team2 = new Team("BesterTeamEver", new float[] {1.0f, 0.6f, 1.0f, 1.0f}, Team.FieldHalf.Right);
		team2.getInFormation(Team.FormationType.ThreeFiveTwo);
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
