package core;

import graphics.quadrangle.Quad;
import graphics.quadrangle.dynamicQuad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import util.Origin;
import util.Rectangle;

public class RenderEngine {
	public static Quad quads;
	public static dynamicQuad dquads;
	
	public static int ProgramID;
	public static int fragmentShaderID;
	public static int vertexShaderID;
	
	public static void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		dquads.render();
	}
	
	public static void setup(){
		dquads = new dynamicQuad(null);
		dquads.setup();
		
	}
	
	public static void test(){
		new dynamicQuad(new Rectangle(0,0, 1, 1, Origin.CENTER));
	}
	
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
		
		return shaderID;
	}
	
	public static void initalizeShaders(){
		vertexShaderID = loadShader("src/graphics/vertex.glsl", GL20.GL_VERTEX_SHADER);
		vertexShaderID = loadShader("src/graphics/fragment.glsl", GL20.GL_FRAGMENT_SHADER);
	}
	
	public static void initalizeProgram(){
		ProgramID = GL20.glCreateProgram();
		GL20.glAttachShader(ProgramID, vertexShaderID);
		GL20.glAttachShader(ProgramID, fragmentShaderID);
		
		GL20.glLinkProgram(ProgramID);
		GL20.glValidateProgram(ProgramID);
	}
}
