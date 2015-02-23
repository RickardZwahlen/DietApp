package se.dullestwall.dietapp;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DietLoader {
    public List<Diet> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readDietsArray(reader);
        } finally {
            reader.close();
        }
    }

    public List<Diet> readDietsArray(JsonReader reader) throws IOException {
        List<Diet> diets = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            diets.add(readDiet(reader));
        }
        reader.endArray();
        return diets;
    }

    public Diet readDiet(JsonReader reader) throws IOException {
        long id = 0;
        String name = null;
        String imageID = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String next = reader.nextName();
            if (next.equals("id")) {
                id = reader.nextLong();
            } else if (next.equals("name")) {
                name = reader.nextString();
            } else if (next.equals("imageID")) {
                imageID = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Diet(id, name, imageID);
    }
}
