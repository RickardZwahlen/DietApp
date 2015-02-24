package se.dullestwall.dietapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeImageAdapter extends BaseAdapter {
    // Keep all Images in array
    private final List<Item> mItems = new ArrayList<>();
    private final LayoutInflater mInflater;

    private boolean returnToWeekView = false;
    private String weekday;
    // Constructor
    public RecipeImageAdapter(Context c) {
        mInflater = LayoutInflater.from(c);

        for (Recipe r : MainActivity.recipes) {
            String name = r.getImageID();
            int id = c.getResources().getIdentifier(name, "drawable", c.getPackageName());
            mItems.add(new Item(r.getName(), id));
        }
    }

    public RecipeImageAdapter(Context c, String filter) {
        mInflater = LayoutInflater.from(c);

        for (Recipe r : MainActivity.recipes) {
            if (r.getDiets().contains(filter)) {
                String name = r.getImageID();
                int id = c.getResources().getIdentifier(name, "drawable", c.getPackageName());
                mItems.add(new Item(r.getName(), id));
            }
        }
    }

    public RecipeImageAdapter(Context c, String filter, String weekday) {
        mInflater = LayoutInflater.from(c);
        returnToWeekView=true;
        this.weekday=weekday;
        for (Recipe r : MainActivity.recipes) {
            if (filter.equals("") ||
                    r.getDiets().contains(filter)) {
                String name = r.getImageID();
                int id = c.getResources().getIdentifier(name, "drawable", c.getPackageName());
                mItems.add(new Item(r.getName(), id));
            }
        }
    }

    public int getCount() {
        return mItems.size();
    }

    public Item getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return mItems.get(position).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.recipe_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.findViewById(R.id.picture);
        name = (TextView) v.findViewById(R.id.text);

        final Item item = getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeDetailFragment recipeFragment = new RecipeDetailFragment();
                Bundle args = new Bundle();
                args.putCharSequence(RecipeDetailFragment.ARG_NAME, item.name);
                if(returnToWeekView)
                {
                    args.putCharSequence(RecipeDetailFragment.ARG_WEEKDAY, weekday);
                }
                recipeFragment.setArguments(args);
                FragmentTransaction transaction = ((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, recipeFragment);
                transaction.commit();
            }
        });

        return v;
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}