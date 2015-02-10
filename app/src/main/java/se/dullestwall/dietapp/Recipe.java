package se.dullestwall.dietapp;

import java.util.List;

/**
 * Created by azzarcher on 10/02/15.
 */
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
}
