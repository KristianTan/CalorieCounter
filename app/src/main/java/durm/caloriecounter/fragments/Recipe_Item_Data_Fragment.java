package durm.caloriecounter.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.models.RecipeListSingleton;

/*
 *
 * This class represents the fragment that has the ingredients and the how to make section for a food.
 *
 */

public class Recipe_Item_Data_Fragment extends Fragment {

    // Food Titles
    String ingredients;
    String howToMake;
    Recipe recipe;

    private Button howToMakeButton;
    private Button deleteButton;
    private ImageView mealImage;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public TextView getIngredientsData() {
        return ingredientsData;
    }
    public TextView getRecipeName(){return  recipeName;}
    private TextView ingredientsData;
    private TextView recipeName;

    public Recipe_Item_Data_Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // maybe set the data here ?????


    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_item_data_fragment, container, false);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mEditor = mPreferences.edit();

        ingredientsData = view.findViewById(R.id.ingredients_text);

        ingredients = MainActivity.foodActiveFragment + "";
        howToMake = MainActivity.itemOpenedNumber + "";
        howToMakeButton = view.findViewById(R.id.howToButton);
        deleteButton = view.findViewById(R.id.deleteButton);
        recipeName = view.findViewById(R.id.recipe_name);
        mealImage = view.findViewById(R.id.imageMeal);


        howToMakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(recipe.getRecipeURL()));
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Recipes_fragment.titles.remove(recipe.getLabel());
                Recipes_fragment.info.remove(recipe.getCalories() / recipe.getServings() + " cal");
                String i = RecipeListSingleton.getInstance().savedRecipeList.toString(); // Is it same object reference

                RecipeListSingleton.getInstance().savedRecipeList.remove(recipe); // Is it same object reference

                String j = RecipeListSingleton.getInstance().savedRecipeList.toString(); // Is it same object reference


                Recipes_fragment.getAdapter().notifyDataSetChanged();

                Map<String, ?> prefs = mPreferences.getAll();

                String keyToRemove = "";
                Gson gson = new Gson();
                Pattern pattern = Pattern.compile("^(savedRecipe)[\\d]+");

                for(String key : prefs.keySet()) {
                    Matcher matcher = pattern.matcher(key);
                    if(prefs.get(key) instanceof String && matcher.matches()) {
                        String json = mPreferences.getString(key, "");
                        Recipe recipeToRemove = gson.fromJson(json, Recipe.class);
                        if(recipeToRemove.getLabel().equals(recipe.getLabel())) {
                            keyToRemove = key;
                        }
                    }
                }
                if(Recipes_fragment.getAdapter().getItemCount() == 0){

                    Recipes_fragment.getNoRecipesText().setVisibility(View.VISIBLE);
                }

                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().popBackStack();

                Toast savedToast = Toast.makeText(view.getContext(), "Recipe deleted", Toast.LENGTH_SHORT);
                savedToast.show();

                mEditor.remove(keyToRemove);
                mEditor.commit();



            }
        });

        if (ingredients != null) {
            ingredientsData.setText(ingredients);
        } else {
            ingredientsData.setText(R.string.error_data);
        }


        return view;
    }

    public void setHowToMake(String howToMake) {
        this.howToMake = howToMake;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public ImageView getMealImage(){return mealImage;}

}

