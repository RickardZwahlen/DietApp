package se.dullestwall.dietapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thedazzler.droidicon.IconicFontDrawable;

import java.util.ArrayList;

/**
 * Created by Rickard on 2015-02-10.
 */
public class ListAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> array;
    public ListAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
    }

    public ListAdapter(Context context, int resource1, int resource2, ArrayList<String> array) {
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
            String url = "http://images.media-allrecipes.com/userphotos/140x140/00/86/93/869323.jpg";
            String imageId =MainActivity.weekRecipes.get(array.get(position)).getImageID();
            int imageIntId = context.getResources().getIdentifier(imageId, "drawable", context.getApplicationContext().getPackageName());
            Picasso.with(context).load(imageIntId).resize(200, 200).into(iv);
            TextView tv = (TextView) v.findViewById(R.id.header_text);
            String dishName= MainActivity.weekRecipes.get(array.get(position)).getName();
            tv.setText(array.get(position) + "      " + dishName);
            TextView tv2 = (TextView) v.findViewById(R.id.rowTextView);
//            tv2.setText("Strawberry Goat Cheese Bruschetta\n" +
//                  "    Heat vinegar in a small skillet over medium-low heat. Simmer until reduced by about half, 8 to 10 minutes. Remove from heat and allow to cool to room temperature.\n" +
//                    "    Prepare a grill for high heat. Place bread slices on a foil-lined baking sheet and drizzle with olive oil.\n" +
//                    "    Combine strawberries and thyme in a small bowl and set aside.\n" +
//                    "    Grill bread on the preheated grill until browned, about 3 minutes per side.\n" +
//                    "    Spread goat cheese on toasted bread. Add black pepper, salt, and reduced vinegar to the strawberry mixture. Spoon over the goat cheese topped bruschetta. Garnish with additional thyme.\n");
            tv2.setText(MainActivity.weekRecipes.get(array.get(position)).getDescription());
            ImageView searchButton =(ImageView)v.findViewById(R.id.searchButton);
            final int viewPosition = position;
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecipesFragment recipesFragment = new RecipesFragment();
                    Bundle args = new Bundle();
                    args.putString(WeekListFragment.ARG_WEEKDAY, array.get(viewPosition));
                    recipesFragment.setArguments(args);
                    FragmentTransaction transaction = ((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, recipesFragment);
                    transaction.commit();
                }
            });
//            ImageView iconView1 = (ImageView) v.findViewById(R.id.imageView);

//            IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(context);
//            iconicFontDrawable.setIcon("gmd-search");
//            iconicFontDrawable.setIconColor(context.getResources().getColor(R.color.light_blue));

//            iconView1.setImageDrawable(iconicFontDrawable);
//            iconView1.setMaxWidth(500);
//            iconView1.setMaxHeight(500);

//            ImageView iconView2 = (ImageView) v.findViewById(R.id.rowButton);



//            IconicFontDrawable iconicFontDrawable2 = new IconicFontDrawable(context);
//            iconicFontDrawable2.setIcon("fa-random");
//            iconicFontDrawable.setIconColor(context.getResources().getColor(R.color.light_blue));

//            iconView2.setBackground(iconicFontDrawable2);
//            iconView2.setMaxWidth(500);
//            iconView2.setMaxHeight(500);



        }

        return v;

    }
}
