package se.dullestwall.dietapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Patrik on 2015-02-11.
 */
public class DailyRandomRecipe {


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
        HashMap<String, ArrayList<String>> hash1 = new HashMap();

        String day = TranslateDay(0);
        r.get(day).getIngredients();
        ArrayList<String> nameOne= new ArrayList();
        ArrayList<String> quantityOne= new ArrayList();
        ArrayList<String> ingredietOne= new ArrayList();
        nameOne.addAll(r.get(day).getIngredients().get("iname"));
        quantityOne.addAll(r.get(day).getIngredients().get("iquantity"));
        ingredietOne.addAll(r.get(day).getIngredients().get("imeasurement"));
       try{
        if(MainActivity.weekTotalIngredients.isEmpty()){
            MainActivity.weekTotalIngredients.put("iname",nameOne);
            MainActivity.weekTotalIngredients.put("iquantity",quantityOne);
            MainActivity.weekTotalIngredients.put("imeasurement",ingredietOne);

        }}catch (NullPointerException e){

       }
        total = (HashMap<String, List<String>>) MainActivity.weekTotalIngredients.clone();

        for(int k=1; k< 7; k++) {
            day = TranslateDay(0);
            hash1 = (HashMap<String, ArrayList<String>>) r.get(day).getIngredients().clone();

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

                        list2.set(j, Double.toString(count1 + count2));

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
        }
        MainActivity.weekTotalIngredients = (HashMap<String, List<String>>) total.clone();
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
