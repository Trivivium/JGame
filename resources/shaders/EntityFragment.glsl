#version 400 core

in vec2 textureCoords;
in vec3 lightsource;
in vec3 surface;
in vec3 cameraPosition;
in float visibility;

out vec4 pixelColour;

uniform vec3      colour;
uniform sampler2D sampler;
uniform float     shine;
uniform float     reflectivity;
uniform vec3      skyColour;

void main(void)
{
    vec3 unitSurface     = normalize(surface);
    vec3 unitLightsource = normalize(lightsource);

    float unitDifference = dot(unitSurface, unitLightsource);
    float brightnesss    = max(unitDifference, 0.2);

    vec3 diffuse = brightnesss * colour;

    vec3 unitCameraPosition = normalize(cameraPosition);

    vec3 lightDirection          = -unitLightsource;
    vec3 reflectedLightDirection = reflect(lightDirection, surface);

    float specularFactor = max(dot(reflectedLightDirection, unitCameraPosition), 0.0);
    float dampedFactor   = pow(specularFactor, shine);

    vec3 specularLighting = dampedFactor * reflectivity * colour;

    vec4 textureColour = texture(sampler, textureCoords);

    if (textureColour.a < 1)
    {
        discard;
    }

    pixelColour = vec4(diffuse, 1.0) * textureColour + vec4(specularLighting, 1.0);
    pixelColour = mix(vec4(skyColour, 1.0), pixelColour, visibility);
}