package android.hackbulgaria.com.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends ImageView implements GameClock.GameClockListener{

    List<GameObject> objectList = new ArrayList();

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for(GameObject object:objectList) {
                    object.onClick();
                    invalidate();
                }
            }
        });
    }

    @Override
    public void onGameEvent(GameEvent gameEvent) {
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Bitmap birdBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        Bitmap backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clouds);
        objectList.add(new Background(backgroundBitmap, new PointF(0, 0)));
        objectList.add(new Background(backgroundBitmap, new PointF(2300, 0)));
        objectList.add(new Bird(birdBitmap, new PointF(400, 350)));
        invalidate();
//        for(GameObject object: objectList) {
//            update position
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (GameObject object : objectList) {
            if (object.getPosition().x < -2000) {
                object.getPosition().set(0, 0);
            }
            object.setPosition();
            object.onDraw(canvas);
        }
    }
}
