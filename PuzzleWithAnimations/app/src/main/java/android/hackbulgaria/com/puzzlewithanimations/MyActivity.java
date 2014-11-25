package android.hackbulgaria.com.puzzlewithanimations;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyActivity extends Activity {

    private GridLayout fatherLayout;
    private List<ImageView> sorted;
    private List<ImageView> shuffled;
    private List<ImageView> currentImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final TypedArray pictures = getResources().obtainTypedArray(R.array.splitPictures);

        sorted = new ArrayList();
        shuffled = new ArrayList();

        for (int i = 0; i < 16; i++) {
            ImageView image = new ImageView(this);
            image.setImageDrawable(pictures.getDrawable(i));
            image.setTag(new Integer(i));
            sorted.add(image);
            shuffled.add(image);
        }

        Collections.shuffle(shuffled);

        fatherLayout = (GridLayout) findViewById(R.id.fatherLayout);
        fatherLayout.setColumnCount(4);
        fatherLayout.setRowCount(4);

        for (int i = 0; i < 16; i++) {
            fatherLayout.addView(shuffled.get(i));
        }

        for (final ImageView draggedImage : shuffled) {
            draggedImage.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    draggedImage.startDrag(null, new View.DragShadowBuilder(draggedImage), draggedImage, 0);
                    for (final ImageView droppedImage : shuffled) {
                        droppedImage.setOnDragListener(new View.OnDragListener() {
                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                if (event.getAction() == DragEvent.ACTION_DROP) {
                                    swapImages(draggedImage, droppedImage);
                                    if(isWin()) {
                                        Toast.makeText(MyActivity.this, "You win!", Toast.LENGTH_LONG).show();
                                    }
                                }
                                return true;
                            }
                        });
                    }
                    return true;
                }
            });
        }
    }

    public void swapImages(ImageView view1, ImageView view2) {
        view1.animate().x(view2.getX()).y(view2.getY()).setDuration(700).start();
        view2.animate().x(view1.getX()).y(view1.getY()).setDuration(700).start();

        int index1=0;
        int index2=0;
        for(int i=0; i<16; i++) {
            if(shuffled.get(i).equals(view1)) {
                index1=i;
            }
            if(shuffled.get(i).equals(view2)) {
                index2=i;
            }
        }
        shuffled.set(index1, view2);
        shuffled.set(index2, view1);
    }

    public boolean isWin() {

        for (int i = 0; i < 16; i++) {
            if (!(((Integer) shuffled.get(i).getTag()).equals(new Integer(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
