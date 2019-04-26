package durm.caloriecounter.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.SearchActivity;
import durm.caloriecounter.viewAdapters.SavedRecipesViewAdapter;

public class Recipes_fragment extends Fragment {


    // Test Data Arrays.
    final public static ArrayList<String> titles = new ArrayList<>();
    final public static ArrayList<String> info = new ArrayList<>();

    public Recipes_fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipes_fragment, container, false);
        final Context c = getContext();

        // create the recycler for the menu data
        RecyclerView recyclerView = view.findViewById(R.id.RecycleViewRecipes);


        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);

        // Food menu adapter
        SavedRecipesViewAdapter adapter = new SavedRecipesViewAdapter(c,titles,info);

        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(c, SearchActivity.class);
                startActivity(profileIntent);
            }
        });


        return view;
    }
}