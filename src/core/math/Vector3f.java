package core.math;

import java.nio.FloatBuffer;

public class Vector3f
{
    public float x;
    public float y;
    public float z;

    public Vector3f(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Vector3f in)
    {
        this.x = in.x;
        this.y = in.y;
        this.z = in.z;
    }

    /**
     * @return Returns the length of the vector.
     */
    public float length()
    {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * @return Returns the squared length of the vector.
     */
    public float lengthSquared()
    {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    /**
     * @return Returns a new vector instance with normalized values.
     */
    public Vector3f normalize()
    {
        float length = this.length();

        if(length == 0)
        {
            throw new IllegalStateException("Zero length vector");
        }

        return new Vector3f(this.x / length, this.y / length, this.z / length);
    }

    /**
     * @param in A vector to calculate against.
     *
     * @return Returns the dot product between two vectors.
     */
    public float dot(Vector3f in)
    {
        return this.x * in.x + this.y * in.y + this.z * in.z;
    }

    /**
     * @param in A vector to calculate against.
     *
     * @return Returns a angle between two vectors in degrees.
     */
    public float angle(Vector3f in)
    {
        float dls = this.dot(in) / (this.length() * in.length());

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

    /**
     * @param in A vector to calculate against.
     *
     * @return Returns the cross product between two vectors.
     */
    public Vector3f cross(Vector3f in)
    {
        float x = this.y * in.z - this.z * in.y;
        float y = this.z * in.x - this.x * in.z;
        float z = this.x * in.y - this.y * in.x;

        return new Vector3f(x, y, z);
    }

    public void translate(float x, float y, float z)
    {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public Vector3f negate()
    {
        return new Vector3f(-this.x, -this.y, -this.z);
    }

    public Vector3f rotate(Vector3f axis, float angle)
    {
        float sin = (float) Math.sin(-angle);
        float cos = (float) Math.cos(-angle);

        axis.multiply(sin).add((this.multiply(cos)).add(axis.multiply(this.dot(axis.multiply(1 - cos)))));

        return this.cross(axis);
    }

    public Vector3f rotate(Quaternion rotation)
    {
        Quaternion conjugate = rotation.conjugate();
        Quaternion result    = rotation.multiply(this).multiply(conjugate);

        return new Vector3f(result.x, result.y, result.z);
    }

    /**
     * @param in     A vector to calculate against.
     * @param factor A interpolation factor.
     *
     * @return Returns the resulting interpolated vector.
     */
    public Vector3f interpolate(Vector3f in, float factor)
    {
        return in.subtract(this).multiply(factor).add(this);
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f add(Vector3f in)
    {
        return new Vector3f(this.x + in.x, this.y + in.y, this.z + in.z);
    }

    /**
     * @param value A value to add to all vector elements.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f add(float value)
    {
        return new Vector3f(this.x + value, this.y + value, this.z + value);
    }


    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector subtracted.
     */
    public Vector3f subtract(Vector3f in)
    {
        return new Vector3f(this.x - in.x, this.y - in.y, this.z - in.z);
    }

    /**
     * @param value A value to add to all vector elements.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f subtract(float value)
    {
        return new Vector3f(this.x - value, this.y - value, this.z - value);
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector multiplied.
     */
    public Vector3f multiply(Vector3f in)
    {
        return new Vector3f(this.x * in.x, this.y * in.y, this.z * in.z);
    }

    /**
     * @param factor A multiplication factor to multiply all vector elements with.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f multiply(float factor)
    {
        return new Vector3f(this.x * factor, this.y * factor, this.z * factor);
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector divided.
     */
    public Vector3f divide(Vector3f in)
    {
        return new Vector3f(this.x / in.x, this.y / in.y, this.z / in.z);
    }

    /**
     * @param divisor A divisor for all vector elements.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f divide(float divisor)
    {
        return new Vector3f(this.x / divisor, this.y / divisor, this.z / divisor);
    }

    /**
     * @return Returns a new vector instance with the current vector
     *         as absolute values.
     */
    public Vector3f abs()
    {
        return new Vector3f(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    /**
     * Determines if two vectors are equal.
     *
     * @param in A vector to compare against.
     *
     * @return Return TRUE of both vectors has equal element values, FALSE otherwise.
     */
    public boolean equals(Vector3f in)
    {
        if(in == null)
        {
            return false;
        }

        return this == in || this.x == in.x && this.y == in.y && this.z == in.z;
    }

    public void load(FloatBuffer buffer)
    {
        this.x = buffer.get();
        this.y = buffer.get();
        this.z = buffer.get();
    }

    public void store(FloatBuffer buffer)
    {
        buffer.put(this.x);
        buffer.put(this.y);
        buffer.put(this.z);
    }

    /**
     * @return Returns a string representation of the vector as: (x,y)
     */
    public String toString()
    {
        return "(" + this.x + "," + this.y + "," + this.z + ")";
    }
}

