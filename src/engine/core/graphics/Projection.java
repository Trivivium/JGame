package engine.core.graphics;

import engine.core.math.Matrix4f;
import engine.core.math.MathUtils;

public class Projection
{
    private static final float FOV        = 70.0f;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE  = 1000.0f;

    private Matrix4f projection;

    public Projection(int width, int height)
    {
        this.projection = MathUtils.createProjection(width, height, FOV, NEAR_PLANE, FAR_PLANE);
    }

    public Matrix4f getProjection()
    {
        return this.projection;
    }

}
