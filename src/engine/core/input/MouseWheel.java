package engine.core.input;

import org.lwjgl.glfw.GLFWScrollCallback;

public class MouseWheel extends GLFWScrollCallback
{
    private static float dx = 0f;
    private static float dy = 0f;

    @Override
    public void invoke(long window, double x, double y)
    {
        MouseWheel.dx = (float) x;
        MouseWheel.dy = (float) y;
    }

    public void update()
    {
        dx = 0f;
        dy = 0f;
    }

    public static boolean isScrolling()
    {
        return dy != 0;
    }

    public static float getY()
    {
        return dy;
    }

}
