#version 400 core

in vec3 position;
in vec3 normal;
in vec2 textureCoordinates;

out vec2 textureCoords;
out vec3 surface;
out vec3 lightsource;
out vec3 cameraPosition;

uniform mat4 transformation;
uniform mat4 projection;
uniform mat4 view;
uniform vec3 light;

void main(void)
{
    vec4 worldPosition = transformation * vec4(position, 1.0);

    gl_Position   = projection * view * worldPosition;
    textureCoords = textureCoordinates;

    surface        = (transformation * vec4(normal, 0.0)).xyz;
    lightsource    = light - worldPosition.xyz;
    cameraPosition = (inverse(view) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - worldPosition.xyz;
}