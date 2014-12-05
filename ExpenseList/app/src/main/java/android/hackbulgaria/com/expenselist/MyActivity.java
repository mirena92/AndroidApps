package android.hackbulgaria.com.expenselist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MyActivity extends Activity {

    MyAdapter adapter;
    EditText editLabel;
    EditText editPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        adapter = new MyAdapter();
        editLabel = (EditText) findViewById(R.id.editTextLabel);
        editPrice = (EditText) findViewById(R.id.editTextPrice);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add(new Expence(editLabel.getText().toString(), editPrice.getText().toString()));
                adapter.notifyDataSetChanged();
                editLabel.setText("");
                editPrice.setText("");
            }
        });
    }
}
