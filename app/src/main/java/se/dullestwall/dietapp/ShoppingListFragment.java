package se.dullestwall.dietapp;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeekListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeekListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListFragment extends android.support.v4.app.ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private final String[] array = {"Hello", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome"};
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingListFragment newInstance(String param1, String param2) {
        ShoppingListFragment fragment = new ShoppingListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ShoppingListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume()
    {
        super.onResume();
      //  final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.view_row, R.id.header_text, array);
      //  final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) getActivity().findViewById(R.id.listview);
      //  expandableLayoutListView.setAdapter(arrayAdapter);

        ShoppingListAdapter adapter = new ShoppingListAdapter(MainActivity.weekTotalIngredients,getActivity().getBaseContext(),5);


        setListAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.shopping_list_view, container, false);

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.llShoppingViewBackground);
        int color = getColor();
        //ll.setBackgroundColor(color);
        return view;
    }

    int getColor(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK)-2;
        int dailyColor;

        if(day==0){
            dailyColor = Color.GREEN;
        }else if(day==1){
            dailyColor = Color.BLUE;
        }else if(day==2){
            dailyColor = Color.rgb(128,0,128);
        }else if(day==3){
            dailyColor = Color.RED;
        }else if(day==4){
            dailyColor = Color.rgb(255,153,0);
        }else if(day==5){
            dailyColor = Color.rgb(255,192,203);
        }else{
            dailyColor = Color.YELLOW;
        }
    return dailyColor;
    }

}
