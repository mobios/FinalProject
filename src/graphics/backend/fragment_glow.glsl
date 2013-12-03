#version 330 core

uniform float top;
uniform float left;
uniform float right;
uniform float bottom;

uniform vec3 tint;

out vec4 color;

void main(){
	int x = glFragCoord.x;
	int y = glFragCoord.y;
	
	if(abs(x-left)>.3 && abs(x-right)>.3)
		color = vec4(tint, 0.12);
	else{
		if(abs(x-left) < abs(x-right))
			color = vec4(tint, 1-((abs(x-left)/.3)*.82);
		else
			color = vec4(tint, 1-((abs(x-right)/.3)*.82);
	}
}