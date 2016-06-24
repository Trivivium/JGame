package engine.core;

public class Timer
{
    private float previous = 0f;

    public void init()
    {
        this.previous = this.getTime();
    }

    public float getTime()
    {
        return (float) (System.nanoTime() / 1000_000_000.0);
    }

    public float getElapsedTime()
    {
        float time  = this.getTime();
        float delta = (time - this.previous);

        this.previous = time;

        return delta;
    }

}
