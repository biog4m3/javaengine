#version 400 core

in vec3 position;
in vec2 textureCoord;

out vec2 fragTextureCoord;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 model;


void main(){
    gl_Position = projection * view * model * vec4(position, 1.0);
    fragTextureCoord = textureCoord;
}