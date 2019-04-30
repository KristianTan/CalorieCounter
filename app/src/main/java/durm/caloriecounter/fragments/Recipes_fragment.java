package durm.caloriecounter.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.activities.SearchActivity;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.requests.CaloriesPerMeal;
import durm.caloriecounter.viewAdapters.SavedRecipesViewAdapter;

public class Recipes_fragment extends Fragment {


    // Test Data Arrays.
    final public static ArrayList<String> titles = new ArrayList<>();
    final public static ArrayList<String> info = new ArrayList<>();
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
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
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mEditor = mPreferences.edit();
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

    @Override
    public void onResume() {
        titles.clear();
        info.clear();
        Map<String, ?> prefs = mPreferences.getAll();
        Pattern pattern = Pattern.compile("^(savedRecipe)[\\d]+");

        for(String key : prefs.keySet()) {
            Matcher matcher = pattern.matcher(key);
            if(prefs.get(key) instanceof String && matcher.matches()) {
                // parse and display
                Gson gson = new Gson();
                String json = mPreferences.getString(key, "");
                Recipe r = gson.fromJson(json, Recipe.class);
                Recipes_fragment.titles.add(r.getLabel());
                Recipes_fragment.info.add(r.getCalories() / r.getServings() + " cal");
            }
        }
        super.onResume();
    }

}