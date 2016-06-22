#version 400 core

in vec3 position;
in vec3 normal;
in vec2 textureCoordinates;

out vec2 textureCoords;
out vec3 surface;
out vec3 lightsource;
out vec3 cameraPosition;
out float visibility;

uniform mat4 transformation;
uniform mat4 projection;
uniform mat4 view;
uniform vec3 light;

uniform float useFakeLighting;

const float density  = 0.007;
const float gradient = 1.5;

void main(void)
{
    vec4 worldPosition = transformation * vec4(position, 1.0);
    vec4 positionRelativeToCam = view * worldPosition;

    gl_Position   = projection * positionRelativeToCam;
    textureCoords = textureCoordinates;

    vec3 actualNormal = normal;

    if(useFakeLighting > 0.5)
    {
        actualNormal = vec3(0.0, 1.0, 0.0);
    }

    surface        = (transformation * vec4(actualNormal, 0.0)).xyz;
    lightsource    = light - worldPosition.xyz;
    cameraPosition = (inverse(view) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - worldPosition.xyz;

    float distance = length(positionRelativeToCam.xyz);

    visibility = exp(-pow((distance * density), gradient));
    visibility = clamp(visibility, 0.0, 1.0);
}