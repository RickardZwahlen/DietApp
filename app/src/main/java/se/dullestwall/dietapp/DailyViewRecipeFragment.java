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

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Simple Fragment used to display some meaningful content for each page in the sample's
 * {@link android.support.v4.view.ViewPager}.
 */
public class DailyViewRecipeFragment extends Fragment {

    private static final String KEY_TITLE = "title";
    private static final String KEY_INDICATOR_COLOR = "indicator_color";
    private static final String KEY_DIVIDER_COLOR = "divider_color";
    private static String recName;
    private static List<String> recIngredient;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

    try {
        recName = MainActivity.weekRecipes.get(args.getCharSequence(KEY_TITLE)).getName();
        recIngredient = MainActivity.weekRecipes.get(args.getCharSequence(KEY_TITLE)).getIngredients();
    }catch (NullPointerException e){
        recName = "Failed";
        recIngredient = MainActivity.recipes.get(0).getIngredients();
    }

        if (args != null) {
            int indicatorColor = args.getInt(KEY_INDICATOR_COLOR);

            ImageView pic = (ImageView) view.findViewById(R.id.ivDailyFood);
            pic.setBackgroundResource(R.drawable.recipe_1);
            //Shows Title (What day)
            TextView title = (TextView) view.findViewById(R.id.item_title);
            title.setText("Title: " + MainActivity.recipes.size());
            title.setTextColor(indicatorColor);
            //args.getCharSequence(KEY_TITLE)

            TextView recipeView = (TextView) view.findViewById(R.id.item_indicator_color);
            recipeView.setText("Recipe: " + recName);
            recipeView.setTextSize(20);


            int dividerColor = args.getInt(KEY_DIVIDER_COLOR);
            CharSequence currIngre;
            TextView ingredientView = (TextView) view.findViewById(R.id.item_divider_color);
            ingredientView.setTextSize(16);
            ingredientView.setText("Ingredients: \n");
            currIngre = ingredientView.getText();
            for(int i=0; i < recIngredient.size(); i++)
            {
                ingredientView.setText(currIngre + recIngredient.get(i)+"\n");
                currIngre = ingredientView.getText();
            }
        }
    }
}
