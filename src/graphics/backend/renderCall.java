package graphics.backend;

public interface renderCall {
	public void staticSetup();
	
	public void setupBatch();
	public void bindTexture();
	public void renderLoop();
	public void teardownBatch();
}
