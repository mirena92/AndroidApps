package android.hackbulgaria.com.flappybird;



import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class Bird extends GameObject {

    private Bitmap birdBitmap;
    boolean isClicked = false;

    public Bird(Bitmap birdBitmap, PointF position) {
        super(position);
        this.birdBitmap = birdBitmap;
    }

    @Override
    public void setPosition() {
        if(isClicked==true) {
            getPosition().set(getPosition().x, (getPosition().y) + 3);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(birdBitmap, getPosition().x, getPosition().y, null);
    }

    @Override
    public void onClick() {
        getPosition().set(getPosition().x, (getPosition().y)-70);
        isClicked = true;
    }
}
