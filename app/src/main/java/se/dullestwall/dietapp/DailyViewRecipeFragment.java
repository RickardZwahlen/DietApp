/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package se.dullestwall.dietapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Simple Fragment used to display some meaningful content for each page in the sample's
 * {@link android.support.v4.view.ViewPager}.
 */
public class DailyViewRecipeFragment extends Fragment {

    public static final String KEY_TITLE = "title";
    public static final String KEY_INDICATOR_COLOR = "indicator_color";
    public static final String KEY_DIVIDER_COLOR = "divider_color";
    private static final String KEY_IMAGEID = "imageID";

    private static String recName;
    private static String recImageID;
    private static HashMap<String, List<String>> recIngredient;
    private static List<String> recInstructions;
    View view;

    /**
     * @return a new instance of {@link se.dullestwall.dietapp.DailyViewRecipeFragment}, adding the parameters into a bundle and
     * setting them as arguments.
     */
    public static DailyViewRecipeFragment newInstance(CharSequence title, int indicatorColor,
            int dividerColor) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(KEY_TITLE, title);
        bundle.putInt(KEY_INDICATOR_COLOR, indicatorColor);
        bundle.putInt(KEY_DIVIDER_COLOR, dividerColor);

        DailyViewRecipeFragment fragment = new DailyViewRecipeFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daily_recipe, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle args = getArguments();

        try {
            recName = MainActivity.weekRecipes.get(args.getCharSequence(KEY_TITLE)).getName();
            recIngredient = MainActivity.weekRecipes.get(args.getCharSequence(KEY_TITLE)).getIngredients();
            recInstructions = MainActivity.weekRecipes.get(args.getCharSequence(KEY_TITLE)).getInstructions();
            recImageID = MainActivity.weekRecipes.get(args.getCharSequence(KEY_TITLE)).getImageID();
        } catch (NullPointerException e){

            recName = "Failed";
            recIngredient = MainActivity.recipes.get(0).getIngredients();
        }

        if (args != null) {
            int indicatorColor = args.getInt(KEY_INDICATOR_COLOR);

            ImageView pic = (ImageView) view.findViewById(R.id.ivDailyFood);
            int draw = getDrawable(recImageID);
            pic.setImageResource(draw);
            pic.setBackgroundColor(indicatorColor);

            //Shows Title (What day)
            TextView title = (TextView) view.findViewById(R.id.tvDailyRecipeName);
            title.setText(MainActivity.testTitle);//recName);
            title.setTextSize(22);

            //args.getCharSequence(KEY_TITLE)

            TextView IngHead = (TextView) view.findViewById(R.id.tvDailyIngredientHeader);
            IngHead.setText("Ingredients:");
            IngHead.setTextSize(18);

            CharSequence currIngredient;
            TextView ingredientView = (TextView) view.findViewById(R.id.tvDailyIngredients);
            ingredientView.setTextSize(16);
            currIngredient = ingredientView.getText();
            for(int i=0; i < recIngredient.get("iname").size(); i++)
            {
                ingredientView.setText(currIngredient + recIngredient.get("iquantity").get(i)+recIngredient.get("imeasurement").get(i)+" "+ recIngredient.get("iname").get(i)+"\n");
                currIngredient = ingredientView.getText();
            }

            TextView InsHead = (TextView) view.findViewById(R.id.tvDailyInstructionsHeader);
            InsHead.setText("Instructions:");
            InsHead.setTextSize(18);

            TextView InstructionsView = (TextView) view.findViewById(R.id.tvDailyInstructions);
            InstructionsView.setTextSize(16);
            CharSequence currInstructions;
            currInstructions = InstructionsView.getText();
            for(int i=0; i < recInstructions.size(); i++)
            {
                InstructionsView.setText(currInstructions +Integer.toString(i+1)+". "+ recInstructions.get(i)+"\n");
                currInstructions = InstructionsView.getText();
            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;

    }

    public int getDrawable(String name) {
        Context context = DailyViewRecipeFragment.this.getActivity().getApplicationContext();
        int resourceId = context.getResources().getIdentifier(name, "drawable", DailyViewRecipeFragment.this.getActivity().getApplicationContext().getPackageName());
        return resourceId;
    }
}
