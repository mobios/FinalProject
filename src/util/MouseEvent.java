package util;

public enum MouseEvent {
	UP, DOWN, MOVE;

	public MouseEvent cast(int arg){
		switch(arg){
		case 0:
			return DOWN;

		case 1:
			return UP;
			
		case 2:
			return MOVE;
		
		default:
			return null;
		}
	}
}
