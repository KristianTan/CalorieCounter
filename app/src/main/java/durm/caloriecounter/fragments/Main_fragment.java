package durm.caloriecounter.fragments;

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

import durm.caloriecounter.R;
import durm.caloriecounter.viewAdapters.ViewAdapter;

///
/// Main Screen view.
///

public class Main_fragment extends Fragment {

   public TextView titleText;

    // Test Data Arrays.
   final public static ArrayList<String> titles = new ArrayList<>();
   final public static ArrayList<String> info = new ArrayList<>();
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



}

