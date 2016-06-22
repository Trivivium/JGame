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

    public Vector2f(FloatBuffer buffer)
    {
        this.load(buffer);
    }

    public void set(Vector2f in)
    {
        this.x = in.x;
        this.y = in.y;
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2f copy()
    {
        return new Vector2f(this.x, this.y);
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
        return this.x * this.x + this.y * this.y;
    }

    /**
     * Normalizes the vector.
     *
     * @return Returns a reference to the vector.
     */
    public Vector2f normalize()
    {
        float length = this.length();

        if(length == 0)
        {
            throw new IllegalStateException("Zero length vector");
        }

        this.x /= length;
        this.y /= length;

        return this;
    }

    public Vector2f translate(Vector2f in)
    {
        this.x += in.x;
        this.y += in.y;

        return this;
    }

    public Vector2f translate(float x, float y)
    {
        this.x += x;
        this.y += y;

        return this;
    }

    public Vector2f negate()
    {
        this.x = -this.x;
        this.y = -this.y;

        return this;
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns a reference to the vector with each value add the
     *         corresponding value in the provided vector.
     */
    public Vector2f add(Vector2f in)
    {
        this.x += in.x;
        this.y += in.y;

        return this;
    }

    /**
     * @param value A value to add to all vector elements.
     *
     * @return Returns a reference to the vector with all values added the
     *         specified value.
     */
    public Vector2f add(float value)
    {
        this.x += value;
        this.y += value;

        return this;
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns a reference to the vector with each value subtracted
     *         by the corresponding value in the provided vector.
     */
    public Vector2f subtract(Vector2f in)
    {
        this.x -= in.x;
        this.y -= in.y;

        return this;
    }

    /**
     * @param value A value to add to all vector elements.
     *
     * @return Returns a reference to the vector with all values subtracted
     *         the specified value.
     */
    public Vector2f subtract(float value)
    {
        this.x -= value;
        this.y -= value;

        return this;
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns a reference to the vector with each value multiplied
     *         by the corresponding value in the provided vector.
     */
    public Vector2f multiply(Vector2f in)
    {
        this.x *= in.x;
        this.y *= in.y;

        return this;
    }

    /**
     * @param factor A multiplication factor to multiply all vector elements with.
     *
     * @return Returns a reference to the vector with all values multiplied
     *         by the given factor.
     */
    public Vector2f multiply(float factor)
    {
        this.x *= factor;
        this.y *= factor;

        return this;
    }

    /**
     * @param in A vector to add to this.
     *
     * @return Returns a reference to the vector with each value divided
     *         by the corresponding value on the provided vector.
     */
    public Vector2f divide(Vector2f in)
    {
        this.x /= in.x;
        this.y /= in.y;

        return this;
    }

    /**
     * @param divisor A divisor for all vector elements.
     *
     * @return Returns a reference to the vector with all values
     *         divided by the specified divisor.
     */
    public Vector2f divide(float divisor)
    {
        this.x /= divisor;
        this.y /= divisor;

        return this;
    }

    /**
     * Determines if two vectors are equal.
     *
     * @param in A vector to compare against.
     *
     * @return Return TRUE of both vectors has equal element m, FALSE otherwise.
     */
    public boolean equals(Vector2f in)
    {
        if(in == null || this.getClass() != in.getClass())
        {
            return false;
        }

        return this == in || this.x == in.x && this.y == in.y;
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
    }

    /**
     * @return Returns a string representation of the vector as: (x,y)
     */
    public String toString()
    {
        return "(" + this.x + "," + this.y + ")";
    }
}
