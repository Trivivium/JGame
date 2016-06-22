package core.graphics.shaders;

import core.graphics.Camera;
import core.entities.Light;

import core.math.Vector3f;
import core.math.Matrix4f;
import core.math.MathUtils;

import org.lwjgl.opengl.GL20;

import java.io.File;

public class EntityShader extends AbstractShader
{
    private static final String VERTEX_FILE   = "resources/shaders/EntityVertex.glsl";
    private static final String FRAGMENT_FILE = "resources/shaders/EntityFragment.glsl";

    public EntityShader()
    {
        super(new File(VERTEX_FILE), new File(FRAGMENT_FILE));
    }

    public void loadSkyColour(Vector3f colour)
    {
        super.setUniform(this.locations.get("skyColour"), colour);
    }

    public void loadFakeLightingConfiguration(boolean conf)
    {
        super.setUniform(this.locations.get("useFakeLighting"), conf);
    }

    public void loadShine(float shine, float reflectivity)
    {
        super.setUniform(this.locations.get("shine"), shine);
        super.setUniform(this.locations.get("reflectivity"), reflectivity);
    }

    public void loadLight(Light light)
    {
        super.setUniform(this.locations.get("light"), light.getPosition());
        super.setUniform(this.locations.get("colour"), light.getColour());
    }

    public void loadView(Camera camera)
    {
        Matrix4f matrix = MathUtils.createView(camera.getPosition(), camera.getRotation());

        super.setUniform(this.locations.get("view"), matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix)
    {
        super.setUniform(this.locations.get("projection"), matrix);
    }

    public void loadTransformationMatrix(Matrix4f matrix)
    {
        super.setUniform(this.locations.get("transformation"), matrix);
    }

    public void enableVertexAttributes()
    {
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
    }

    public void disableVertexAttributes()
    {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
    }

    @Override
    protected void bindAttributes()
    {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
        super.bindAttribute(2, "normal");
    }

    @Override
    protected void loadUniformLocations()
    {
        super.loadUniformLocation("transformation");
        super.loadUniformLocation("projection");
        super.loadUniformLocation("view");

        super.loadUniformLocation("light");
        super.loadUniformLocation("colour");
        super.loadUniformLocation("shine");
        super.loadUniformLocation("reflectivity");

        super.loadUniformLocation("useFakeLighting");
        super.loadUniformLocation("skyColour");
    }
}
