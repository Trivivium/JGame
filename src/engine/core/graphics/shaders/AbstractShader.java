package engine.core.graphics.shaders;

import engine.core.graphics.Camera;
import engine.entities.Light;

import engine.core.math.Matrix4f;
import engine.core.math.Vector3f;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.HashMap;
import java.nio.FloatBuffer;

public abstract class AbstractShader
{

    private int ID;
    private int vertexID;
    private int fragmentID;

    private final File vertex;
    private final File fragment;

    protected final HashMap<String, Integer> locations = new HashMap<>();

    /**
     * Stores a buffer for when uploading matrices to shader programs.
     */
    private final FloatBuffer buffer = BufferUtils.createFloatBuffer(16);

    public AbstractShader(File vertex, File fragment)
    {
        this.vertex   = vertex;
        this.fragment = fragment;
    }

    public void init()
    {
        this.ID         = GL20.glCreateProgram();
        this.vertexID   = this.load(this.vertex,   GL20.GL_VERTEX_SHADER);
        this.fragmentID = this.load(this.fragment, GL20.GL_FRAGMENT_SHADER);

        GL20.glAttachShader(this.ID, this.vertexID);
        GL20.glAttachShader(this.ID, this.fragmentID);

        this.bindAttributes();

        GL20.glLinkProgram(this.ID);
        GL20.glValidateProgram(this.ID);

        this.loadUniformLocations();
    }

    public void bind()
    {
        GL20.glUseProgram(this.ID);
    }

    public void unbind()
    {
        GL20.glUseProgram(0);
    }

    public abstract void loadFakeLightingConfiguration(boolean conf);

    public abstract void loadShine(float shine, float reflectivity);

    public abstract void loadLight(Light light);

    public abstract void loadView(Camera camera);

    public abstract void loadProjectionMatrix(Matrix4f matrix);

    public abstract void loadTransformationMatrix(Matrix4f matrix);

    public void cleanup()
    {
        this.unbind();

        GL20.glDetachShader(this.ID, this.vertexID);
        GL20.glDetachShader(this.ID, this.fragmentID);

        GL20.glDeleteShader(this.vertexID);
        GL20.glDeleteShader(this.fragmentID);

        GL20.glDeleteProgram(this.ID);
    }

    public abstract void enableVertexAttributes();

    public abstract void disableVertexAttributes();

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variable)
    {
        GL20.glBindAttribLocation(this.ID, attribute, variable);
    }

    protected abstract void loadUniformLocations();

    protected int loadUniformLocation(String name)
    {
        if(this.locations.containsKey(name))
        {
            return this.locations.get(name);
        }

        int ID = GL20.glGetUniformLocation(this.ID, name);

        this.locations.put(name, ID);

        return ID;
    }

    protected void setUniform(int location, int value)
    {
        GL20.glUniform1i(location, value);
    }

    protected void setUniform(int location, float value)
    {
        GL20.glUniform1f(location, value);
    }

    protected void setUniform(int location, Vector3f vector)
    {
        GL20.glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void setUniform(int location, boolean value)
    {
        float payload = 0f;

        if(value)
        {
            payload = 1f;
        }

        this.setUniform(location, payload);
    }

    protected void setUniform(int location, Matrix4f matrix)
    {
        matrix.store(this.buffer);
        this.buffer.flip();

        GL20.glUniformMatrix4fv(location, false, this.buffer);
    }

    private int load(File file, int type)
    {
        BufferedReader reader;
        StringBuilder  source = new StringBuilder();
        String         line;

        try
        {
            reader = new BufferedReader(new FileReader(file));

            while((line = reader.readLine()) != null)
            {
                source.append(line).append("\n");
            }

            reader.close();
        }
        catch(IOException exception)
        {
            System.err.println("Cannot load shader source file: " + file.getPath());
            exception.printStackTrace();
            System.exit(-1);
        }

        int id = GL20.glCreateShader(type);

        GL20.glShaderSource(id, source);
        GL20.glCompileShader(id);

        if(GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
        {
            System.err.println(GL20.glGetShaderInfoLog(id, 500));
            System.err.println("Could not compile shader program");
            System.exit(-1);
        }

        return id;
    }

}


























