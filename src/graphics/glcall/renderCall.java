package graphics.glcall;

public interface renderCall {
	public void staticSetup();
	public void setupBatch();
	public void teardownBatch();
	public void renderloop();
}
