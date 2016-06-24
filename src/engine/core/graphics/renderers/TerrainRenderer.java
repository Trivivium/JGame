package engine.core.graphics.renderers;

import engine.core.Engine;

import engine.entities.Light;

import engine.core.graphics.Camera;
import engine.core.graphics.Projection;
import engine.core.graphics.shaders.TerrainShader;
import engine.core.graphics.textures.TerrainTexturePack;

import engine.terrain.Terrain;

import engine.core.math.Matrix4f;
import engine.core.math.Vector3f;
import engine.core.math.MathUtils;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import java.util.List;

public class TerrainRenderer extends Renderer
{
    protected TerrainShader shader;

    public TerrainRenderer(TerrainShader shader)
    {
        super(shader);
        this.shader = shader;
    }

    public void init(Projection projection)
    {
        super.init();

        this.shader.bind();
        this.shader.connectTextureUnits();
        this.shader.loadProjectionMatrix(projection.getProjection());
        this.shader.unbind();
    }

    public void render(Light sun, Camera camera, List<Terrain> terrains)
    {
        this.shader.bind();
        this.shader.loadLight(sun);
        this.shader.loadView(camera);

        for(Terrain terrain : terrains)
        {
            bind(terrain);
            prepare(terrain);

            GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

            unbind();
        }

        this.shader.unbind();
    }

    private void bindTextures(Terrain terrain)
    {
        TerrainTexturePack pack = terrain.getTexturePack();

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getBackgroundTexture().getID());

        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getRTexture().getID());

        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getGTexture().getID());

        GL13.glActiveTexture(GL13.GL_TEXTURE3);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getBTexture().getID());

        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, terrain.getBlendmap().getID());
    }

    private void bind(Terrain terrain)
    {
        GL30.glBindVertexArray(terrain.getModel().getVaoID());

        this.bindTextures(terrain);
        this.shader.enableVertexAttributes();
        this.shader.loadShine(1, 0);
    }

    private void unbind()
    {
        Engine.setCulling(true);

        this.shader.disableVertexAttributes();

        GL30.glBindVertexArray(0);
    }

    private void prepare(Terrain terrain)
    {
        Matrix4f transformation = MathUtils.createTransformation(
                new Vector3f(terrain.getX(), 0, terrain.getZ()),
                new Vector3f(0, 0, 0),
                new Vector3f(1, 1, 1)
        );

        this.shader.loadTransformationMatrix(transformation);
    }
}
