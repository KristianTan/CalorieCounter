package durm.caloriecounter.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import java.util.LinkedHashMap;
import java.util.Map;

import durm.caloriecounter.R;
import durm.caloriecounter.enumerators.enumFoodType;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.models.RecipeListSingleton;
import durm.caloriecounter.requests.CaloriesPerMeal;
import durm.caloriecounter.requests.GetRecipeData;
import durm.caloriecounter.viewAdapters.ViewAdapter;

///
/// Main Screen view.
///

public class Main_fragment extends Fragment {

    public TextView titleText;
    public TextView calories;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    public ViewAdapter adapter;
    private RecyclerView recyclerView;

    // Test Data Arrays.
    final public static ArrayList<String> titles = new ArrayList<>();
    final public static ArrayList<String> info = new ArrayList<>();
    // Access it from anywhere

    public Main_fragment() {
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


        this.titleText = view.findViewById(R.id.textViewFoodType);
        this.calories = view.findViewById(R.id.TargetTextNumber);

        calories.setText(mPreferences.getInt("caloricIntake", 0) + " calories");

        String foodTypeString = enumFoodType.values()[mPreferences.getInt("foodValue", 0)].name();
        foodTypeString = foodTypeString.replace("_", " ");

        titleText.setText(foodTypeString + " | MENU");

        // create the recycler for the menu data
        recyclerView = view.findViewById(R.id.fragmentRecycleView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);

        if(Main_fragment.titles.size() == 0) {
            setRecipesForDay();
        }

        // Food menu adapter
        adapter = new ViewAdapter(c, titles, info);

        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onResume() {
        String before = calories.getText().toString();
        String after = mPreferences.getInt("caloricIntake", 0) + " calories";

        if (!before.equals(after)) {
            CaloriesPerMeal caloriesPerMeal = new CaloriesPerMeal();
            Map<String, Integer> meals = caloriesPerMeal.caloriesPerMeal(mPreferences.getInt("caloricIntake", 0));

            ArrayList<String> newTitles = new ArrayList<>();
            ArrayList<String> cals = new ArrayList<>();

            calories.setText(mPreferences.getInt("caloricIntake", 0) + " calories");
            RecipeListSingleton.getInstance().recipeList.clear();
            setRecipesForDay();
        }


        String foodTypeString = enumFoodType.values()[mPreferences.getInt("foodValue", 0)].name();
        foodTypeString = foodTypeString.replace("_", " ");

        titleText.setText(foodTypeString + " | MENU");
        super.onResume();
    }

    public void setRecipesForDay() {
        CaloriesPerMeal caloriesPerMeal = new CaloriesPerMeal();
        LinkedHashMap<String, Integer> meals = caloriesPerMeal.caloriesPerMeal(mPreferences.getInt("caloricIntake", 0));

        titles.clear();
        info.clear();
        for (String key : meals.keySet()) {
            AsyncTask<String, Integer, Recipe> getRecipeData = new GetRecipeData(new GetRecipeData.AsyncResponse() {
                @Override
                public void processFinish(Recipe output) {
                    if (output != null) {
                        RecipeListSingleton.getInstance().recipeList.add(output);

                        titles.add(key + ": " + output.getLabel());
                        info.add(output.getCalories() / output.getServings() + " cal");

                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }
                }
            }).execute(String.valueOf(meals.get(key)), key);
        }

    }
}

