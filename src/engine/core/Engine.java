package engine.core;

import engine.core.graphics.Camera;
import engine.core.graphics.Display;
import engine.core.graphics.Projection;
import engine.entities.*;
import engine.core.graphics.models.TexturedModel;
import engine.core.graphics.renderers.EntityRenderer;

import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine
{
    private final float TARGET_UPDATES_PER_SECOND = 30f;

    private Timer   timer;
    private Display display;
    private Camera  camera;
    private Light   sun;

    private EntityRenderer renderer;

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();

    private List<Updatable> updatables = new ArrayList<>();
    private List<Inputable> inputables = new ArrayList<>();

    private Player player;

    private boolean running = false;

    public Engine(Display display, EntityRenderer entityRenderer, Camera camera, Timer timer)
    {
        this.display  = display;
        this.camera   = camera;
        this.renderer = entityRenderer;
        this.timer    = timer;
    }

    public void setPlayer(Player player)
    {
        /*
         * Store a reference to render the player.
         */
        this.player = player;
    }

    public static void setCulling(boolean active)
    {
        if(active)
        {
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glCullFace(GL11.GL_BACK);
            return;
        }

        GL11.glDisable(GL11.GL_CULL_FACE);
    }

    public void start()
    {
        this.inputables.add(player);
        this.updatables.add(player);
        this.inputables.add(camera);
        this.updatables.add(camera);

        this.display.show();
        this.running = true;
        this.loop();
    }

    public void stop()
    {
        this.running = false;
    }

    public void addSun(Light sun)
    {
        this.sun = sun;
    }

    public void addEntity(Entity entity)
    {
        TexturedModel model = entity.getTexturedModel();

        if(this.entities.containsKey(model))
        {
            this.entities.get(model).add(entity);
            return;
        }

        List<Entity> batch = new ArrayList<>();

        batch.add(entity);
        this.entities.put(model, batch);

        if(entity instanceof Updatable)
        {
            this.updatables.add((Updatable) entity);
        }

        if(entity instanceof Inputable)
        {
            this.inputables.add((Inputable) entity);
        }
    }

    public void cleanup()
    {
        this.renderer.cleanup();
        this.display.cleanup();
    }

    public void init(Projection projection)
    {
        this.timer.init();
        this.renderer.init(projection);

        /*
         * Enable depth testing
         */
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        /*
         * Enable transparency blending
         */
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        /*
         * Enable back-face culling of engine.entities
         */
        setCulling(true);
    }

    protected void loop()
    {
        float delta;
        float accumulator = 0f;
        float interval    = 1f / TARGET_UPDATES_PER_SECOND;

        while(this.running)
        {
            if(this.display.isCloseRequested())
            {
                this.stop();
            }

            delta        = this.timer.getElapsedTime();
            accumulator += delta;

            this.input();

            while(accumulator >= interval)
            {
                this.update();
                accumulator -= interval;
            }

            this.render(accumulator / interval);

            this.display.update();
            this.display.clear();
        }
    }

    protected void input()
    {
        for(int i = 0; i < this.inputables.size(); i++)
        {
            this.inputables.get(i).input();
        }
    }

    protected void update()
    {
        for(int i = 0; i < this.updatables.size(); i++)
        {
            this.updatables.get(i).update();
        }
    }

    protected void render(float alpha)
    {
        //System.out.println("Update completion: " + alpha);

        this.renderer.render(alpha, this.sun, this.camera, this.player);
        this.renderer.render(alpha, this.sun, this.camera, this.entities);
    }

}
