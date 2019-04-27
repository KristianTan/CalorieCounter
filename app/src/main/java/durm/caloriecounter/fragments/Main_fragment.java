package durm.caloriecounter.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import durm.caloriecounter.R;
import durm.caloriecounter.enumerators.enumFoodType;
import durm.caloriecounter.viewAdapters.ViewAdapter;

///
/// Main Screen view.
///

public class Main_fragment extends Fragment {

   public TextView titleText;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


    // Test Data Arrays.
   final public static ArrayList<String> titles = new ArrayList<>();
   final public static ArrayList<String> info = new ArrayList<>();
    // Access it from anywhere
    static String FoodChoiceVar;

    public  Main_fragment() {
        // Needed empty constructor.
    }

    // Don't edit this method as you won't be able tu use "R".
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Added 6 fragments for tests;
        // EVERYTHING WILL FALL APART IF YOU DELETE SOMETHING;
        // had no time to implement any fail safe.

        // Add meal data to the fragments

    }

    //Use this instead.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);


        final Context c = getContext();

        mPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        mEditor = mPreferences.edit();


        titleText = view.findViewById(R.id.textViewFoodType);

        // get the food choice
        foodChoice();

        String foodTypeString = enumFoodType.values()[mPreferences.getInt("foodValue", 0)].name();
        foodTypeString = foodTypeString.replace("_", " ");

        titleText.setText(foodTypeString + " | MENU");

        // create the recycler for the menu data
        RecyclerView recyclerView = view.findViewById(R.id.fragmentRecycleView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);

        // Food menu adapter
        ViewAdapter adapter = new ViewAdapter(c,titles,info);

        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);

        return view;
    }


    private void foodChoice(){

        switch (mPreferences.getInt("foodValue",0)){

            case 0: FoodChoiceVar = "All Food";
            break;
            case 1: FoodChoiceVar = "Fruit Lover";
                break;
            case 2: FoodChoiceVar = "Vegan";
                break;
            case 3: FoodChoiceVar = "Meat Lover";
                break;
            case 4: FoodChoiceVar = "Sweets Lover";
                break;
            case 5: FoodChoiceVar = "Vegetarian";
                break;
        }

    }



}

