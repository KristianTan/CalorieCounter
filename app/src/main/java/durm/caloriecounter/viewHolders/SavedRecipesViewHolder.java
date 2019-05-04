package durm.caloriecounter.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.models.RecipeListSingleton;


public class SavedRecipesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



    public TextView titleText;
    public TextView infoData;

    public SavedRecipesViewHolder(@NonNull View itemView) {
        super(itemView);

        // Set the data responsable for meals.
        titleText = itemView.findViewById(R.id.ItemText);
        infoData = itemView.findViewById(R.id.ItemText_Cal);

        // Set the clicker.
        itemView.setOnClickListener(this);


    }



    @Override
    public void onClick(View view){

        Recipe thisRecipe = RecipeListSingleton.getInstance().savedRecipeList.get(getAdapterPosition());

        String ingredients = "";

        int i =1;
        for(String item : thisRecipe.getIngredients()) {
            ingredients = ingredients.concat(i+".   "+item + "\n\n");
            i++;
        }

        MainActivity.saveRecipeDataFragment.getIngredientsData().setText(ingredients);
        MainActivity.saveRecipeDataFragment.setHowToMake(thisRecipe.getRecipeURL());
        MainActivity.saveRecipeDataFragment.setRecipe(thisRecipe);
        MainActivity.saveRecipeDataFragment.getRecipeName().setText(thisRecipe.getLabel());

        AppCompatActivity activity = (AppCompatActivity)view.getContext();

        // Show that fragment.
        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                .show(MainActivity.saveRecipeDataFragment).hide(MainActivity.fragment2).addToBackStack(null).commit();

    }






}
