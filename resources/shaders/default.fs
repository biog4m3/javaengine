#version 400 core

in vec2 fragTextureCoord;

out vec4 fragColour;

uniform sampler2D textureSampler;

void main(){
    //fragColour = texture(textureSampler, fragTextureCoord);
    fragColour = vec4(0.87,0.07, 0.83, 1.0);
}