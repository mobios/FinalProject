package graphics;

public abstract class DoubleBuffer<T>{
	protected T buffer1;
	protected T buffer2;
	protected T curentBuffer;
	
	protected boolean choice;
	
	public void swap(){
		if(choice){
			choice = false;
			return;
		}
		choice = true;
	}
	
	public T retrieve(){
		if(choice)
			return buffer1;
		return buffer2;
	}
	
	public abstract void modify();
	public abstract void bufferAction();
}
