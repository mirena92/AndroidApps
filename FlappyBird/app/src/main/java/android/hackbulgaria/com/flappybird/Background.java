package android.hackbulgaria.com.flappybird;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class Background extends GameObject {

    private Bitmap backgroundBitmap;

    public Background(Bitmap backgroundBitmap, PointF position) {
        super(position);
        this.backgroundBitmap = backgroundBitmap;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(backgroundBitmap, getPosition().x, getPosition().y, null);
    }

    @Override
    public void setPosition() {
        getPosition().set((getPosition().x)-10, getPosition().y);
    }

    @Override
    public void onClick() {

    }
}
