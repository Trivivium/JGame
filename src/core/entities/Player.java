package core.entities;

import core.graphics.models.TexturedModel;
import core.input.Keyboard;
import core.math.Vector3f;

import org.lwjgl.glfw.GLFW;

public class Player extends Entity implements Updatable, Inputable
{
    private static final float SPEED_RUN  = 0.5f;
    private static final float SPEED_TURN = 3f;

    private float currentRunSpeed  = 0;
    private float currentTurnSpeed = 0;

    public Player(TexturedModel model, Vector3f position)
    {
        super(model, position);
    }

    public void input()
    {
        if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_W))
        {
            this.currentRunSpeed = -SPEED_RUN;
        }
        else if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_S))
        {
            this.currentRunSpeed = SPEED_RUN;
        }
        else
        {
            this.currentRunSpeed = 0;
        }

        if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_A))
        {
            this.currentTurnSpeed = SPEED_TURN;
        }
        else if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_D))
        {
            this.currentTurnSpeed = -SPEED_TURN;
        }
        else
        {
            this.currentTurnSpeed = 0;
        }
    }

    public void update()
    {
        float ry = this.currentTurnSpeed;

        super.setRotation(0, ry);

        float  dx = (float) (this.currentRunSpeed * Math.sin(Math.toRadians(super.getRotation().y)));
        float  dz = (float) (this.currentRunSpeed * Math.cos(Math.toRadians(super.getRotation().y)));

        super.setPosition(dx, this.getPosition().y, dz);
    }

    public boolean isMoving()
    {
        return this.currentRunSpeed != 0;
    }
}
