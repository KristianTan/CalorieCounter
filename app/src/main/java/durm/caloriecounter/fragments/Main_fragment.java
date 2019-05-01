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

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String now = sdf.format(Calendar.getInstance().getTime());
        String lastGenerated = mPreferences.getString("mealsGeneratedDate", null);

        if(lastGenerated == null || !lastGenerated.equals(now)) {
            setRecipesForDay();
        } else {
            Map<String, ?> prefs = mPreferences.getAll();
            Pattern pattern = Pattern.compile("^[a-z]+(Today)");
            RecipeListSingleton.getInstance().recipeList.clear();
            titles.clear();
            info.clear();
            for(String key : prefs.keySet()) {
                Matcher matcher = pattern.matcher(key);
                if(prefs.get(key) instanceof String && matcher.matches()) {
                    // parse and display
                    Gson gson = new Gson();
                    String json = mPreferences.getString(key, "");
                    Recipe r = gson.fromJson(json, Recipe.class);
                    titles.add(r.getLabel());
                    info.add(r.getCalories() / r.getServings() + " cal");
                    RecipeListSingleton.getInstance().recipeList.add(r);
                }
            }
        }

        // Food menu adapter
        adapter = new ViewAdapter(c, titles, info);

        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onResume() {
        String caloriesBefore = calories.getText().toString();
        String caloriesAfter = mPreferences.getInt("caloricIntake", 0) + " calories";

        String foodTypeBefore = titleText.getText().toString();
        String foodTypeAfter = enumFoodType.values()[mPreferences.getInt("foodValue", 0)].name() + " | MENU";
        foodTypeAfter = foodTypeAfter.replace("_", " ");

        if (!caloriesBefore.equals(caloriesAfter) || !foodTypeBefore.equals(foodTypeAfter)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            mEditor.putString("mealsGeneratedDate", sdf.format(Calendar.getInstance().getTime()));
            mEditor.commit();

            calories.setText(mPreferences.getInt("caloricIntake", 0) + " calories");
            RecipeListSingleton.getInstance().recipeList.clear();
            setRecipesForDay();

            String foodTypeString = enumFoodType.values()[mPreferences.getInt("foodValue", 0)].name();
            foodTypeString = foodTypeString.replace("_", " ");

            titleText.setText(foodTypeString + " | MENU");
        }

        super.onResume();
    }

    public void setRecipesForDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        mEditor.putString("mealsGeneratedDate", sdf.format(Calendar.getInstance().getTime()));

        CaloriesPerMeal caloriesPerMeal = new CaloriesPerMeal();
        LinkedHashMap<String, Integer> meals = caloriesPerMeal.caloriesPerMeal(mPreferences.getInt("caloricIntake", 0));

        titles.clear();
        info.clear();
        Gson gson = new Gson();
        for (String key : meals.keySet()) {
            AsyncTask<String, Integer, Recipe> getRecipeData = new GetRecipeData(recyclerView.getContext(), new GetRecipeData.AsyncResponse() {
                @Override
                public void processFinish(Recipe output) {
                    if (output != null) {
                        RecipeListSingleton.getInstance().recipeList.add(output);

                        titles.add(key + ": " + output.getLabel());
                        info.add(output.getCalories() / output.getServings() + " cal");

                        String json = gson.toJson(output);
                        mEditor.putString(key.toLowerCase() + "Today", json);
                        mEditor.commit();

                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }
                }
            }).execute(String.valueOf(meals.get(key)), key);
        }


        mEditor.commit();
    }
}

