package durm.caloriecounter.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

public class Menu_Item_Data_Fragment extends Fragment {

    // Food Titles



    String ingredients;
    String howToMake;

    private TextView ingredientsData;
    private TextView howToMakeData;
    private TextView recipeName;
    private CardView recipeCard;
    private ImageView mealImage;
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
        recipeName = view.findViewById(R.id.recipe_name);
        mealImage = view.findViewById(R.id.imageMeal);
        recipeCard = view.findViewById(R.id.recipe_card);

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

                Recipes_fragment.getNoRecipesText().setVisibility(View.GONE);

                Gson gson = new Gson();
                String json = gson.toJson(recipe);

                Map<String, ?> prefs = mPreferences.getAll();
                int count = 0;
                Pattern pattern = Pattern.compile("^(savedRecipe)[\\d]+");
                boolean repeated = false;

                for(String key : prefs.keySet()) {
                    Matcher matcher = pattern.matcher(key);
                    if(prefs.get(key) instanceof String && matcher.matches()) {
                        count += 1;
                        Recipe r = gson.fromJson((String)prefs.get(key), Recipe.class);
                        if(r.getLabel().equals(recipe.getLabel())) {
                            repeated = true;
                            break;
                        }
                    }
                }

                if(repeated) {
                    Toast notSavedToast = Toast.makeText(view.getContext(), "You have already saved this recipe", Toast.LENGTH_SHORT);
                    notSavedToast.show();
                } else {
                    Recipes_fragment.titles.add(recipe.getLabel());
                    Recipes_fragment.info.add(recipe.getCalories() / recipe.getServings() + " cal");
                    RecipeListSingleton.getInstance().savedRecipeList.add(recipe);
                    Recipes_fragment.getAdapter().notifyDataSetChanged();
                    mEditor.putString("savedRecipe" + count, json);
                    mEditor.commit();

                    Toast savedToast = Toast.makeText(view.getContext(), "Recipe saved", Toast.LENGTH_SHORT);
                    savedToast.show();

                }

                AppCompatActivity activity = (AppCompatActivity)view.getContext();

                activity.getSupportFragmentManager().popBackStack();





            }
        });
        
        return view;
    }

    public void setHowToMake(String howToMake) {
        this.howToMake = howToMake;
    }

    public TextView getIngredientsData() {
        return ingredientsData;
    }

    public TextView getRecipeName(){
        return recipeName;
    }

    public ImageView getMealImage(){return mealImage;}

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public CardView getRecipeCard() {
        return recipeCard;
    }
}

