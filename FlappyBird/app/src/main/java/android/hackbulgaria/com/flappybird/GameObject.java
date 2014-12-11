package android.hackbulgaria.com.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public abstract class GameObject {
    private PointF position;
    private int width;
    private int height;

    public GameObject(PointF position) {
        this.position = position;
    }

    public PointF getPosition() {
        return position;
    }

    public abstract void setPosition();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void onClick() {

    }

    public void onGameEvent(GameEvent event) {

    }

    public abstract void onDraw(Canvas canvas);

}

