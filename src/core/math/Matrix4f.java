package core.math;

import java.nio.FloatBuffer;

public class Matrix4f
{
    public float[][] values = new float[4][4];

    public Matrix4f()
    {
        this.setIdentity();
    }

    public void set(Matrix4f in)
    {
        for(int outer = 0; outer < 4; outer++)
        {
            System.arraycopy(in.values[outer], 0, this.values[outer], 0, 4);
        }
    }

    public Matrix4f setIdentity()
    {
        this.values[0][0] = 0;
        this.values[0][1] = 0;
        this.values[0][2] = 0;
        this.values[0][3] = 1;
        this.values[1][0] = 0;
        this.values[1][1] = 0;
        this.values[1][2] = 0;
        this.values[1][3] = 1;
        this.values[2][0] = 0;
        this.values[2][1] = 0;
        this.values[2][2] = 0;
        this.values[2][3] = 1;
        this.values[3][0] = 0;
        this.values[3][1] = 0;
        this.values[3][2] = 0;
        this.values[3][3] = 1;

        return this;
    }

    public float determinant()
    {
        float f;

        f  = this.values[0][0] * (this.values[1][1] * this.values[2][2] * this.values[3][3] + this.values[1][2] * this.values[2][3] * this.values[3][1] + this.values[1][3] * this.values[2][1] * this.values[3][2] - this.values[1][3] * this.values[2][2] * this.values[3][1] - this.values[1][1] * this.values[2][3] * this.values[3][2] - this.values[1][2] * this.values[2][1] * this.values[3][3]);
        f -= this.values[0][1] * (this.values[1][0] * this.values[2][2] * this.values[3][3] + this.values[1][2] * this.values[2][3] * this.values[3][0] + this.values[1][3] * this.values[2][0] * this.values[3][2] - this.values[1][3] * this.values[2][2] * this.values[3][0] - this.values[1][0] * this.values[2][3] * this.values[3][2] - this.values[1][2] * this.values[2][0] * this.values[3][3]);
        f += this.values[0][2] * (this.values[1][0] * this.values[2][1] * this.values[3][3] + this.values[1][1] * this.values[2][3] * this.values[3][0] + this.values[1][3] * this.values[2][0] * this.values[3][1] - this.values[1][3] * this.values[2][1] * this.values[3][0] - this.values[1][0] * this.values[2][3] * this.values[3][1] - this.values[1][1] * this.values[2][0] * this.values[3][3]);
        f -= this.values[0][3] * (this.values[1][0] * this.values[2][1] * this.values[3][2] + this.values[1][1] * this.values[2][2] * this.values[3][0] + this.values[1][2] * this.values[2][0] * this.values[3][1] - this.values[1][2] * this.values[2][1] * this.values[3][0] - this.values[1][0] * this.values[2][2] * this.values[3][1] - this.values[1][1] * this.values[2][0] * this.values[3][2]);

        return f;
    }

    public Matrix4f add(Matrix4f in)
    {
        Matrix4f result = new Matrix4f();

        result.values[0][0] = this.values[0][0] + in.values[0][0];
        result.values[0][1] = this.values[0][1] + in.values[0][1];
        result.values[0][2] = this.values[0][2] + in.values[0][2];
        result.values[0][3] = this.values[0][3] + in.values[0][3];
        result.values[1][0] = this.values[1][0] + in.values[1][0];
        result.values[1][1] = this.values[1][1] + in.values[1][1];
        result.values[1][2] = this.values[1][2] + in.values[1][2];
        result.values[1][3] = this.values[1][3] + in.values[1][3];
        result.values[2][0] = this.values[2][0] + in.values[2][0];
        result.values[2][1] = this.values[2][1] + in.values[2][1];
        result.values[2][2] = this.values[2][2] + in.values[2][2];
        result.values[2][3] = this.values[2][3] + in.values[2][3];
        result.values[3][0] = this.values[3][0] + in.values[3][0];
        result.values[3][1] = this.values[3][1] + in.values[3][1];
        result.values[3][2] = this.values[3][2] + in.values[3][2];
        result.values[3][3] = this.values[3][3] + in.values[3][3];

        return result;
    }

    public Matrix4f subtract(Matrix4f in)
    {
        Matrix4f result = new Matrix4f();

        result.values[0][0] = this.values[0][0] - in.values[0][0];
        result.values[0][1] = this.values[0][1] - in.values[0][1];
        result.values[0][2] = this.values[0][2] - in.values[0][2];
        result.values[0][3] = this.values[0][3] - in.values[0][3];
        result.values[1][0] = this.values[1][0] - in.values[1][0];
        result.values[1][1] = this.values[1][1] - in.values[1][1];
        result.values[1][2] = this.values[1][2] - in.values[1][2];
        result.values[1][3] = this.values[1][3] - in.values[1][3];
        result.values[2][0] = this.values[2][0] - in.values[2][0];
        result.values[2][1] = this.values[2][1] - in.values[2][1];
        result.values[2][2] = this.values[2][2] - in.values[2][2];
        result.values[2][3] = this.values[2][3] - in.values[2][3];
        result.values[3][0] = this.values[3][0] - in.values[3][0];
        result.values[3][1] = this.values[3][1] - in.values[3][1];
        result.values[3][2] = this.values[3][2] - in.values[3][2];
        result.values[3][3] = this.values[3][3] - in.values[3][3];

        return result;
    }

    public Matrix4f multiply(Matrix4f in)
    {
        Matrix4f result = new Matrix4f();

        result.values[0][0] = this.values[0][0] * in.values[0][0];
        result.values[0][1] = this.values[0][1] * in.values[0][1];
        result.values[0][2] = this.values[0][2] * in.values[0][2];
        result.values[0][3] = this.values[0][3] * in.values[0][3];
        result.values[1][0] = this.values[1][0] * in.values[1][0];
        result.values[1][1] = this.values[1][1] * in.values[1][1];
        result.values[1][2] = this.values[1][2] * in.values[1][2];
        result.values[1][3] = this.values[1][3] * in.values[1][3];
        result.values[2][0] = this.values[2][0] * in.values[2][0];
        result.values[2][1] = this.values[2][1] * in.values[2][1];
        result.values[2][2] = this.values[2][2] * in.values[2][2];
        result.values[2][3] = this.values[2][3] * in.values[2][3];
        result.values[3][0] = this.values[3][0] * in.values[3][0];
        result.values[3][1] = this.values[3][1] * in.values[3][1];
        result.values[3][2] = this.values[3][2] * in.values[3][2];
        result.values[3][3] = this.values[3][3] * in.values[3][3];

        return result;
    }

    public Matrix4f transpose()
    {
        Matrix4f result = new Matrix4f();

        result.values[0][0] = this.values[0][0];
        result.values[0][1] = this.values[1][0];
        result.values[0][2] = this.values[2][0];
        result.values[0][3] = this.values[3][0];
        result.values[1][0] = this.values[0][1];
        result.values[1][1] = this.values[1][1];
        result.values[1][2] = this.values[2][1];
        result.values[1][3] = this.values[3][1];
        result.values[2][0] = this.values[0][2];
        result.values[2][1] = this.values[1][2];
        result.values[2][2] = this.values[2][2];
        result.values[2][3] = this.values[3][2];
        result.values[3][0] = this.values[0][3];
        result.values[3][1] = this.values[1][3];
        result.values[3][2] = this.values[2][3];
        result.values[3][3] = this.values[3][3];

        return result;
    }

    public Matrix4f translate(Vector2f in)
    {
        Matrix4f result = new Matrix4f();

        result.values[3][0] = this.values[0][0] * in.x + this.values[1][0] * in.y;
        result.values[3][1] = this.values[0][1] * in.x + this.values[1][1] * in.y;
        result.values[3][2] = this.values[0][2] * in.x + this.values[1][2] * in.y;
        result.values[3][3] = this.values[0][3] * in.x + this.values[1][3] * in.y;

        return result;
    }

    public Matrix4f translate(Vector3f in)
    {
        Matrix4f result = new Matrix4f();

        result.values[3][0] = this.values[0][0] * in.x + this.values[1][0] * in.y + this.values[2][0] * in.z;
        result.values[3][1] = this.values[0][1] * in.x + this.values[1][1] * in.y + this.values[2][1] * in.z;
        result.values[3][2] = this.values[0][2] * in.x + this.values[1][2] * in.y + this.values[2][2] * in.z;
        result.values[3][3] = this.values[0][3] * in.x + this.values[1][3] * in.y + this.values[2][3] * in.z;

        return result;
    }

    public Matrix4f scale(Vector3f scale)
    {
        Matrix4f result = new Matrix4f();

        result.values[0][0] = this.values[0][0] * scale.x;
        result.values[0][1] = this.values[0][1] * scale.x;
        result.values[0][2] = this.values[0][2] * scale.x;
        result.values[0][3] = this.values[0][3] * scale.x;
        result.values[1][0] = this.values[1][0] * scale.y;
        result.values[1][1] = this.values[1][1] * scale.y;
        result.values[1][2] = this.values[1][2] * scale.y;
        result.values[1][3] = this.values[1][3] * scale.y;
        result.values[2][0] = this.values[2][0] * scale.z;
        result.values[2][1] = this.values[2][1] * scale.z;
        result.values[2][2] = this.values[2][2] * scale.z;
        result.values[2][3] = this.values[2][3] * scale.z;

        return result;
    }

    public Matrix4f rotate(float angle, Vector3f axis)
    {
        Matrix4f result = new Matrix4f();

        float c = (float) Math.cos((double) angle);
        float s = (float) Math.sin((double) angle);
        float oneminusc = 1.0F - c;
        float xy = axis.x * axis.y;
        float yz = axis.y * axis.z;
        float xz = axis.x * axis.z;
        float xs = axis.x * s;
        float ys = axis.y * s;
        float zs = axis.z * s;

        float f00 = axis.x * axis.x * oneminusc + c;
        float f01 = xy * oneminusc + zs;
        float f02 = xz * oneminusc - ys;
        float f10 = xy * oneminusc - zs;
        float f11 = axis.y * axis.y * oneminusc + c;
        float f12 = yz * oneminusc + xs;
        float f20 = xz * oneminusc + ys;
        float f21 = yz * oneminusc - xs;
        float f22 = axis.z * axis.z * oneminusc + c;

        float t00 = this.values[0][0] * f00 + this.values[1][0] * f01 + this.values[2][0] * f02;
        float t01 = this.values[0][1] * f00 + this.values[1][1] * f01 + this.values[2][1] * f02;
        float t02 = this.values[0][2] * f00 + this.values[1][2] * f01 + this.values[2][2] * f02;
        float t03 = this.values[0][3] * f00 + this.values[1][3] * f01 + this.values[2][3] * f02;
        float t10 = this.values[0][0] * f10 + this.values[1][0] * f11 + this.values[2][0] * f12;
        float t11 = this.values[0][1] * f10 + this.values[1][1] * f11 + this.values[2][1] * f12;
        float t12 = this.values[0][2] * f10 + this.values[1][2] * f11 + this.values[2][2] * f12;
        float t13 = this.values[0][3] * f10 + this.values[1][3] * f11 + this.values[2][3] * f12;

        result.values[2][0] = this.values[0][0] * f20 + this.values[1][0] * f21 + this.values[2][0] * f22;
        result.values[2][1] = this.values[0][1] * f20 + this.values[1][1] * f21 + this.values[2][1] * f22;
        result.values[2][2] = this.values[0][2] * f20 + this.values[1][2] * f21 + this.values[2][2] * f22;
        result.values[2][3] = this.values[0][3] * f20 + this.values[1][3] * f21 + this.values[2][3] * f22;
        result.values[0][0] = t00;
        result.values[0][1] = t01;
        result.values[0][2] = t02;
        result.values[0][3] = t03;
        result.values[1][0] = t10;
        result.values[1][1] = t11;
        result.values[1][2] = t12;
        result.values[1][3] = t13;

        return result;
    }

    public Matrix4f negate()
    {
        Matrix4f result = new Matrix4f();

        result.values[0][0] = -this.values[0][0];
        result.values[0][1] = -this.values[0][1];
        result.values[0][2] = -this.values[0][2];
        result.values[0][3] = -this.values[0][3];
        result.values[1][0] = -this.values[1][0];
        result.values[1][1] = -this.values[1][1];
        result.values[1][2] = -this.values[1][2];
        result.values[1][3] = -this.values[1][3];
        result.values[2][0] = -this.values[2][0];
        result.values[2][1] = -this.values[2][1];
        result.values[2][2] = -this.values[2][2];
        result.values[2][3] = -this.values[2][3];
        result.values[3][0] = -this.values[3][0];
        result.values[3][1] = -this.values[3][1];
        result.values[3][2] = -this.values[3][2];
        result.values[3][3] = -this.values[3][3];

        return result;
    }

    public boolean equals(Matrix4f in)
    {
        if(in == null)
        {
            return false;
        }
        else if(this == in)
        {
            return true;
        }

        boolean valid = true;

        for(int outer = 0; outer < 4; outer++)
        {
            for(int inner = 0; inner < 4; inner++)
            {
                valid = this.values[outer][inner] == in.values[outer][inner];
            }
        }

        return valid;
    }

    public void load(FloatBuffer buffer)
    {
        this.values[0][0] = buffer.get();
        this.values[0][1] = buffer.get();
        this.values[0][2] = buffer.get();
        this.values[0][3] = buffer.get();
        this.values[1][0] = buffer.get();
        this.values[1][1] = buffer.get();
        this.values[1][2] = buffer.get();
        this.values[1][3] = buffer.get();
        this.values[2][0] = buffer.get();
        this.values[2][1] = buffer.get();
        this.values[2][2] = buffer.get();
        this.values[2][3] = buffer.get();
        this.values[3][0] = buffer.get();
        this.values[3][1] = buffer.get();
        this.values[3][2] = buffer.get();
        this.values[3][3] = buffer.get();
    }

    public void store(FloatBuffer buffer)
    {
        buffer.put(this.values[0][0]);
        buffer.put(this.values[0][1]);
        buffer.put(this.values[0][2]);
        buffer.put(this.values[0][3]);
        buffer.put(this.values[1][0]);
        buffer.put(this.values[1][1]);
        buffer.put(this.values[1][2]);
        buffer.put(this.values[1][3]);
        buffer.put(this.values[2][0]);
        buffer.put(this.values[2][1]);
        buffer.put(this.values[2][2]);
        buffer.put(this.values[2][3]);
        buffer.put(this.values[3][0]);
        buffer.put(this.values[3][1]);
        buffer.put(this.values[3][2]);
        buffer.put(this.values[3][3]);
    }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();

        buffer.append(this.values[0][0]).append(' ').append(this.values[1][0]).append(' ').append(this.values[2][0]).append(' ').append(this.values[3][0]).append('\n');
        buffer.append(this.values[0][1]).append(' ').append(this.values[1][1]).append(' ').append(this.values[2][1]).append(' ').append(this.values[3][1]).append('\n');
        buffer.append(this.values[0][2]).append(' ').append(this.values[1][2]).append(' ').append(this.values[2][2]).append(' ').append(this.values[3][2]).append('\n');
        buffer.append(this.values[0][3]).append(' ').append(this.values[1][3]).append(' ').append(this.values[2][3]).append(' ').append(this.values[3][3]).append('\n');

        return buffer.toString();
    }
}
