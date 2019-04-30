package durm.caloriecounter.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.models.Recipe;

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
    private Recipe recipe;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button howToMakeButton;
    private Button saveButton;


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
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mEditor = mPreferences.edit();

        ingredientsData = view.findViewById(R.id.ingredients_text);

        howToMakeButton = view.findViewById(R.id.howToButton);
        saveButton = view.findViewById(R.id.saveButton);

        ingredients = MainActivity.foodActiveFragment + "";
        howToMake = MainActivity.itemOpenedNumber + "";


        if (ingredients != null) {
            ingredientsData.setText(ingredients);
        } else {
            ingredientsData.setText(R.string.error_data);
        }

        howToMakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(howToMake));
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(recipe);

                Map<String, ?> prefs = mPreferences.getAll();
                int count = 0;
                Pattern pattern = Pattern.compile("^(savedRecipe)[\\d]+");

                for(String key : prefs.keySet()) {
                    Matcher matcher = pattern.matcher(key);
                    if(prefs.get(key) instanceof String && matcher.matches()) {
                        count += 1;
                    }
                }


                mEditor.putString("savedRecipe" + count, json);
                mEditor.commit();

            }
        });
        
        return view;
    }




    public String getHowToMake() {
        return howToMake;
    }

    public void setHowToMake(String howToMake) {
        this.howToMake = howToMake;
    }
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


    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}

