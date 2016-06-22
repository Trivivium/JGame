package core.math;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class MathUtils
{
    public static float clamp(float value, float lower, float upper)
    {
        if(value < lower)
        {
            return lower;
        }
        else if(value > upper)
        {
            return upper;
        }

        return value;
    }

    public static Matrix4f createView(Vector3f position, Vector3f rotation)
    {
        Matrix4f view = new Matrix4f();
        view.setIdentity();

        view.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0));
        view.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0));

        view.translate(new Vector3f(-position.x, -position.y, -position.z));

        return view;
    }

    public static Matrix4f createProjection(float width, float height, float fov, float near, float far)
    {
        float aspect  = width / height;
        float frustum = far - near;

        float y = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) / aspect);
        float x = (float) (1f / Math.tan(Math.toRadians(fov / 2f)));

        Matrix4f projection = new Matrix4f();

        projection.m00 = x;
        projection.m11 = y;

        projection.m22 = -(far + near) / frustum;
        projection.m23 = -1;

        projection.m32 = -(2 * near * far) / frustum;
        projection.m33 = 0;

        return projection;
    }

    public static Matrix4f createTransformation(Vector3f translation, Vector3f rotation, Vector3f scale)
    {
        Matrix4f transformation = new Matrix4f();

        transformation.translate(translation);
        transformation.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0));
        transformation.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
        transformation.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1));
        transformation.scale(scale);

        return transformation;
    }
}
