package core.graphics.renderers;

import core.Engine;
import core.graphics.Projection;
import core.graphics.Camera;
import core.entities.Entity;
import core.entities.Light;
import core.graphics.models.Model;
import core.graphics.textures.ModelTexture;
import core.math.MathUtils;

import core.graphics.models.TexturedModel;
import core.graphics.shaders.EntityShader;

import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;
import java.util.Map;

public class EntityRenderer extends Renderer
{
    protected EntityShader shader;

    public EntityRenderer(EntityShader shader)
    {
        super(shader);
        this.shader = shader;
    }

    public void init(Projection projection)
    {
        super.init();

        this.shader.bind();
        this.shader.loadProjectionMatrix(projection.getProjection());
        this.shader.unbind();
    }

    public void render(float alpha, Light sun, Camera camera, Entity entity)
    {
        this.shader.bind();

        this.shader.loadSkyColour(new Vector3f(0.5f, 0.5f, 0.5f));
        this.shader.loadLight(sun);
        this.shader.loadView(camera);

        Model        model = entity.getTexturedModel().getModel();
        ModelTexture texture = entity.getTexturedModel().getTexture();

        bind(model, texture);

        prepare(alpha, entity);
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        unbind();

        this.shader.unbind();
    }

    public void render(float alpha, Light sun, Camera camera, Map<TexturedModel, List<Entity>> entities)
    {
        this.shader.bind();

        this.shader.loadSkyColour(new Vector3f(0.5f, 0.5f, 0.5f));
        this.shader.loadLight(sun);
        this.shader.loadView(camera);

        for(TexturedModel model : entities.keySet())
        {
            this.bind(model.getModel(), model.getTexture());

            for(Entity entity : entities.get(model))
            {
                prepare(alpha, entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }

            this.unbind();
        }

        this.shader.unbind();
    }

    private void bind(Model model, ModelTexture texture)
    {
        GL30.glBindVertexArray(model.getVaoID());

        this.shader.enableVertexAttributes();
        this.shader.loadShine(texture.getShine(), texture.getReflectivity());

        if(texture.isTransparent())
        {
            Engine.setCulling(false);
        }

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getID());
    }

    private void unbind()
    {
        Engine.setCulling(true);

        this.shader.disableVertexAttributes();

        GL30.glBindVertexArray(0);
    }

    private void prepare(float alpha, Entity entity)
    {
        this.shader.loadTransformationMatrix(MathUtils.createTransformation(
                entity.getPosition(),
                entity.getRotation(),
                entity.getScale()
        ));
    }
}
