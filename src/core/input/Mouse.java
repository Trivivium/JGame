package core.input;

import core.graphics.Display;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Mouse extends GLFWMouseButtonCallback
{
    private static boolean[] buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];

    @Override
    public void invoke(long window, int button, int action, int mods)
    {
        buttons[button] = action != GLFW_RELEASE;
    }

    public static boolean isButtonPressed(int button)
    {
        return buttons[button];
    }

    public static boolean isLeftButtonPressed()
    {
        return buttons[GLFW_MOUSE_BUTTON_LEFT];
    }

    public static boolean isRightButtonPressed()
    {
        return buttons[GLFW_MOUSE_BUTTON_RIGHT];
    }

}
