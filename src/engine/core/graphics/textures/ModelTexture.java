package engine.core.graphics.textures;

public class ModelTexture
{
    private int ID;

    private float shine        = 1;
    private float reflectivity = 0;

    private boolean transparent     = false;
    private boolean useFakeLighting = false;

    public ModelTexture(int ID)
    {
        this.ID = ID;
    }

    public int getID()
    {
        return ID;
    }

    public float getShine()
    {
        return shine;
    }

    public void setShine(float shine)
    {
        this.shine = shine;
    }

    public float getReflectivity()
    {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity)
    {
        this.reflectivity = reflectivity;
    }

    public boolean isTransparent()
    {
        return this.transparent;
    }

    public void setTransparent(boolean status)
    {
        this.transparent = status;
    }

    public boolean isUsingFakeLighting()
    {
        return this.useFakeLighting;
    }

    public void setFakeLighting(boolean status)
    {
        this.useFakeLighting = status;
    }

}
