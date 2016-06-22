package core.math;

public class VectorUtils
{
    public static float dot(Vector2f a, Vector2f b)
    {
        return a.x * b.x + a.y * b.y;
    }

    public static float dot(Vector3f a, Vector3f b)
    {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static float cross(Vector2f a, Vector2f b)
    {
        return a.x * b.y - a.y * b.x;
    }

    public static Vector3f cross(Vector3f a, Vector3f b)
    {
        float x = a.y * b.z - a.z * b.y;
        float y = a.z * b.x - a.x * b.z;
        float z = a.x * b.y - a.y * b.x;

        return new Vector3f(x, y, z);
    }

    public Vector2f rotate(Vector2f src, float angle)
    {
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        float x = (float) (src.x * cos - src.y * sin);
        float y = (float) (src.x * sin - src.y * cos);

        return new Vector2f(x, y);
    }

    public Vector3f rotate(Vector3f src, Vector3f axis, float angle)
    {
        float sin = (float) Math.sin(-angle);
        float cos = (float) Math.cos(-angle);

        axis.multiply(sin).add((src.multiply(cos)).add(axis.multiply(VectorUtils.dot(src, axis.multiply(1 - cos)))));

        return VectorUtils.cross(src, axis);
    }

    public Vector3f rotate(Vector3f src, Quaternion rotation)
    {
        Quaternion conjugate = rotation.conjugate();
        Quaternion result    = rotation.multiply(src).multiply(conjugate);

        return new Vector3f(result.x, result.y, result.z);
    }

    public float angle(Vector2f a, Vector2f b)
    {
        float dls = dot(a, b) / (a.length() * b.length());

        if(dls < -1.0f)
        {
            dls = -1.0f;
        }
        else if(dls > 1.0f)
        {
            dls = 1.0f;
        }

        return (float) Math.toDegrees(Math.acos(dls));
    }

    public static float angle(Vector3f a, Vector3f b)
    {
        float dls = dot(a, b) / (a.length() * b.length());

        if(dls < -1.0f)
        {
            dls = -1.0f;
        }
        else if(dls > 1.0f)
        {
            dls = 1.0f;
        }

        return (float) Math.toDegrees(Math.acos(dls));
    }

    public Vector2f lerp(Vector2f a, Vector2f b, float factor)
    {
        return b.subtract(a).multiply(factor).add(a);
    }

    public Vector3f lerp(Vector3f a, Vector3f b, float factor)
    {
        return b.subtract(a).multiply(factor).add(a);
    }

    public Vector2f abs(Vector2f src)
    {
        float x = Math.abs(src.x);
        float y = Math.abs(src.y);

        return new Vector2f(x, y);
    }

    public Vector3f abs(Vector3f src)
    {
        float x = Math.abs(src.x);
        float y = Math.abs(src.y);
        float z = Math.abs(src.z);

        return new Vector3f(x, y, z);
    }
}
