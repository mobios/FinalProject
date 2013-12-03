package graphics.backend;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {
	private static Map<String, Integer> textures;
	
	public static void setup(){
		textures = new HashMap<String, Integer>();
	}
	
	public static boolean isLoaded(String path){
		return textures.containsKey(path);
	}
	
	public static int getIndex(String path){
		return textures.get(path);
	}
	
	public static void load(String path, int index){
		textures.put(path, index);
	}
}
