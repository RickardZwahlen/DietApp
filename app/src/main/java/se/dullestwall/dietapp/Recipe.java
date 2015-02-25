package se.dullestwall.dietapp;

import java.util.HashMap;
import java.util.List;

public class Recipe {
    private long id;
    private String name;
    private String description;
    private String imageID;
    private HashMap<String, List<String>> ingredients;
    private List<String> diets;
    private List<String> instructions;

    public Recipe(long id, String name, String description, String imageID,
                  List<String> diets, HashMap<String, List<String>> ingredients, List<String> instructions) {
        this.id = id;
        this.name = name;
        this.imageID = imageID;
        this.description = description;
        this.diets = diets;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public long getId() { return id;}

    public String getDescription() {
        return description;
    }

    public String getImageID() {return imageID; }

    public HashMap<String, List<String>> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public List<String> getDiets() { return diets; }
}
