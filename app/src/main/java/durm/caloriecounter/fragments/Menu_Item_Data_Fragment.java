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

public class Menu_Item_Data_Fragment extends Fragment {

    // Food Titles

    public String getHowToMake() {
        return howToMake;
    }

    public void setHowToMake(String howToMake) {
        this.howToMake = howToMake;
    }

    String ingredients;
    String howToMake;


    public TextView getIngredientsData() {
        return ingredientsData;
    }

    public void setIngredientsData(TextView ingredientsData) {
        this.ingredientsData = ingredientsData;
    }

    public TextView getHowToMakeData() {
        return howToMakeData;
    }

    public void setHowToMakeData(TextView howToMakeData) {
        this.howToMakeData = howToMakeData;
    }

    private TextView ingredientsData;
    private TextView howToMakeData;


    public Menu_Item_Data_Fragment() {

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
        View view = inflater.inflate(R.layout.menu_item_data_fragment, container, false);


        ingredientsData = view.findViewById(R.id.ingredients_text);

        ingredients = MainActivity.foodActiveFragment + "";
        howToMake = MainActivity.itemOpenedNumber + "";


        if (ingredients != null) {
            ingredientsData.setText(ingredients);
        } else {
            ingredientsData.setText(R.string.error_data);
        }
        
        return view;
    }


}

