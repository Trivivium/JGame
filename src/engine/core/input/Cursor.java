package engine.core.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Cursor extends GLFWCursorPosCallback
{
    private static double x  = 0;
    private static double y  = 0;
    private static double dx = 0;
    private static double dy = 0;

    @Override
    public void invoke(long window, double xpos, double ypos)
    {
        /*
         * Calculate delta position
         */
        dx = xpos - x;
        dy = ypos - y;

        /*
         * Update new position
         */
        x = xpos;
        y = ypos;
    }

    public void update()
    {
        dx = 0;
        dy = 0;
    }

    public static double getX()
    {
        return x;
    }

    public static double getY()
    {
        return y;
    }

    public static double getDX()
    {
        return dx;
    }

    public static double getDY()
    {
        return dy;
    }
}
