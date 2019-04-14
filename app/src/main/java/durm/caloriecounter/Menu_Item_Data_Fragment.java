package durm.caloriecounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
*
* This class represents the fragment that has the ingredients and the how to make section for a food.
*
*/

public class Menu_Item_Data_Fragment extends Fragment {

    // Food Titles

    String ingredients;
    String howToMake;


    private TextView ingredientsData;
    private TextView howToMakeData;


    public Menu_Item_Data_Fragment(){

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
        howToMakeData = view.findViewById(R.id.how_to_make_text);

        ingredients = MainActivity.foodActiveFragment+"";
        howToMake = MainActivity.itemOpenedNumber+"";


        if(ingredients != null){
            ingredientsData.setText(ingredients);
        } else {
            ingredientsData.setText("Error getting data!");
        }



        if(howToMake!= null){
            howToMakeData.setText(howToMake);
        } else {
            howToMakeData.setText("Error getting data!");
        }

        return view;
    }



}

