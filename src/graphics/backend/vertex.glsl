#version 330 core

layout(location = 0) in vec4 position;
layout(location = 1) in vec2 uv;
layout(location = 2) in vec4 tint;

out vec2 pass_uv;
out vec4 pass_tint;

void main(){
	gl_Position = position;
	pass_uv = uv;
	pass_tint = tint;
}