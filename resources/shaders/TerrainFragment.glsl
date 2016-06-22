#version 400 core

in vec2 textureCoords;
in vec3 lightsource;
in vec3 surface;
in vec3 cameraPosition;

out vec4 pixelColour;

uniform sampler2D backgroudTexture;
uniform sampler2D rTexture;
uniform sampler2D gTexture;
uniform sampler2D bTexture;
uniform sampler2D blendmap;

uniform vec3      colour;
uniform float     shine;
uniform float     reflectivity;

void main(void)
{
    vec4 blendMapColour = texture(blendmap, textureCoords);
    vec2 tiledCoords    = textureCoords * 40.0;

    float backTextureAmount     = 1 - (blendMapColour.r + blendMapColour.g + blendMapColour.b);
    vec4 backgroudTextureColour = texture(backgroudTexture, tiledCoords) * backTextureAmount;

    vec4 rTextureColour = texture(rTexture, tiledCoords) * blendMapColour.r;
    vec4 gTextureColour = texture(gTexture, tiledCoords) * blendMapColour.g;
    vec4 bTextureColour = texture(bTexture, tiledCoords) * blendMapColour.b;

    vec4 totalColour = backgroudTextureColour + rTextureColour + gTextureColour + bTextureColour;

    vec3 unitSurface     = normalize(surface);
    vec3 unitLightsource = normalize(lightsource);

    float unitDifference = dot(unitSurface, unitLightsource);
    float brightnesss    = max(unitDifference, 0.15);

    vec3 diffuse = brightnesss * colour;

    vec3 unitCameraPosition = normalize(cameraPosition);

    vec3 lightDirection          = -unitLightsource;
    vec3 reflectedLightDirection = reflect(lightDirection, surface);

    float specularFactor = max(dot(reflectedLightDirection, unitCameraPosition), 0.0);
    float dampedFactor   = pow(specularFactor, shine);

    vec3 specularLighting = dampedFactor * reflectivity * colour;

    pixelColour = vec4(diffuse, 1.0) * totalColour + vec4(specularLighting, 1.0);
}