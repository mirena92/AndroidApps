package android.hackbulgaria.com.downloadfiles;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyIntentService extends IntentService {

    public MyIntentService () {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        try {
            URL url = new URL(workIntent.getStringExtra("url"));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard, "filename.mp3");
            file.createNewFile();

            FileOutputStream fileOutput = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();

            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                fileOutput.write(buffer, 0, bufferLength);
            }

            //novIntent s ekstra i action, activity za nego se subsribe

            Intent newIntent = new Intent("OurAction");
            newIntent.putExtra("complete", true);
            sendBroadcast(newIntent);
            fileOutput.close();
            stopSelf();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
