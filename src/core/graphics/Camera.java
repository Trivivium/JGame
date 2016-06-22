package core.graphics;

import core.entities.Inputable;
import core.entities.Player;
import core.entities.Updatable;
import core.math.MathUtils;
import core.input.Cursor;
import core.input.Mouse;
import core.input.MouseWheel;

import org.lwjgl.util.vector.Vector3f;

public class Camera implements Updatable, Inputable
{
    private Vector3f position;
    private Vector3f rotation;

    private float distance = 10f;
    private float angle    = 0f;
    private float pitch    = 0f;

    private Player player;

    public Camera(Vector3f position, Vector3f rotation)
    {
        this.position = position;
        this.rotation = rotation;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void input()
    {
        if(MouseWheel.isScrolling())
        {
            this.distance += -MouseWheel.getY();
        }

        if(Mouse.isRightButtonPressed())
        {
            this.angle += -Cursor.getDX() * 0.3f;
            this.pitch += Cursor.getDY() * 0.3f;
        }

        this.distance = MathUtils.clamp(this.distance, 3, 50);
        this.pitch    = MathUtils.clamp(this.pitch,    0, 50);
    }

    public void update()
    {
        if(MouseWheel.isScrolling())
        {
            this.distance += -MouseWheel.getY();
        }

        if(Mouse.isRightButtonPressed())
        {
            this.angle += -Cursor.getDX() * 0.3f;
            this.pitch += Cursor.getDY() * 0.3f;
        }

        this.distance = MathUtils.clamp(this.distance, 3, 50);
        this.pitch    = MathUtils.clamp(this.pitch,    0, 50);

        /*
         * The amount of degrees the user specified angle is corrected by
         * when moving.
         */
        final float ANGLE_RESET_INTERVAL = 4;

        /*
         * Ensure the angle around the Y axis is reset if the player has changed it
         * with mouse input. This effect is stopped if the played is currently changing
         * the angle.
         */
        if(this.player.isMoving() && this.angle != 0 && !Mouse.isRightButtonPressed())
        {
            if(Math.abs(0 - this.angle) < ANGLE_RESET_INTERVAL)
            {
                this.angle = 0;
            }
            else if(this.angle < 0)
            {
                this.angle += ANGLE_RESET_INTERVAL;
            }
            else
            {
                this.angle -= ANGLE_RESET_INTERVAL;
            }
        }

        float yaw = this.player.getRotation().y + this.angle;

        this.position.x = (float) (Math.sin(Math.toRadians(yaw))) * this.distance + this.player.getPosition().x;
        this.position.z = (float) (Math.cos(Math.toRadians(yaw))) * this.distance + this.player.getPosition().z;
        this.position.y = (float) (Math.sin(Math.toRadians(this.pitch))) * this.distance + this.player.getPosition().y;

        this.rotation.x = this.pitch;
        this.rotation.y = -yaw;
    }

    public Vector3f getPosition()
    {
        return this.position;
    }

    public Vector3f getRotation()
    {
        return rotation;
    }
}
