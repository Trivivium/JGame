package engine.core.graphics.renderers;

import engine.core.graphics.shaders.AbstractShader;

public abstract class Renderer
{
    protected AbstractShader shader;

    public Renderer(AbstractShader shader)
    {
        this.shader = shader;
    }

    public void init()
    {
        this.shader.init();
    }

    public void cleanup()
    {
        this.shader.cleanup();
    }
}
