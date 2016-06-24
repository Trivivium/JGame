import engine.core.Engine;
import engine.core.Timer;
import engine.core.graphics.*;
import engine.core.graphics.Camera;
import engine.entities.Entity;
import engine.entities.Light;
import engine.entities.Player;

import engine.core.graphics.loaders.*;
import engine.core.graphics.models.*;

import engine.core.graphics.shaders.EntityShader;
import engine.core.graphics.renderers.EntityRenderer;

import engine.core.graphics.textures.ModelTexture;

import engine.core.math.Vector3f;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Launcher
{
    private static final int DISPLAY_WIDTH  = 1200;
    private static final int DISPLAY_HEIGHT = 720;

    public static void main(String[] args)
    {
        Display display = new Display("JGame Engine", DISPLAY_WIDTH, DISPLAY_HEIGHT);
        Loader  loader  = new Loader();

        Camera          camera     = new Camera(new Vector3f(0, 0, 10), new Vector3f(0, 0, 0));
        Projection      projection = new Projection(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        EntityRenderer  renderer   = new EntityRenderer(new EntityShader());

        Engine engine = new Engine(display, renderer, camera, new Timer());

        engine.init(projection);

        List<Entity>  entities = new ArrayList<>();

        Model        model   = OBJLoader.load(new File("resources/models/cube.obj"), loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture(new File("resources/textures/gold.png")));

        Light sun = new Light(new Vector3f(0, 40, 0), new Vector3f(1, 1, 1));

        TexturedModel block = new TexturedModel(model, texture);

        entities.add(new Entity(block, new Vector3f(-25, 0, -30)));
        entities.add(new Entity(block, new Vector3f( 25, 0, -30)));

        engine.addSun(sun);

        Player player = new Player(new TexturedModel(model,texture), new Vector3f(0, 0, 0));

        camera.setPlayer(player);
        engine.setPlayer(player);

        entities.forEach(engine::addEntity);

        engine.start();
        engine.cleanup();
        loader.cleanup();
    }
}
