package se.dullestwall.dietapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ShoppingListAdapter extends BaseAdapter {

    public static HashMap<String, List<String>> total;
//private HashMap<String,String> element;

    private HashMap<String, List<String>> _data;
    Context _c;
    int _size;

    ShoppingListAdapter(HashMap<String, List<String>> data, Context c, int size) {
        _data = data;
        _c = c;
        _size = 7;//_more.get("iname").size();

    }

    public int getCount() {
        return _data.get("iname").size();
    }

    public Object getItem(int position) {
        return _data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(R.layout.shopping_list_item, null);

        TextView shoppName = (TextView) v.findViewById(R.id.tvShoppItemName);
        TextView shopQuantity = (TextView) v.findViewById(R.id.tvShoppItemAmount);
        TextView shopMeasure = (TextView) v.findViewById(R.id.tvShoppItemMeasurement);
        //TextView recName = (TextView)v.findViewById(R.id.tvListReceiptName);

        //  HashMap<String, String> msg = _data.get(position);

        _data.get("iname").get(0);
        shopQuantity.setText(_data.get("iquantity").get(position));
        shopMeasure.setText(_data.get("imeasurement").get(position));
        shoppName.setText(_data.get("iname").get(position));

        return v;
    }
}

