package se.dullestwall.dietapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Rickard on 2015-02-10.
 */
public class ListAdapter extends ArrayAdapter<String> {
    Context context;
    String[] array;
    public ListAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
    }

    public ListAdapter(Context context, int resource1, int resource2, String[] array) {
        super(context, resource1, resource2, array);
        this.array=array;
        this.context=context;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        //här när man startar aktiviteten
        //2. Expandablelayoutlistview r 64
        //3. expandablelayoutitem r 188
        View v = convertView;
        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.view_row, null);

//            v.setOnClickListener(new View.OnClickListener()
//                                 {

//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }

        String p = getItem(position);

        if (p != null) {
            ImageView iv = (ImageView) v.findViewById(R.id.rowImageView);
            String url = "http://square.github.io/picasso/static/debug.png";
            Picasso.with(context).load(url).resize(300, 200).into(iv);
            TextView tv = (TextView) v.findViewById(R.id.header_text);
            tv.setText(array[position]);
            TextView tv2 = (TextView) v.findViewById(R.id.rowTextView);
            tv2.setText("This is " + Integer.toString(position) + "\nLorem ipsum blablabla nginaoenguin aengunsegon songonsengoinsoe ngnsegnsensenn ns ngsen sen gns ngsn grsn nsonho isnhiod thkmthkmt ho noidrnhoi nroh nrdion nrh nkn");



        }

        return v;

    }
}
