package se.dullestwall.dietapp;

import java.util.List;

public class Recipe {
    private long id;
    private String name;
    private String description;
    private List<String> ingredients;
    private List<String> instructions;

    public Recipe(long id, String name, String description,
                  List<String> ingredients, List<String> instructions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }
}
