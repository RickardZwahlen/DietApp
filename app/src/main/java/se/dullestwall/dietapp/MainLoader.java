package se.dullestwall.dietapp;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import se.dullestwall.dietapp.AsyncResult;
import se.dullestwall.dietapp.CreateRecipe;
import se.dullestwall.dietapp.Diet;
import se.dullestwall.dietapp.DietLoader;
import se.dullestwall.dietapp.DownloadWebpageTask;
import se.dullestwall.dietapp.MainActivity;
import se.dullestwall.dietapp.Recipe;
import se.dullestwall.dietapp.RecipeLoader;

/**
 * The MainLoader for the application, creates backgroundthreads for downloading JSON information and sends the infromation tho other activities.
 * @author BeerDev
 *
 */
public class MainLoader extends Activity {
    /**
     * pDialog for showing progress dialog
     */
    private ProgressDialog pDialog;
    /**
     * Show if no internet connection
     */

    /**
     * URL to get products JSON
     */
    String p1 = "https://spreadsheets.google.com/tq?key=";
    String p2 = "15d9PoMYAVNbIBgMfXrr8ka0e3iz2F9rJpOe-GX--88A"; //newRecipe
    String p3 = "1yyTcjWA6RAUwkI7sKOevWXAJfpITs__Zb0TwilihDCw"; //Telerik
    String p4 = "1HE_9-3nuA7b2sufFzxPSphccJrt8Pkhrgr1YLT498b8"; //Patriks Sheet
    public String sheetURL =p1+p4;

    /**
     * JSON Node for finding products
     */
    /**
     * List of recipes
     */
    public static ArrayList<Recipe> recipes;
    public static ArrayList<Diet> diets;
    //Saving Recipes in a HashMap
    public static HashMap<String,Recipe> weekRecipes;
    public static HashMap<String,List<String>> weekTotalIngredients;
    public static HashMap<String,Recipe> weekTotalIngredients2;
    public static String testTitle="XXX";


    public boolean allRDY=false;


    public static boolean wasOnline;
    public boolean downloadFinished;
    /**
     * Hashmap for the products
     */
    public static ArrayList<HashMap<String, String>> productList;
    public static ArrayList<HashMap<String, String>> beerList;
    public static ArrayList<HashMap<String, String>> ciderList;
    public static ArrayList<HashMap<String, String>> spritList;

    public static ArrayList<HashMap<String, String>> completeProductList;
    public static ArrayList<HashMap<String, String>> completeShoppingList;
    public static int shoppingSum;

    public static int currentCredit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        recipes = new ArrayList();
        //getproductList = new ArrayList<HashMap<String, String>>();

        CheckingNetwork();
    }



    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetProducts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainLoader.this);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
                   loadRecipes();

            return null;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if(downloadFinished) {
                MainActivity.recipes = (List) recipes;
                Intent in = new Intent(getApplicationContext(),
                        MainActivity.class);
                in.putExtra("allrecipes", recipes);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            }
            if (pDialog.isShowing()){
                pDialog.dismiss();
            }

        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {
            wasOnline = true;
        }else{
            wasOnline = false;
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {
            if(wasOnline == false){
                new GetProducts().execute();
            }
        }
    }

    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public void CheckingNetwork(){

        if(isOnline()){

			wasOnline = true;
			downloadFinished = false;
            new DownloadWebpageTask(new AsyncResult() {
                @Override
                public void onResult(JSONObject object) {
                    try {
                        processJson(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).execute(sheetURL);

            Toast.makeText(this, "Downloading recipes..", Toast.LENGTH_LONG).show();



        }
        else if(!isOnline())
        {
            Toast.makeText(this, "No internet connection..", Toast.LENGTH_LONG).show();
            offlineMode();
            wasOnline = false;
        }
    }


    @SuppressWarnings("unchecked")
    public void offlineMode() {
        //JSONObject obj;
        AssetManager am = this.getAssets();
        InputStream is = null;
        try {
            is = am.open("recipes.json");
            RecipeLoader recipeLoader = new RecipeLoader();
            this.recipes = recipeLoader.readJsonStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Intent in = new Intent(getApplicationContext(),
                    MainActivity.class);
            in.putExtra("position", 0);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
            finish();
        }
    }


    public void loadRecipes() {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                try {
                    processJson(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).execute(sheetURL);

    }


    private void processJson(JSONObject object) throws JSONException {
        //      testTitle="processJson";
        recipes.clear();
        try {
            JSONArray rows = object.getJSONArray("rows");
            for (int r = 0; r < rows.length(); r++) {
                JSONObject row = rows.getJSONObject(r);

                JSONArray columns = row.getJSONArray("c");
                //Name
                int id = columns.getJSONObject(0).getInt("v");
                String name = columns.getJSONObject(1).getString("v");
                String imaID = columns.getJSONObject(2).getString("v");
                String descr = columns.getJSONObject(3).getString("v");
                String diet  = columns.getJSONObject(4).getString("v");
                String inam = columns.getJSONObject(5).getString("v");
                String iquan = columns.getJSONObject(6).getString("v");
                String imeas = columns.getJSONObject(7).getString("v");
                String instr = columns.getJSONObject(8).getString("v");

                testTitle = name;
                CreateRecipe cr = new CreateRecipe();
                Recipe rec = cr.putRecipe(id,name,imaID,descr,diet,inam,iquan,imeas,instr);
                recipes.add(rec);

            }
            MainActivity.recipes = (List) recipes;
            Intent in = new Intent(getApplicationContext(),
                    MainActivity.class);
        //    in.putExtra("allrecipes", recipes);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(in);
            finish();
            downloadFinished = true;
            //   final TeamsAdapter adapter = new TeamsAdapter(this, R.layout.team, teams);
            //  listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            com.example.android.common.logger.Log.e("Error", testTitle);
            testTitle="fel1";
        }
        finally {

        }
    }

}
