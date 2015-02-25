package se.dullestwall.dietapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Patrik on 2015-02-24.
 */
public class CreateRecipe {

    int id;
    String name, imageID, description, diets, iname, iquantity, imeasurement, instructions;
    HashMap<String, List<String>> ingredients;
    ArrayList<String> arrListName;
    ArrayList<String> arrListQuantity;
    ArrayList<String> arrListMeasurement;
    ArrayList<String> arrListDiets;
    ArrayList<String> arrListInstructions;

    public Recipe putRecipe(int id, String name, String imageID, String description, String diets, String iname, String iquantiry, String imeasurement, String instructions ){
        String splitIngredients ="\\,"+"\\s+"; //"\\s+"+
        String splitInstructions ="\\."+"\\,"+"\\s+";
        arrListName = new ArrayList();
        arrListQuantity = new ArrayList();
        arrListMeasurement = new ArrayList();
        arrListDiets = new ArrayList();
        arrListInstructions = new ArrayList();
        ingredients= new HashMap();

        //Splitting all ingredient related and adding in Arraylist
        String[] allIngr = iname.split(splitIngredients);
        String[] allQuan = iquantiry.split(splitIngredients);
        String[] allMeas = imeasurement.split(splitIngredients);
        String[] allDiet = diets.split(splitIngredients);
        String[] allInst = instructions.split(splitInstructions);
        for(int i=0; i<allIngr.length;i++){
            arrListName.add(allIngr[i]);
            arrListQuantity.add(allQuan[i]);
            arrListMeasurement.add(allMeas[i]);
        }
        for(int i=0; i<allDiet.length;i++){
            arrListDiets.add(allDiet[i]);
        }
        for(int i=0; i<allInst.length;i++){
            arrListInstructions.add(allInst[i]);
        }
        ingredients.put("iname", arrListName);
        ingredients.put("iquantity", arrListQuantity);
        ingredients.put("imeasurement", arrListMeasurement);

        return new Recipe(id, name, description, imageID, arrListDiets, ingredients, arrListInstructions);


    }

}
