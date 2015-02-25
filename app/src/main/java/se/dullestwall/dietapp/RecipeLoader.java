package se.dullestwall.dietapp;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeLoader {

    private long recipeID=0;

    public ArrayList<Recipe> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return (ArrayList<Recipe>)readRecipesArray(reader);
        } finally {
            reader.close();
        }
    }

    public List<Recipe> readRecipesArray(JsonReader reader) throws IOException {
        List<Recipe> recipes = new ArrayList<Recipe>();

        reader.beginArray();
        while (reader.hasNext()) {
            recipes.add(readRecipe(reader));
        }
        reader.endArray();
        return recipes;
    }

    public Recipe readRecipe(JsonReader reader) throws IOException {
        long id = 0;
        String name = null;
        String description = null;
        String imageID = null;
        List<String> diets = null;
        HashMap<String,List<String>> ingredients = null;
        List<String> instructions = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String next = reader.nextName();
            if (next.equals("id")) {
                id = reader.nextLong();
                id=recipeID;
                recipeID++;
            } else if (next.equals("name")) {
                name = reader.nextString();
            } else if (next.equals("imageID")) {
                imageID = reader.nextString();
            } else if (next.equals("description")) {
                description = reader.nextString();
            } else if (next.equals("diets")) {
                diets = readStringsArray(reader);
            } else if (next.equals("ingredients") && reader.peek() != JsonToken.NULL) {
                ingredients = readIngredianseHash(reader);
            } else if (next.equals("instructions")) {
                instructions = readStringsArray(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Recipe(id, name, description, imageID, diets, ingredients, instructions);
    }

    public List readStringsArray(JsonReader reader) throws IOException {
        List<String> strings = new ArrayList<String>();

        reader.beginArray();
        while (reader.hasNext()) {
            strings.add(reader.nextString());
        }
        reader.endArray();
        return strings;
    }
    public HashMap readIngredianseHash(JsonReader reader) throws IOException {
        List<String> strings = new ArrayList<String>();
        HashMap<String, List<String>> hash = new HashMap<String, List<String>>();
        List<String> iName = null;
        List<String> iQuantity = null;
        List<String> iMeasurement = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String next = reader.nextName();
            if (next.equals("iname")) {
                iName = readStringsArray(reader);
                hash.put("iname", iName);
            } else if (next.equals("iquantity")) {
                iQuantity = readStringsArray(reader);
                hash.put("iquantity", iQuantity);
            } else if (next.equals("imeasurement")) {
                iMeasurement = readStringsArray(reader);
                hash.put("imeasurement", iMeasurement);
            }
        }
            reader.endObject();
            return hash;

    }
}
