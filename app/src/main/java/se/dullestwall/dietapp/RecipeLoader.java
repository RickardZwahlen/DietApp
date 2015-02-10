package se.dullestwall.dietapp;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecipeLoader {

    public List readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readRecipesArray(reader);
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
        List<String> ingredients = null;
        List<String> instructions = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String next = reader.nextName();
            if (next.equals("id")) {
                id = reader.nextLong();
            } else if (next.equals("name")) {
                name = reader.nextString();
            } else if (next.equals("description")) {
                description = reader.nextString();
            } else if (next.equals("ingredients") && reader.peek() != JsonToken.NULL) {
                ingredients = readStringsArray(reader);
            } else if (next.equals("user")) {
                instructions = readStringsArray(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Recipe(id, name, description, ingredients, instructions);
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
}
