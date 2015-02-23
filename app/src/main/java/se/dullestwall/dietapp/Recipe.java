package se.dullestwall.dietapp;

import java.util.List;

public class Recipe {
    private long id;
    private String name;
    private String description;
    private String imageID;
    private List<String> diets;
    private List<String> ingredients;
    private List<String> instructions;

    public Recipe(long id, String name, String description, String imageID,
                  List<String> diets, List<String> ingredients, List<String> instructions) {
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

    public String getDescription() {
        return description;
    }

    public String getImageID() {return imageID; }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public List<String> getDiets() { return diets; }
}
