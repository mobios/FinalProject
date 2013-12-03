package util;

import core.GameEngine;

public class Clamp {
	public static float clampX(float x){
		x -= GameEngine.WIDTH/2.f;
		x /= GameEngine.WIDTH/2.f;
		return x;
	}
	
	public static float clampY(float y){
		y -= GameEngine.HEIGHT/2.f;
		y /= GameEngine.HEIGHT/2.f;
		return y;
	}
}
