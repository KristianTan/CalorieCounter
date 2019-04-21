package durm.caloriecounter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;

/*
 *
 * This class represents the fragment that has the ingredients and the how to make section for a food.
 *
 */

public class Recipe_Item_Data_Fragment extends Fragment {

    // Food Titles

    String ingredients;
    String howToMake;


    private TextView ingredientsData;
    private TextView howToMakeData;


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
        howToMakeData = view.findViewById(R.id.how_to_make_text);

        ingredients = MainActivity.foodActiveFragment + "";
        howToMake = MainActivity.itemOpenedNumber + "";


        if (ingredients != null) {
            ingredientsData.setText(ingredients);
        } else {
            ingredientsData.setText(R.string.error_data);
        }


        if (howToMake != null) {
            howToMakeData.setText(howToMake);
        } else {
            howToMakeData.setText(R.string.error_data);
        }

        return view;
    }


}

