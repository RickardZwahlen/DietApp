package se.dullestwall.dietapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Patrik on 2015-02-11.
 */
public class DailyRandomRecipe {

    String recName;
    int randomNum;
    int min = 0;

    public void setWeeklyRecipes()
    {
        int size = MainActivity.recipes.size();
        int random=getRandomRecipeDay(size);
        MainActivity.weekRecipes.put("Monday",MainActivity.recipes.get(random));

        random=getRandomRecipeDay(size);
        MainActivity.weekRecipes.put("Tuesday",MainActivity.recipes.get(random));

        random=getRandomRecipeDay(size);
        MainActivity.weekRecipes.put("Wednesday",MainActivity.recipes.get(random));

        random=getRandomRecipeDay(size);
        MainActivity.weekRecipes.put("Thursday",MainActivity.recipes.get(random));

        random=getRandomRecipeDay(size);
        MainActivity.weekRecipes.put("Friday",MainActivity.recipes.get(random));

        random=getRandomRecipeDay(size);
        MainActivity.weekRecipes.put("Saturday",MainActivity.recipes.get(random));

        random=getRandomRecipeDay(size);
        MainActivity.weekRecipes.put("Sunday",MainActivity.recipes.get(random));

    }

    public int getRandomRecipeDay(int max){
        randomNum = min + (int)(Math.random()*max);
        return randomNum;
    }

}
