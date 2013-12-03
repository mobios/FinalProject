package core;

import graphics.backend.TextureManager;
import graphics.backend.segmentDisplay.SegmentHorizontal;
import graphics.backend.segmentDisplay.SegmentVertical;
import graphics.backend.segmentDisplay.SevenSegmentDisplay;
import graphics.frontend.BackgroundImage;
import graphics.frontend.BallModel;
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
import coachingTools.Player;
import coachingTools.Team;
import util.Point;
import util.Rectangle;

public class RenderEngine {
	public static Quad quads;
	public static PlayerModel dquadStaticHandle;
	public static BackgroundImage squadStaticHandle;
	public static GuiElement guiStaticHandle;
	
	public static SegmentHorizontal horizSeg;
	public static SegmentVertical vertSeg;
	
	public static int ProgramID;
	public static int fragmentShaderID;
	public static int vertexShaderID;

	// renders the the various images displayed ie the player, the background and the GUI
	public static void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		squadStaticHandle.render();
		dquadStaticHandle.render();
		guiStaticHandle.render();
	}
	
	// used to setup all the thing needed in creating a image on screen like textures shaders and the program itself
	public static void setup(){
		TextureManager.setup();
		dquadStaticHandle = new PlayerModel();
		dquadStaticHandle.staticSetup();

		squadStaticHandle = new BackgroundImage();
		squadStaticHandle.staticSetup();
		
		guiStaticHandle = new GuiElement();
		guiStaticHandle.staticSetup();
		
		horizSeg = new SegmentHorizontal();
		vertSeg = new SegmentVertical();
		horizSeg.staticSetup();
		vertSeg.staticSetup();
		
		initializeShaders();
		initializeProgram();
	}
	
	
	public static void test(){
		guiStaticHandle.populate();
	}
	
	// loads the shaders form a .glsl file that is passed in as a string (the path to the file)
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
	
	// creates the shaders used in setup() by calling loadShader()
	public static void initializeShaders(){
		vertexShaderID = loadShader("src/graphics/backend/vertex.glsl", GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader("src/graphics/backend/fragment.glsl", GL20.GL_FRAGMENT_SHADER);
	}
	
	// initializes the program by by setting its ID, attaching and the shaders
	// must be called after initializeShaders()
	public static void initializeProgram(){
		ProgramID = GL20.glCreateProgram();
		GL20.glAttachShader(ProgramID, vertexShaderID);
		GL20.glAttachShader(ProgramID, fragmentShaderID);
		
		// set blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		//disables the GL depth test
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		// likes the program to its ID and validates that ID
		GL20.glLinkProgram(ProgramID);
		GL20.glValidateProgram(ProgramID);
		GL20.glUseProgram(ProgramID);
	}
}
