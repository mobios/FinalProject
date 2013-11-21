#version 330 core

uniform sampler2D texelSample;

in vec2 pass_uv;
in vec4 tint;

out vec4 color;

void main(){
	//color = texture(texelSample, pass_uv) + tint;
	color = tint;
}