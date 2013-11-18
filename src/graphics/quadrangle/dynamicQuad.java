package graphics.quadrangle;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import util.Rectangle;

public class dynamicQuad extends Quad {	
	private Rectangle area;
	
	private static int vbo;
	private static int vao;
	private static int vto;
	
	private static int count;
	private static int max;
	
	
	public dynamicQuad(Rectangle rect){
		super();
		area = rect;
		load();
	}
	
	public static void setup(){
		int positionVectorSize = new Rectangle(0,0,1,1,null).get3dVertices().length;
		int texelMappingSize = 2;
		int tintSize = 3;
		
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer((positionVectorSize + texelMappingSize + tintSize) * 50);
		
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data_size, GL15.GL_DYNAMIC_DRAW);
		
	}
	
	public void load() {
		float[] vertices = area.get3dVertices();
		byte[] indices = area.getOrder();
		
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
		verticesBuffer.put(vertices);
		verticesBuffer.flip();
		
		ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indices.length);
		indicesBuffer.put(indices);
		indicesBuffer.flip();
		
		GL30.glBindVertexArray(vao);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0,3, GL11.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

//	@Override
//	public void render() {		
//		GL30.glBindVertexArray(vao);
//		GL20.glEnableVertexAttribArray(0);
//		
//		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vibo);
//		
//		GL11.glDrawElements(GL11.GL_TRIANGLES, indexCount, GL11.GL_UNSIGNED_BYTE, 0);
//		
//		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
//		GL20.glDisableVertexAttribArray(0);
//		GL30.glBindVertexArray(0);
//	}

	public void close() {
		GL20.glDisableVertexAttribArray(0);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vibo);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vbo);
		
		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(vao);
	}

	@Override
	public void move(float deltx, float delty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	public static void render(){
		
	}
}
