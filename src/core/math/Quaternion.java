package core.math;

import java.nio.FloatBuffer;

public class Quaternion
{
    public float x;
    public float y;
    public float z;
    public float w;

    public Quaternion(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion(Vector3f axis, float angle)
    {
        float sin = (float) Math.sin(angle / 2);
        float cos = (float) Math.cos(angle / 2);

        this.x = axis.x * sin;
        this.y = axis.y * sin;
        this.z = axis.z * sin;
        this.w = cos;
    }

    public float length()
    {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }

    public Quaternion normalize()
    {
        float length = this.length();

        return new Quaternion(this.x / length, this.y / length, this.z / length, this.w / length);
    }

    public Quaternion conjugate()
    {
        return new Quaternion(-this.x, -this.y, -this.z, this.w);
    }

    public float dot(Quaternion in)
    {
        return this.x * in.x + this.y * in.y + this.z * in.z + this.w * in.w;
    }

    public Quaternion add(Quaternion in)
    {
        return new Quaternion(this.x + in.x, this.y + in.y, this.z + in.z, this.w + in.w);
    }

    public Quaternion subtract(Quaternion in)
    {
        return new Quaternion(this.x - in.x, this.y - in.y, this.z - in.z, this.w - in.w);
    }

    public Quaternion multiply(float factor)
    {
        return new Quaternion(this.x * factor, this.y * factor, this.z * factor, this.w * factor);
    }

    public Quaternion multiply(Quaternion in)
    {
        float w = this.w * in.w - this.x * in.x - this.y * in.y - this.z * in.z;
        float x = this.x * in.w + this.w * in.x + this.y * in.z - this.z * in.y;
        float y = this.y * in.w + this.w * in.y + this.z * in.x - this.x * in.z;
        float z = this.z * in.w + this.w * in.z + this.x * in.y - this.y * in.x;

        return new Quaternion(x, y, z, w);
    }

    public Quaternion multiply(Vector3f in)
    {
        float w_ = -this.x * in.x - this.y * in.y - this.z * in.z;
        float x_ =  this.w * in.x + this.y * in.z - this.z * in.y;
        float y_ =  this.w * in.y + this.z * in.x - this.x * in.z;
        float z_ =  this.w * in.z + this.x * in.y - this.y * in.x;

        return new Quaternion(x_, y_, z_, w_);
    }

    public Quaternion nlerp(Quaternion in, float factor, boolean shortest)
    {
        if(shortest && this.dot(in) < 0)
        {
            in = new Quaternion(-in.x, -in.y, -in.z, -in.w);
        }

        return in.subtract(this).multiply(factor).add(this).normalize();
    }

    public Quaternion slerp(Quaternion in, float factor, boolean shortest)
    {
        final float EPSILON = 1e3f;

        float      cos    = this.dot(in);
        Quaternion result = in;

        if(shortest && cos < 0)
        {
            cos    = -cos;
            result = new Quaternion(-in.x, -in.y, -in.z, -in.w);
        }

        if(Math.abs(cos) >= 1 - EPSILON)
        {
            return this.nlerp(result, factor, false);
        }

        float sin   = (float) Math.sqrt(1.0f - cos * cos);
        float angle = (float) Math.atan2(sin, cos);

        float srcFactor  = (float) Math.sin((1.0f - factor) * angle) * 1.0f / sin;
        float destFactor = (float) Math.sin((factor) * angle) * 1.0f / sin;

        return this.multiply(srcFactor).add(result.multiply(destFactor));
    }

    public boolean equals(Quaternion in)
    {
        return this.x == in.x && this.y == in.y && this.z == in.z && this.w == in.w;
    }

    public void load(FloatBuffer buffer)
    {
        this.x = buffer.get();
        this.y = buffer.get();
        this.z = buffer.get();
        this.w = buffer.get();
    }

    public void store(FloatBuffer buffer)
    {
        buffer.put(this.x);
        buffer.put(this.y);
        buffer.put(this.z);
        buffer.put(this.w);
    }

    public String toString() {
        return "(" + this.x + "," + this.y + "," + this.z + "," + this.w + ")";
    }
}
