package durm.caloriecounter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

///
/// Main Screen view.
///

public class Main_fragment extends Fragment {

    TextView titleText;

    // Test Data Arrays.
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> info = new ArrayList<>();
    // Access it from anywhere
    static String FoodChoiceVar = "All food"; // Value set for test only. Maybe make an ENUM instead?

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

        if(MainActivity.numberOfMeals == 6) {

            titles.add("Breakfast");
            titles.add("Morning Snack");
            titles.add("Lunch");
            titles.add("Afternoon Snack");
            titles.add("Dinner");
            titles.add("Night Snack");
            info.add("300 Kcal     2 Items");
            info.add("250 Kcal     3 Items");
            info.add("100 Kcal     2 Items");
            info.add("500 Kcal     5 Items");
            info.add("200 Kcal     3 Items");
            info.add("600 Kcal     4 Items");
        }
        // Add meal data to the fragments

    }

    //Use this instead.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);


        final Context c = getContext();

        titleText = view.findViewById(R.id.textViewFoodType);
        titleText.setText(FoodChoiceVar + "| MENU");

        // add dummy info to fragments
        addDataToList();


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

    // Add information specific to a fragment.
    private void addDataToList(){


        // Titles inside fragments.
        setTitleData(0,"Breakfast");
        setTitleData(1,"Morning Snack");
        setTitleData(2,"Lunch");
        setTitleData(3,"Afternoon Snack");
        setTitleData(4,"Dinner");
        setTitleData(5,"Night Snack");

        // Foods inside fragments;

        // Fragment 0;
        setFoodData(0,"Bread and Butter");
        setFoodData(0,"Black Tea");

        // Fragment 1;
        setFoodData(1,"Energy Baton");
        setFoodData(1,"Water");

        // Fragment 2;
        setFoodData(2,"Fried Chicken");
        setFoodData(2,"French Fries");
        setFoodData(2,"Fizzy Drink");






    }

    // Sets and adds foods for each fragment.
    private void setFoodData(int index, String foodName){
        MainActivity.menuFragments.get(index).titles.add(foodName);
    }
    private void setTitleData(int index, String foodName){
        MainActivity.menuFragments.get(index).titleOfFragment = foodName;
    }


}

