#version 330 core

uniform sampler2D texelSample;

in vec2 pass_uv;
in vec4 pass_tint;

out vec4 color;

void main(){
	color = texture2D(texelSample, pass_uv)+ pass_tint;
	//color = pass_tint;
}