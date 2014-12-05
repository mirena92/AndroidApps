package android.hackbulgaria.com.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MyActivity extends Activity {

    Intent i;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        i = new Intent();
        editText = (EditText) findViewById(R.id.editText);
    }

    public void clickButtons(View view) {
        switch (view.getId()) {
            case R.id.btnDail:
                i.setAction(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + editText.getText()));
                break;
            case R.id.btnBrowse:
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://" + editText.getText()));
                break;
            case R.id.btnAlarm:
                i.setAction(AlarmClock.ACTION_SET_ALARM);
                setAlarm(editText.getText().toString());
        }
        startActivity(i);
    }

    private void setAlarm(String s) {
        String[] str = s.split("[:]");
        i.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(str[0]));
        i.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(str[1]));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
