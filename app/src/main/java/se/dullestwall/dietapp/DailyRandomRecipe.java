package se.dullestwall.dietapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Patrik on 2015-02-11.
 */
public class DailyRandomRecipe {

    String recName;
    int randomNum;
    int min = 0;
    int random;

    public void setWeeklyRecipes()
    {
        if(MainActivity.recipes!=null) {
            int size = MainActivity.recipes.size();

            random = getRandomRecipeDay(size);
            MainActivity.weekRecipes.put("Monday", MainActivity.recipes.get(random));

            random = getRandomRecipeDay(size);
            MainActivity.weekRecipes.put("Tuesday", MainActivity.recipes.get(random));

            random = getRandomRecipeDay(size);
            MainActivity.weekRecipes.put("Wednesday", MainActivity.recipes.get(random));

            random = getRandomRecipeDay(size);
            MainActivity.weekRecipes.put("Thursday", MainActivity.recipes.get(random));

            random = getRandomRecipeDay(size);
            MainActivity.weekRecipes.put("Friday", MainActivity.recipes.get(random));

            random = getRandomRecipeDay(size);
            MainActivity.weekRecipes.put("Saturday", MainActivity.recipes.get(random));

            random = getRandomRecipeDay(size);
            MainActivity.weekRecipes.put("Sunday", MainActivity.recipes.get(random));

            MainActivity.weekTotalIngredients2 = (HashMap<String,Recipe>) MainActivity.weekRecipes;
            getAllIngredients(MainActivity.weekTotalIngredients2);

            MainActivity.done1=1;
        }
    }

    public int getRandomRecipeDay(int max){
        randomNum = min + (int)(Math.random()*max);
        return randomNum;
    }
// Create a groceryList
    public void getAllIngredients(HashMap<String, Recipe> r) {
        HashMap<String, List<String>> total = new HashMap();
        boolean done = false;
        double count1 = 0;
        double count2 = 0;
        HashMap<String, List<String>> hash1 = new HashMap();

        String day = TranslateDay(0);
      //  r.get(day).getIngredients();
       try{
        if(MainActivity.weekTotalIngredients.isEmpty()){
            MainActivity.weekTotalIngredients.put("iname",r.get(day).getIngredients().get("iname"));
            MainActivity.weekTotalIngredients.put("iquantity",r.get(day).getIngredients().get("iquantity"));
            MainActivity.weekTotalIngredients.put("imeasurement",r.get(day).getIngredients().get("imeasurement"));

        }}catch (NullPointerException e){

       }
        total = (HashMap<String, List<String>>) MainActivity.weekTotalIngredients.clone();
        hash1 = (HashMap<String, List<String>>) r.get("Tuesday").getIngredients();

            for (int i = 0; i < hash1.get("iname").size(); i++) {
                String ingredient = hash1.get("iname").get(i);
                String string1 = hash1.get("iname").get(i);
                String string2 = hash1.get("iquantity").get(i);
                String string3 = hash1.get("imeasurement").get(i);

                List<String> list1 = total.get("iname");
                List<String> list2 = total.get("iquantity");
                List<String> list3 = total.get("imeasurement");
                    for (int j = 0; j < total.get("iname").size() - 1; j++) {
                        if (ingredient.equals(total.get("iname").get(j))) {
                            count1 = Double.valueOf(hash1.get("iquantity").get(i));
                            count2 = Double.valueOf(total.get("iquantity").get(j));

                            list2.set(j, Double.toString(count1+count2));

                            total.put("iquantity", list2);
                            done = true;
                        }
                    }

                if (!done) {
                    list1.add(string1);
                    list2.add(string2);
                    list3.add(string3);

                    total.put("iname", list1);
                    total.put("iquantity", list2);
                    total.put("imeasurement", list3);
                } else {
                    done = false;
                }
            }
       // MainActivity.weekTotalIngredients = (HashMap<String, List<String>>) total.clone();
    }

    public String TranslateDay(int day){
        if(day == 0){
            return "Monday";
        }else if(day == 1){
            return "Tuesday";
        }else if(day == 2){
            return "Wednesday";
        }else if(day == 3){
            return "Thursday";
        }else if(day == 4){
            return "Friday";
        }else if(day == 5){
            return "Saturday";
        }else{
            return "Sunday";
        }
    }

}
