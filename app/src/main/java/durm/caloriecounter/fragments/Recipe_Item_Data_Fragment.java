package durm.caloriecounter.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.models.Recipe;

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


    public TextView getIngredientsData() {
        return ingredientsData;
    }

    private TextView ingredientsData;


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


        ingredientsData = view.findViewById(R.id.ingredients_text);

        ingredients = MainActivity.foodActiveFragment + "";
        howToMake = MainActivity.itemOpenedNumber + "";
        howToMakeButton = view.findViewById(R.id.howToButton);
        deleteButton = view.findViewById(R.id.deleteButton);

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
                // Remove from sharedPrefs
                // Go back to saved recipes screen
                // Display recipes
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



}

