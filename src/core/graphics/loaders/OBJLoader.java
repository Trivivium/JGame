package core.graphics.loaders;

import core.graphics.models.Model;

import core.math.Vector2f;
import core.math.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader
{
    public static Model load(File file, Loader loader)
    {
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(file));
        }
        catch(IOException exception)
        {
            System.err.println("Could not load OBJ file: " + file.getPath());
            System.exit(-1);
        }

        int    lineno = 1;
        String line;

        List<Vector3f> vertices = new ArrayList<>();
        List<Vector2f> textures = new ArrayList<>();
        List<Vector3f> normals  = new ArrayList<>();
        List<Integer>  indices  = new ArrayList<>();

        float[] texturesArray = null;
        float[] normalsArray  = null;

        try
        {
            while((line = reader.readLine()) != null)
            {
                String[] current = line.split(" ");

                if(line.startsWith("v "))
                {
                    Vector3f vertex = new Vector3f(
                        Float.parseFloat(current[1]),
                        Float.parseFloat(current[2]),
                        Float.parseFloat(current[3])
                    );

                    vertices.add(vertex);
                }
                else if(line.startsWith("vt "))
                {
                    Vector2f texture = new Vector2f(
                        Float.parseFloat(current[1]),
                        Float.parseFloat(current[2])
                    );

                    textures.add(texture);
                }
                else if(line.startsWith("vn "))
                {
                    Vector3f vertex = new Vector3f(
                            Float.parseFloat(current[1]),
                            Float.parseFloat(current[2]),
                            Float.parseFloat(current[3])
                    );

                    normals.add(vertex);
                }
                else if(line.startsWith("f "))
                {
                    break;
                }

                lineno++;
            }

            texturesArray = new float[vertices.size() * 2];
            normalsArray  = new float[vertices.size() * 3];

            while((line = reader.readLine()) != null)
            {
                if(!line.startsWith("f "))
                {
                    continue;
                }

                String[] current = line.split(" ");

                processVertex(current[1].split("/"), indices, textures, normals, texturesArray, normalsArray);
                processVertex(current[2].split("/"), indices, textures, normals, texturesArray, normalsArray);
                processVertex(current[3].split("/"), indices, textures, normals, texturesArray, normalsArray);

                lineno++;
            }

            reader.close();
        }
        catch(Exception exception)
        {
            System.err.println("Error passing OBJ file on line " + lineno);
            exception.printStackTrace();
            System.exit(-1);
        }

        float[] verticesArray = new float[vertices.size() * 3];
        int[]   indicesArray  = new int[indices.size()];

        int pointer = 0;

        for(Vector3f vertex : vertices)
        {
            verticesArray[pointer++] = vertex.x;
            verticesArray[pointer++] = vertex.y;
            verticesArray[pointer++] = vertex.z;
        }

        for(int i = 0; i < indices.size(); i++)
        {
            indicesArray[i] = indices.get(i);
        }

        return loader.loadModel(verticesArray, texturesArray, normalsArray, indicesArray);
    }

    private static void processVertex(String[] data, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals, float[] textureArray, float[] normalsArray)
    {
        int pointer = Integer.parseInt(data[0]) - 1;

        indices.add(pointer);

        Vector2f texture = textures.get(Integer.parseInt(data[1]) - 1);

        /*
         * Negate the Y value to match OpenGL coordinate system.
         */
        textureArray[pointer * 2]     = texture.x;
        textureArray[pointer * 2 + 1] = 1 - texture.y;

        Vector3f normal = normals.get(Integer.parseInt(data[2]) - 1);

        normalsArray[pointer * 3]     = normal.x;
        normalsArray[pointer * 3 + 1] = normal.y;
        normalsArray[pointer * 3 + 2] = normal.z;
    }

}
