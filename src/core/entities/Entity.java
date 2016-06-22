package core.entities;

import core.graphics.models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class Entity
{
    private TexturedModel model;

    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public Entity(TexturedModel model)
    {
        this.model    = model;
        this.position = new Vector3f(0.0f, 0.0f, 0.0f);
        this.rotation = new Vector3f(1.0f, 0.0f, 0.0f);
        this.scale    = new Vector3f(1.0f, 1.0f, 1.0f);
    }

    public Entity(TexturedModel model, Vector3f position)
    {
        this.model    = model;
        this.position = position;
        this.rotation = new Vector3f(1.0f, 0.0f, 0.0f);
        this.scale    = new Vector3f(1.0f, 1.0f, 1.0f);
    }

    public Entity(TexturedModel model, Vector3f position, Vector3f rotation)
    {
        this.model    = model;
        this.position = position;
        this.rotation = rotation;
        this.scale    = new Vector3f(1.0f, 1.0f, 1.0f);
    }

    public Entity(TexturedModel model, Vector3f position, Vector3f rotation, Vector3f scale)
    {
        this.model    = model;
        this.position = position;
        this.rotation = rotation;
        this.scale    = scale;
    }

    public TexturedModel getTexturedModel()
    {
        return this.model;
    }

    public Vector3f getPosition()
    {
        return this.position;
    }

    public Vector3f getRotation()
    {
        return this.rotation;
    }

    public Vector3f getScale()
    {
        return this.scale;
    }

    public void setPosition(float x)
    {
        this.setPosition(x, this.position.y, this.position.z);
    }

    public void setPosition(float x, float y)
    {
        this.setPosition(x, y, this.position.z);
    }

    public void setPosition(float x, float y, float z)
    {
        this.position.x += x;
        this.position.y += y;
        this.position.z += z;
    }

    public void setRotation(float x)
    {
        this.setRotation(x, this.rotation.y, this.rotation.z);
    }

    public void setRotation(float x, float y)
    {
        this.setRotation(x, y, this.rotation.z);
    }

    public void setRotation(float x, float y, float z)
    {
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
    }

    public void setScale(float x)
    {
        this.setScale(x, this.scale.y, this.scale.z);
    }

    public void setScale(float x, float y)
    {
        this.setScale(x, y, this.scale.z);
    }

    public void setScale(float x, float y, float z)
    {
        this.scale.x += x;
        this.scale.y += y;
        this.scale.z += z;
    }

}
