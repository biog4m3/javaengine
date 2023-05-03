#version 400 core

in vec3 position;
out vec3 fragPos;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 model;

void main(){
    gl_Position = projection * view * model * vec4(position, 1.0);
    fragPos = position;
}