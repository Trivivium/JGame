package engine.core.graphics.loaders;

import engine.core.graphics.models.Model;

import de.matthiasmann.twl.utils.PNGDecoder;

import org.lwjgl.opengl.*;
import org.lwjgl.BufferUtils;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import java.util.List;
import java.util.ArrayList;

public class Loader
{
    private List<Integer> vaos     = new ArrayList<>();
    private List<Integer> vbos     = new ArrayList<>();
    private List<Integer> textures = new ArrayList<>();

    public Model loadModel(float[] vertices, float[] textures, float[] normals, int[] indices)
    {
        int vaoID = this.createVAO();

        /*
         * Bind VAO instance.
         */
        GL30.glBindVertexArray(vaoID);

        this.bindIndices(indices);

        /*
         * Store passed vertex positions into a buffer.
         */
        this.bindData(0, 3, vertices);

        if(textures != null)
        {
            this.bindData(1, 2, textures);
        }

        if(normals != null)
        {
            this.bindData(2, 3, normals);
        }


        /*
         * Unbind VAO.
         */
        GL30.glBindVertexArray(0);

        return new Model(vaoID, indices.length);
    }

    public int loadTexture(File file)
    {
        int ID = 0;

        try
        {
            BufferedInputStream stream  = new BufferedInputStream(new FileInputStream(file));
            PNGDecoder          decoder = new PNGDecoder(stream);

            int width  = decoder.getWidth();
            int height = decoder.getHeight();

            ByteBuffer buffer = ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());

            decoder.decode(buffer, 4 * width, PNGDecoder.Format.RGBA);

            /*
             * Prepare the buffer for reading.
             */
            buffer.flip();

            ID = GL11.glGenTextures();

            GL13.glActiveTexture(ID);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, ID);

            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
            System.exit(-1);
        }

        this.textures.add(ID);
        return ID;
    }

    /**
     * Cleans up any bound VAOs, VBOs and loaded textures to ensure
     * proper memory management.
     */
    public void cleanup()
    {
        this.textures.forEach(GL11::glDeleteTextures);
        this.vaos.forEach(GL30::glDeleteVertexArrays);
        this.vbos.forEach(GL15::glDeleteBuffers);
    }

    /**
     * Creates a new VAO instance and stores the ID
     * for cleanup.
     *
     * @return The VAO ID.
     */
    private int createVAO()
    {
        /*
         * Create VAO and return its ID
         */
        int ID = GL30.glGenVertexArrays();

        /*
         * Store the VAO ID for cleanup when the application
         * closes.
         */
        this.vaos.add(ID);

        return ID;
    }

    /**
     * Creates a new VBO instance and stores the ID
     * for cleanup.
     *
     * @return The VBO ID.
     */
    private int createVBO()
    {
        /*
         * Create VBO and return its ID
         */
        int ID = GL15.glGenBuffers();

        /*
         * Store the VBO ID for cleanup when the application
         * closes.
         */
        this.vbos.add(ID);

        return ID;
    }

    private void bindIndices(int[] indices)
    {
        int ID = this.createVBO();

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ID);

        IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);

        buffer.put(indices);
        buffer.flip();

        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    /**
     * Stores the provided data into a VAO attribute list
     *
     * @param attribute An integer declaring the attribute list ID to bindData the buffer into
     * @param vertices  An array of floats representing vertex position coordinates
     */
    private void bindData(int attribute, int size, float[] vertices)
    {
        int ID = this.createVBO();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, ID);

        /*
         * Create new float buffer to bindData the data into
         */
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);

        /*
         * Store data into the buffer.
         */
        buffer.put(vertices);

        /*
         * Prepare the buffer to be read from. It is
         * currently expecting to be written to.
         */
        buffer.flip();

        /*
         * Bind buffer data to the VBO
         */
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

        /*
         * Bind the VBO to the currently bound VAO.
         *
         * Args:
         *  Attribute number
         *  Length of each vertex
         *  Data type
         *  Is normalized
         *  Distance between each vertex
         *  Offset
         */
        GL20.glVertexAttribPointer(attribute, size, GL11.GL_FLOAT, false, 0, 0);

        /*
         * Unbind buffer.
         */
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
}
