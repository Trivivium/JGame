package engine.core.math;

import java.nio.FloatBuffer;

public class Vector3f
{
    public float x;
    public float y;
    public float z;

    public Vector3f(float x, float y, float z)
    {
        this.set(x, y, z);
    }

    public Vector3f(FloatBuffer buffer)
    {
        this.load(buffer);
    }

    public void set(Vector3f in)
    {
        this.x = in.x;
        this.y = in.y;
        this.z = in.z;
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f copy()
    {
        return new Vector3f(this.x, this.y , this.z);
    }

    /**
     * @return Returns the length of the vector.
     */
    public float length()
    {
        return (float) Math.sqrt(this.lengthSquared());
    }

    /**
     * @return Returns the squared length of the vector.
     */
    public float lengthSquared()
    {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    /**
     * Normalizes the vector.
     *
     * @return Returns a reference to the vector.
     */
    public Vector3f normalize()
    {
        float length = this.length();

        if(length == 0)
        {
            throw new IllegalStateException("Zero length vector");
        }

        this.x /= length;
        this.y /= length;
        this.z /= length;

        return this;
    }

    public Vector3f translate(Vector3f in)
    {
        this.x += in.x;
        this.y += in.y;
        this.z += in.z;

        return this;
    }

    public Vector3f translate(float x, float y, float z)
    {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public Vector3f negate()
    {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;

        return this;
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f add(Vector3f in)
    {
        this.x += in.x;
        this.y += in.y;
        this.z += in.z;

        return this;
    }

    /**
     * @param value A value to add to all vector elements.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f add(float value)
    {
        this.x += value;
        this.y += value;
        this.z += value;

        return this;
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector subtracted.
     */
    public Vector3f subtract(Vector3f in)
    {
        this.x -= in.x;
        this.y -= in.y;
        this.z -= in.z;

        return this;
    }

    /**
     * @param value A value to add to all vector elements.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f subtract(float value)
    {
        this.x -= value;
        this.y -= value;
        this.z -= value;

        return this;
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector multiplied.
     */
    public Vector3f multiply(Vector3f in)
    {
        this.x *= in.x;
        this.y *= in.y;
        this.z *= in.z;

        return this;
    }

    /**
     * @param factor A multiplication factor to multiply all vector elements with.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f multiply(float factor)
    {
        this.x *= factor;
        this.y *= factor;
        this.z *= factor;

        return this;
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector divided.
     */
    public Vector3f divide(Vector3f in)
    {
        this.x /= in.x;
        this.y /= in.y;
        this.z /= in.z;

        return this;
    }

    /**
     * @param divisor A divisor for all vector elements.
     *
     * @return Returns the new vector instance with the provided and current
     *         vector added together.
     */
    public Vector3f divide(float divisor)
    {
        this.x /= divisor;
        this.y /= divisor;
        this.z /= divisor;

        return this;
    }

    /**
     * Determines if two vectors are equal.
     *
     * @param in A vector to compare against.
     *
     * @return Return TRUE of both vectors has equal element m, FALSE otherwise.
     */
    public boolean equals(Vector3f in)
    {
        if(in == null || this.getClass() != in.getClass())
        {
            return false;
        }

        return this == in || this.x == in.x && this.y == in.y && this.z == in.z;
    }

    /**
     * Populates the vector with data from a float buffer.
     *
     * @param buffer A buffer instance.
     */
    public void load(FloatBuffer buffer)
    {
        this.x = buffer.get();
        this.y = buffer.get();
        this.z = buffer.get();
    }

    /**
     * Stores the vector into a float buffer
     *
     * @param buffer A buffer instance.
     */
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

