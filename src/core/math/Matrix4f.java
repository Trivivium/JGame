package core.math;

import java.nio.FloatBuffer;

public class Matrix4f
{
    public float[][] m = new float[4][4];

    public Matrix4f()
    {
        this.setIdentity();
    }

    public Matrix4f(Matrix4f in)
    {
        this.set(in);
    }

    public Matrix4f(FloatBuffer buffer)
    {
        this.load(buffer);
    }

    public void set(Matrix4f in)
    {
        for(int outer = 0; outer < 4; outer++)
        {
            System.arraycopy(in.m[outer], 0, this.m[outer], 0, 4);
        }
    }

    public Matrix4f setIdentity()
    {
        this.m[0][0] = 1; this.m[0][1] = 0; this.m[0][2] = 0; this.m[0][3] = 0;
        this.m[1][0] = 0; this.m[1][1] = 1; this.m[1][2] = 0; this.m[1][3] = 0;
        this.m[2][0] = 0; this.m[2][1] = 0; this.m[2][2] = 1; this.m[2][3] = 0;
        this.m[3][0] = 0; this.m[3][1] = 0; this.m[3][2] = 0; this.m[3][3] = 1;

        return this;
    }

    public Matrix4f setZero()
    {
        this.m[0][0] = 0; this.m[0][1] = 0; this.m[0][2] = 0; this.m[0][3] = 0;
        this.m[1][0] = 0; this.m[1][1] = 0; this.m[1][2] = 0; this.m[1][3] = 0;
        this.m[2][0] = 0; this.m[2][1] = 0; this.m[2][2] = 0; this.m[2][3] = 0;
        this.m[3][0] = 0; this.m[3][1] = 0; this.m[3][2] = 0; this.m[3][3] = 0;

        return this;
    }

    public float determinant()
    {
        float f;

        f  = this.m[0][0] * (this.m[1][1] * this.m[2][2] * this.m[3][3] + this.m[1][2] * this.m[2][3] * this.m[3][1] + this.m[1][3] * this.m[2][1] * this.m[3][2] - this.m[1][3] * this.m[2][2] * this.m[3][1] - this.m[1][1] * this.m[2][3] * this.m[3][2] - this.m[1][2] * this.m[2][1] * this.m[3][3]);
        f -= this.m[0][1] * (this.m[1][0] * this.m[2][2] * this.m[3][3] + this.m[1][2] * this.m[2][3] * this.m[3][0] + this.m[1][3] * this.m[2][0] * this.m[3][2] - this.m[1][3] * this.m[2][2] * this.m[3][0] - this.m[1][0] * this.m[2][3] * this.m[3][2] - this.m[1][2] * this.m[2][0] * this.m[3][3]);
        f += this.m[0][2] * (this.m[1][0] * this.m[2][1] * this.m[3][3] + this.m[1][1] * this.m[2][3] * this.m[3][0] + this.m[1][3] * this.m[2][0] * this.m[3][1] - this.m[1][3] * this.m[2][1] * this.m[3][0] - this.m[1][0] * this.m[2][3] * this.m[3][1] - this.m[1][1] * this.m[2][0] * this.m[3][3]);
        f -= this.m[0][3] * (this.m[1][0] * this.m[2][1] * this.m[3][2] + this.m[1][1] * this.m[2][2] * this.m[3][0] + this.m[1][2] * this.m[2][0] * this.m[3][1] - this.m[1][2] * this.m[2][1] * this.m[3][0] - this.m[1][0] * this.m[2][2] * this.m[3][1] - this.m[1][1] * this.m[2][0] * this.m[3][2]);

        return f;
    }

    public Matrix4f add(Matrix4f in)
    {
        this.m[0][0] += in.m[0][0];
        this.m[0][1] += in.m[0][1];
        this.m[0][2] += in.m[0][2];
        this.m[0][3] += in.m[0][3];
        this.m[1][0] += in.m[1][0];
        this.m[1][1] += in.m[1][1];
        this.m[1][2] += in.m[1][2];
        this.m[1][3] += in.m[1][3];
        this.m[2][0] += in.m[2][0];
        this.m[2][1] += in.m[2][1];
        this.m[2][2] += in.m[2][2];
        this.m[2][3] += in.m[2][3];
        this.m[3][0] += in.m[3][0];
        this.m[3][1] += in.m[3][1];
        this.m[3][2] += in.m[3][2];
        this.m[3][3] += in.m[3][3];

        return this;
    }

    public Matrix4f subtract(Matrix4f in)
    {
        this.m[0][0] -= in.m[0][0];
        this.m[0][1] -= in.m[0][1];
        this.m[0][2] -= in.m[0][2];
        this.m[0][3] -= in.m[0][3];
        this.m[1][0] -= in.m[1][0];
        this.m[1][1] -= in.m[1][1];
        this.m[1][2] -= in.m[1][2];
        this.m[1][3] -= in.m[1][3];
        this.m[2][0] -= in.m[2][0];
        this.m[2][1] -= in.m[2][1];
        this.m[2][2] -= in.m[2][2];
        this.m[2][3] -= in.m[2][3];
        this.m[3][0] -= in.m[3][0];
        this.m[3][1] -= in.m[3][1];
        this.m[3][2] -= in.m[3][2];
        this.m[3][3] -= in.m[3][3];

        return this;
    }

    public Matrix4f multiply(Matrix4f in)
    {
        float m00 = this.m[0][0] * in.m[0][0] + this.m[1][0] * in.m[0][1] + this.m[2][0] * in.m[0][2] + this.m[3][0] * in.m[0][3];
        float m01 = this.m[0][1] * in.m[0][0] + this.m[1][1] * in.m[0][1] + this.m[2][1] * in.m[0][2] + this.m[3][1] * in.m[0][3];
        float m02 = this.m[0][2] * in.m[0][0] + this.m[1][2] * in.m[0][1] + this.m[2][2] * in.m[0][2] + this.m[3][2] * in.m[0][3];
        float m03 = this.m[0][3] * in.m[0][0] + this.m[1][3] * in.m[0][1] + this.m[2][3] * in.m[0][2] + this.m[3][3] * in.m[0][3];
        float m10 = this.m[0][0] * in.m[1][0] + this.m[1][0] * in.m[1][1] + this.m[2][0] * in.m[1][2] + this.m[3][0] * in.m[1][3];
        float m11 = this.m[0][1] * in.m[1][0] + this.m[1][1] * in.m[1][1] + this.m[2][1] * in.m[1][2] + this.m[3][1] * in.m[1][3];
        float m12 = this.m[0][2] * in.m[1][0] + this.m[1][2] * in.m[1][1] + this.m[2][2] * in.m[1][2] + this.m[3][2] * in.m[1][3];
        float m13 = this.m[0][3] * in.m[1][0] + this.m[1][3] * in.m[1][1] + this.m[2][3] * in.m[1][2] + this.m[3][3] * in.m[1][3];
        float m20 = this.m[0][0] * in.m[2][0] + this.m[1][0] * in.m[2][1] + this.m[2][0] * in.m[2][2] + this.m[3][0] * in.m[2][3];
        float m21 = this.m[0][1] * in.m[2][0] + this.m[1][1] * in.m[2][1] + this.m[2][1] * in.m[2][2] + this.m[3][1] * in.m[2][3];
        float m22 = this.m[0][2] * in.m[2][0] + this.m[1][2] * in.m[2][1] + this.m[2][2] * in.m[2][2] + this.m[3][2] * in.m[2][3];
        float m23 = this.m[0][3] * in.m[2][0] + this.m[1][3] * in.m[2][1] + this.m[2][3] * in.m[2][2] + this.m[3][3] * in.m[2][3];
        float m30 = this.m[0][0] * in.m[3][0] + this.m[1][0] * in.m[3][1] + this.m[2][0] * in.m[3][2] + this.m[3][0] * in.m[3][3];
        float m31 = this.m[0][1] * in.m[3][0] + this.m[1][1] * in.m[3][1] + this.m[2][1] * in.m[3][2] + this.m[3][1] * in.m[3][3];
        float m32 = this.m[0][2] * in.m[3][0] + this.m[1][2] * in.m[3][1] + this.m[2][2] * in.m[3][2] + this.m[3][2] * in.m[3][3];
        float m33 = this.m[0][3] * in.m[3][0] + this.m[1][3] * in.m[3][1] + this.m[2][3] * in.m[3][2] + this.m[3][3] * in.m[3][3];

        this.m[0][0] = m00;
        this.m[0][1] = m01;
        this.m[0][2] = m02;
        this.m[0][3] = m03;
        this.m[1][0] = m10;
        this.m[1][1] = m11;
        this.m[1][2] = m12;
        this.m[1][3] = m13;
        this.m[2][0] = m20;
        this.m[2][1] = m21;
        this.m[2][2] = m22;
        this.m[2][3] = m23;
        this.m[3][0] = m30;
        this.m[3][1] = m31;
        this.m[3][2] = m32;
        this.m[3][3] = m33;

        return this;
    }

    public Matrix4f transpose()
    {
        Matrix4f temporary = new Matrix4f();

        temporary.m[0][0] = this.m[0][0];
        temporary.m[0][1] = this.m[1][0];
        temporary.m[0][2] = this.m[2][0];
        temporary.m[0][3] = this.m[3][0];
        temporary.m[1][0] = this.m[0][1];
        temporary.m[1][1] = this.m[1][1];
        temporary.m[1][2] = this.m[2][1];
        temporary.m[1][3] = this.m[3][1];
        temporary.m[2][0] = this.m[0][2];
        temporary.m[2][1] = this.m[1][2];
        temporary.m[2][2] = this.m[2][2];
        temporary.m[2][3] = this.m[3][2];
        temporary.m[3][0] = this.m[0][3];
        temporary.m[3][1] = this.m[1][3];
        temporary.m[3][2] = this.m[2][3];
        temporary.m[3][3] = this.m[3][3];

        this.set(temporary);

        return this;
    }

    public Matrix4f translate(Vector2f in)
    {
        this.m[3][0] += this.m[0][0] * in.x + this.m[1][0] * in.y;
        this.m[3][1] += this.m[0][1] * in.x + this.m[1][1] * in.y;
        this.m[3][2] += this.m[0][2] * in.x + this.m[1][2] * in.y;
        this.m[3][3] += this.m[0][3] * in.x + this.m[1][3] * in.y;

        return this;
    }

    public Matrix4f translate(Vector3f in)
    {
        this.m[3][0] += this.m[0][0] * in.x + this.m[1][0] * in.y + this.m[2][0] * in.z;
        this.m[3][1] += this.m[0][1] * in.x + this.m[1][1] * in.y + this.m[2][1] * in.z;
        this.m[3][2] += this.m[0][2] * in.x + this.m[1][2] * in.y + this.m[2][2] * in.z;
        this.m[3][3] += this.m[0][3] * in.x + this.m[1][3] * in.y + this.m[2][3] * in.z;

        return this;
    }

    public Matrix4f scale(Vector3f scale)
    {
        this.m[0][0] *= scale.x;
        this.m[0][1] *= scale.x;
        this.m[0][2] *= scale.x;
        this.m[0][3] *= scale.x;
        this.m[1][0] *= scale.y;
        this.m[1][1] *= scale.y;
        this.m[1][2] *= scale.y;
        this.m[1][3] *= scale.y;
        this.m[2][0] *= scale.z;
        this.m[2][1] *= scale.z;
        this.m[2][2] *= scale.z;
        this.m[2][3] *= scale.z;

        return this;
    }

    public Matrix4f rotate(float angle, Vector3f axis)
    {
        float cos = (float) Math.cos((double) angle);
        float sin = (float) Math.sin((double) angle);
        float oneminusc = 1.0f - cos;
        float xy = axis.x * axis.y;
        float yz = axis.y * axis.z;
        float xz = axis.x * axis.z;
        float xs = axis.x * sin;
        float ys = axis.y * sin;
        float zs = axis.z * sin;

        float f00 = axis.x * axis.x * oneminusc + cos;
        float f01 = xy * oneminusc + zs;
        float f02 = xz * oneminusc - ys;
        float f10 = xy * oneminusc - zs;
        float f11 = axis.y * axis.y * oneminusc + cos;
        float f12 = yz * oneminusc + xs;
        float f20 = xz * oneminusc + ys;
        float f21 = yz * oneminusc - xs;
        float f22 = axis.z * axis.z * oneminusc + cos;

        float t00 = this.m[0][0] * f00 + this.m[1][0] * f01 + this.m[2][0] * f02;
        float t01 = this.m[0][1] * f00 + this.m[1][1] * f01 + this.m[2][1] * f02;
        float t02 = this.m[0][2] * f00 + this.m[1][2] * f01 + this.m[2][2] * f02;
        float t03 = this.m[0][3] * f00 + this.m[1][3] * f01 + this.m[2][3] * f02;
        float t10 = this.m[0][0] * f10 + this.m[1][0] * f11 + this.m[2][0] * f12;
        float t11 = this.m[0][1] * f10 + this.m[1][1] * f11 + this.m[2][1] * f12;
        float t12 = this.m[0][2] * f10 + this.m[1][2] * f11 + this.m[2][2] * f12;
        float t13 = this.m[0][3] * f10 + this.m[1][3] * f11 + this.m[2][3] * f12;

        this.m[2][0] = this.m[0][0] * f20 + this.m[1][0] * f21 + this.m[2][0] * f22;
        this.m[2][1] = this.m[0][1] * f20 + this.m[1][1] * f21 + this.m[2][1] * f22;
        this.m[2][2] = this.m[0][2] * f20 + this.m[1][2] * f21 + this.m[2][2] * f22;
        this.m[2][3] = this.m[0][3] * f20 + this.m[1][3] * f21 + this.m[2][3] * f22;
        this.m[0][0] = t00;
        this.m[0][1] = t01;
        this.m[0][2] = t02;
        this.m[0][3] = t03;
        this.m[1][0] = t10;
        this.m[1][1] = t11;
        this.m[1][2] = t12;
        this.m[1][3] = t13;

        return this;
    }

    public Matrix4f negate()
    {
        this.m[0][0] = -this.m[0][0];
        this.m[0][1] = -this.m[0][1];
        this.m[0][2] = -this.m[0][2];
        this.m[0][3] = -this.m[0][3];
        this.m[1][0] = -this.m[1][0];
        this.m[1][1] = -this.m[1][1];
        this.m[1][2] = -this.m[1][2];
        this.m[1][3] = -this.m[1][3];
        this.m[2][0] = -this.m[2][0];
        this.m[2][1] = -this.m[2][1];
        this.m[2][2] = -this.m[2][2];
        this.m[2][3] = -this.m[2][3];
        this.m[3][0] = -this.m[3][0];
        this.m[3][1] = -this.m[3][1];
        this.m[3][2] = -this.m[3][2];
        this.m[3][3] = -this.m[3][3];

        return this;
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
                valid = this.m[outer][inner] == in.m[outer][inner];
            }
        }

        return valid;
    }

    public void load(FloatBuffer buffer)
    {
        this.m[0][0] = buffer.get();
        this.m[0][1] = buffer.get();
        this.m[0][2] = buffer.get();
        this.m[0][3] = buffer.get();
        this.m[1][0] = buffer.get();
        this.m[1][1] = buffer.get();
        this.m[1][2] = buffer.get();
        this.m[1][3] = buffer.get();
        this.m[2][0] = buffer.get();
        this.m[2][1] = buffer.get();
        this.m[2][2] = buffer.get();
        this.m[2][3] = buffer.get();
        this.m[3][0] = buffer.get();
        this.m[3][1] = buffer.get();
        this.m[3][2] = buffer.get();
        this.m[3][3] = buffer.get();
    }

    public void store(FloatBuffer buffer)
    {
        buffer.put(this.m[0][0]);
        buffer.put(this.m[0][1]);
        buffer.put(this.m[0][2]);
        buffer.put(this.m[0][3]);
        buffer.put(this.m[1][0]);
        buffer.put(this.m[1][1]);
        buffer.put(this.m[1][2]);
        buffer.put(this.m[1][3]);
        buffer.put(this.m[2][0]);
        buffer.put(this.m[2][1]);
        buffer.put(this.m[2][2]);
        buffer.put(this.m[2][3]);
        buffer.put(this.m[3][0]);
        buffer.put(this.m[3][1]);
        buffer.put(this.m[3][2]);
        buffer.put(this.m[3][3]);
    }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();

        buffer.append(this.m[0][0]).append(' ').append(this.m[1][0]).append(' ').append(this.m[2][0]).append(' ').append(this.m[3][0]).append('\n');
        buffer.append(this.m[0][1]).append(' ').append(this.m[1][1]).append(' ').append(this.m[2][1]).append(' ').append(this.m[3][1]).append('\n');
        buffer.append(this.m[0][2]).append(' ').append(this.m[1][2]).append(' ').append(this.m[2][2]).append(' ').append(this.m[3][2]).append('\n');
        buffer.append(this.m[0][3]).append(' ').append(this.m[1][3]).append(' ').append(this.m[2][3]).append(' ').append(this.m[3][3]).append('\n');

        return buffer.toString();
    }
}
