package core.graphics.textures;

public class TerrainTexturePack
{
    private TerrainTexture backgroundTexture;
    private TerrainTexture rTexture;
    private TerrainTexture gTexture;
    private TerrainTexture bTexture;

    public TerrainTexturePack(TerrainTexture background, TerrainTexture r, TerrainTexture g, TerrainTexture b)
    {
        this.backgroundTexture = background;
        this.rTexture = r;
        this.gTexture = g;
        this.bTexture = b;
    }

    public TerrainTexture getBackgroundTexture()
    {
        return backgroundTexture;
    }

    public TerrainTexture getRTexture()
    {
        return rTexture;
    }

    public TerrainTexture getGTexture()
    {
        return gTexture;
    }

    public TerrainTexture getBTexture()
    {
        return bTexture;
    }
}
