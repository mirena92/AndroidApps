package android.hackbulgaria.com.sensorfun;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class MyActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor proximitySensor;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        imageView = (ImageView) findViewById(R.id.imageView);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            double vector = Math.sqrt(sensorEvent.values[0]*sensorEvent.values[0] + sensorEvent.values[1]*sensorEvent.values[1]
                    + sensorEvent.values[2]*sensorEvent.values[2]);
            if(vector > 26) {
                startMusic(Sensor.TYPE_ACCELEROMETER);
                changeImage(Sensor.TYPE_ACCELEROMETER);
            }
        }

        if (mySensor.getType() == Sensor.TYPE_PROXIMITY) {
            if(sensorEvent.values[0]==0f) {
                startMusic(Sensor.TYPE_PROXIMITY);
                changeImage(Sensor.TYPE_PROXIMITY);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void startMusic(int i) {
        MediaPlayer mediaPlayer;
        if(i==Sensor.TYPE_ACCELEROMETER) {
            mediaPlayer = MediaPlayer.create(this, R.raw.ray_gun);
            mediaPlayer.start();
        }
        if(i==Sensor.TYPE_PROXIMITY) {
            mediaPlayer = MediaPlayer.create(this, R.raw.roar);
            mediaPlayer.start();
        }
    }

    public void changeImage(int i) {
        if(i==Sensor.TYPE_ACCELEROMETER) {
            imageView.setImageResource(R.drawable.gun);
        }
        if(i==Sensor.TYPE_PROXIMITY) {
            imageView.setImageResource(R.drawable.near);
        }
    }
}
