package se.dullestwall.dietapp;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import com.example.android.common.logger.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity implements RecipesFragment.OnFragmentInteractionListener,
        NavigationDrawerFragment.NavigationDrawerCallbacks, WeekListFragment.OnFragmentInteractionListener,
        DietFragment.OnFragmentInteractionListener, RecipeDetailFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /**
     * List of recipes
     */
    public static List<Recipe> recipes;
    public static List<Diet> diets;
    //Saving Recipes in a HashMap, String=Day
    public static HashMap<String,Recipe> weekRecipes;
    public static HashMap<String,List<String>> weekTotalIngredients;
    public static HashMap<String,Recipe> weekTotalIngredients2;
    public int dailyColor;
    public int first = 0;
    public static String testTitle="XXX";
    String p1 = "https://spreadsheets.google.com/tq?key=";
    String p2 = "15d9PoMYAVNbIBgMfXrr8ka0e3iz2F9rJpOe-GX--88A"; //newRecipe
    String p3 = "1yyTcjWA6RAUwkI7sKOevWXAJfpITs__Zb0TwilihDCw"; //Telerik
    String p4 = "1HE_9-3nuA7b2sufFzxPSphccJrt8Pkhrgr1YLT498b8";
    public String sheetURL =p1+p4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Loads recipes from Json file
        loadRecipes();
        loadDiets();
        //Randomize recipes for each day
        dailyColor = Color.RED;
        if(first==0) {
            weekRecipes = new HashMap();
            weekTotalIngredients2 = new HashMap();
            weekTotalIngredients = new HashMap();
            DailyRandomRecipe daily = new DailyRandomRecipe();
            daily.setWeeklyRecipes();
            weekTotalIngredients2 = weekRecipes;
            daily.getAllIngredients(weekTotalIngredients2);
        }
        first=1;//Checks if its the first time on this page

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        switch(position)
        {
            case 0:

                fragmentManager.beginTransaction()
                        .replace(R.id.container, WeekListFragment.newInstance("test", Integer.toString(position)))
                        .commit();
                break;
            case 1:

                transaction = getSupportFragmentManager().beginTransaction();
                RecipesFragment fragmentAllRec = new RecipesFragment();
                transaction.replace(R.id.container, fragmentAllRec);
                transaction.commit();

                break;
            case 2:
                //placeholder
                transaction = getSupportFragmentManager().beginTransaction();
                DailyViewFragment fragmentDaily = new DailyViewFragment();
                transaction.replace(R.id.container, fragmentDaily);
                transaction.commit();
                break;
            case 3:
                //placeholder
                transaction = getSupportFragmentManager().beginTransaction();
                ShoppingListFragment fragmentShoppList = new ShoppingListFragment();
                transaction.replace(R.id.container, fragmentShoppList);
                transaction.commit();
                break;
            case 4:
                transaction = getSupportFragmentManager().beginTransaction();
                DietFragment dietFragment = new DietFragment();
                transaction.replace(R.id.container, dietFragment);
                transaction.commit();
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    private void loadRecipes() {
        AssetManager am = this.getAssets();
        InputStream is = null;

        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                try {
                    processJson(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                    testTitle = "FelOnResult";
                }
            }
        }).execute(sheetURL);


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
        }
    }

    private void loadDiets() {
        AssetManager am = this.getAssets();
        InputStream is = null;

        try {
            is = am.open("diets.json");
            DietLoader dietLoader = new DietLoader();
            this.diets = dietLoader.readJsonStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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

         //   final TeamsAdapter adapter = new TeamsAdapter(this, R.layout.team, teams);
          //  listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
           Log.e("Error", testTitle);
            testTitle="fel1";
        }
    }
}
