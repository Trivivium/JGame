package core.math;

import java.nio.FloatBuffer;

public class Vector2f
{
    public float x;
    public float y;

    public Vector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2f in)
    {
        this.x = in.x;
        this.y = in.y;
    }

    /**
     * @return Returns the length of the vector.
     */
    public float length()
    {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * @return Returns the squared length of the vector.
     */
    public float lengthSquared()
    {
        return this.x * this.x + this.y * this.y;
    }

    /**
     * @return Returns a new vector instance with normalized values.
     */
    public Vector2f normalize()
    {
        float length = this.length();

        if(length == 0)
        {
            throw new IllegalStateException("Zero length vector");
        }

        return new Vector2f(this.x / length, this.y / length);
    }

    /**
     * @param in A vector to calculate against.
     *
     * @return Returns the dot product between two vectors.
     */
    public float dot(Vector2f in)
    {
        return this.x * in.x + this.y * in.y;
    }

    /**
     * @param in A vector to calculate against.
     *
     * @return Returns a angle between two vectors in degrees.
     */
    public float angle(Vector2f in)
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
    public float cross(Vector2f in)
    {
        return this.x * in.y - this.y * in.x;
    }

    public void translate(float x, float y)
    {
        this.x += x;
        this.y += y;
    }

    public Vector2f negate()
    {
        return new Vector2f(-this.x, -this.y);
    }

    /**
     * @param angle An angle in degrees to rotate to vector.
     *
     * @return Returns a new Vector rotated the specified angle.
     */
    public Vector2f rotate(float angle)
    {
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        return new Vector2f((float) (this.x * cos - this.y * sin), (float) (this.x * sin + this.y * cos));
    }

    /**
     * @param in     A vector to calculate against.
     * @param factor A interpolation factor.
     *
     * @return Returns the resulting interpolated vector.
     */
    public Vector2f interpolate(Vector2f in, float factor)
    {
        return in.subtract(this).multiply(factor).add(this);
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector2f add(Vector2f in)
    {
        return new Vector2f(this.x + in.x, this.y + in.y);
    }

    /**
     * @param value A value to add to all vector elements.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector2f add(float value)
    {
        return new Vector2f(this.x + value, this.y + value);
    }


    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector subtracted.
     */
    public Vector2f subtract(Vector2f in)
    {
        return new Vector2f(this.x - in.x, this.y - in.y);
    }

    /**
     * @param value A value to add to all vector elements.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector2f subtract(float value)
    {
        return new Vector2f(this.x - value, this.y - value);
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector multiplied.
     */
    public Vector2f multiply(Vector2f in)
    {
        return new Vector2f(this.x * in.x, this.y * in.y);
    }

    /**
     * @param factor A multiplication factor to multiply all vector elements with.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector2f multiply(float factor)
    {
        return new Vector2f(this.x * factor, this.y * factor);
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector divided.
     */
    public Vector2f divide(Vector2f in)
    {
        return new Vector2f(this.x / in.x, this.y / in.y);
    }

    /**
     * @param divisor A divisor for all vector elements.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector2f divide(float divisor)
    {
        return new Vector2f(this.x / divisor, this.y / divisor);
    }

    /**
     * @return Returns a new vector instance with the current vector
     *         as absolute values.
     */
    public Vector2f abs()
    {
        return new Vector2f(Math.abs(this.x), Math.abs(this.y));
    }

    /**
     * Determines if two vectors are equal.
     *
     * @param in A vector to compare against.
     *
     * @return Return TRUE of both vectors has equal element values, FALSE otherwise.
     */
    public boolean equals(Vector2f in)
    {
        if(in == null)
        {
            return false;
        }

        return this == in || this.x == in.x && this.y == in.y;
    }

    public void load(FloatBuffer buffer)
    {
        this.x = buffer.get();
        this.y = buffer.get();
    }

    public void store(FloatBuffer buffer)
    {
        buffer.put(this.x);
        buffer.put(this.y);
    }

    /**
     * @return Returns a string representation of the vector as: (x,y)
     */
    public String toString()
    {
        return "(" + this.x + "," + this.y + ")";
    }
}
