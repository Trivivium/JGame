package core.graphics;

import core.input.Cursor;
import core.input.Keyboard;

import core.input.Mouse;
import core.input.MouseWheel;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display
{
    private long window;

    private GLFWErrorCallback      errorCallback;
    private GLFWWindowSizeCallback windowSizeCallback;

    private Keyboard   keyboard;
    private Mouse      mouse;
    private MouseWheel mouseWheel;
    private Cursor     cursor;

    private int width;
    private int height;

    public Display(String title, int width, int height)
    {
        this.width  = width;
        this.height = height;

        glfwSetErrorCallback(this.errorCallback = GLFWErrorCallback.createPrint(System.err));

        if(glfwInit() == GLFW_FALSE)
        {
            throw new IllegalStateException("Failed to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE,   GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 4);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        this.window = glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);

        if(this.window == NULL)
        {
            throw new IllegalStateException("Failed to create GLFW window");
        }

        glfwSetWindowSizeCallback(this.window, this.windowSizeCallback = new GLFWWindowSizeCallback()
        {
            @Override
            public void invoke(long window, int width, int height)
            {
                Display.this.width   = width;
                Display.this.height  = height;

                GL11.glViewport(0, 0, width, height);
            }
        });

        /*
         * Load keyboard callback handler
         */
        glfwSetKeyCallback(this.window, this.keyboard = new Keyboard());
        glfwSetScrollCallback(this.window, this.mouseWheel = new MouseWheel());
        glfwSetMouseButtonCallback(this.window, this.mouse = new Mouse());
        glfwSetCursorPosCallback(this.window, this.cursor = new Cursor());

        glfwMakeContextCurrent(this.window);

        /*
         * Create OpenGL bindings.
         */
        GL.createCapabilities();

        GLFWVidMode mode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        /*
         * Center the window
         */
        glfwSetWindowPos(this.window,
            (mode.width() - width) / 2,
            (mode.height() - height) / 2
        );

        GL11.glViewport(0, 0, width, height);

        /*
         * Enable v-sync
         */
        glfwSwapInterval(1);

        /*
         * Sets the default background color.
         *
         * Color: Light Slate Gray
         */
        glClearColor(0.5f, 0.5f, 0.5f, 1);
    }

    public void show()
    {
        glfwShowWindow(this.window);
    }

    public void clear()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void update()
    {
        /*
         * Reset mouse wheel before polling for input events.
         */
        this.cursor.update();
        this.mouseWheel.update();

        glfwSwapBuffers(this.window);
        glfwPollEvents();
    }

    public boolean isCloseRequested()
    {
        return glfwWindowShouldClose(window) == GLFW_TRUE;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void cleanup()
    {
        this.keyboard.release();
        this.mouseWheel.release();
        this.mouse.release();
        this.cursor.release();

        this.windowSizeCallback.release();
        this.errorCallback.release();

        glfwDestroyWindow(window);
        glfwTerminate();
    }
}