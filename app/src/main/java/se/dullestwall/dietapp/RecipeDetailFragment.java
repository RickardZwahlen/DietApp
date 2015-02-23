package se.dullestwall.dietapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeDetailFragment extends android.support.v4.app.Fragment {
    public static final String ARG_NAME = "name";

    private String mName;
    private Recipe recipe;

    private OnFragmentInteractionListener mListener;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    private int getDrawable(String name) {
        Context context = getActivity().getApplicationContext();
        int resourceId = context.getResources().getIdentifier(name, "drawable", getActivity().getApplicationContext().getPackageName());
        return resourceId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);

            recipe = findRecipe(mName);
        }
    }

    private Recipe findRecipe(String name) {
        for (Recipe r : MainActivity.recipes) {
            if (r.getName().equals(name)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.daily_recipe, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageView pic = (ImageView)getActivity().findViewById(R.id.ivDailyFood);
        int draw = getDrawable(recipe.getImageID());
        pic.setImageResource(draw);

        TextView title = (TextView)getActivity().findViewById(R.id.tvDailyRecipeName);
        title.setText(recipe.getName());
        title.setTextSize(22);

        TextView IngHead = (TextView)getActivity().findViewById(R.id.tvDailyIngredientHeader);
        IngHead.setText("Ingredients:");
        IngHead.setTextSize(18);

        CharSequence currIngredient;
        TextView ingredientView = (TextView)getActivity().findViewById(R.id.tvDailyIngredients);
        ingredientView.setTextSize(16);
        currIngredient = ingredientView.getText();
        for(int i=0; i < recipe.getIngredients().get("iname").size(); i++)
        {
            ingredientView.setText(currIngredient + recipe.getIngredients().get("iquantity").get(i)+recipe.getIngredients().get("imeasurement").get(i)+" "+ recipe.getIngredients().get("iname").get(i)+"\n");
            currIngredient = ingredientView.getText();
        }

        TextView InsHead = (TextView)getActivity().findViewById(R.id.tvDailyInstructionsHeader);
        InsHead.setText("Instructions:");
        InsHead.setTextSize(18);

        TextView InstructionsView = (TextView)getActivity().findViewById(R.id.tvDailyInstructions);
        InstructionsView.setTextSize(16);
        CharSequence currInstructions;
        currInstructions = InstructionsView.getText();
        for(int i=0; i < recipe.getInstructions().size(); i++)
        {
            InstructionsView.setText(currInstructions +Integer.toString(i+1)+". "+ recipe.getInstructions().get(i)+"\n");
            currInstructions = InstructionsView.getText();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
