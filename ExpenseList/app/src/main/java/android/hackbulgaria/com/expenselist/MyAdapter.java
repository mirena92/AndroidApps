package android.hackbulgaria.com.expenselist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RUSHI on 3.12.2014 г..
 */
public class MyAdapter extends BaseAdapter {

    private List<Expence> list = new ArrayList<Expence>();

    public void add(Expence expence) {
        list.add(expence);
    }

    private final static class ViewHolder {
        public TextView label;
        public TextView price;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        LinearLayout layout = null;
        ViewHolder viewHolder;
        if (convertView != null) {
            layout = (LinearLayout) convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            layout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view_item, null, false);
            TextView label = (TextView) layout.findViewById(R.id.txtLabel);
            TextView price = (TextView) layout.findViewById(R.id.txtPrice);
            ImageButton btn = (ImageButton) layout.findViewById(R.id.btnDelete);

            viewHolder = new ViewHolder();
            viewHolder.label = label;
            viewHolder.price = price;
            layout.setTag(viewHolder);

            btn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        Expence expence = (Expence) getItem(position);
        viewHolder.label.setText(expence.getLabel());
        viewHolder.price.setText(expence.getPrice());

        return layout;
    }
}
