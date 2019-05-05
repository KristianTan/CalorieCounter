package durm.caloriecounter.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.models.RecipeListSingleton;


public class SearchResultsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



    public TextView titleText;
    public TextView infoData;

    public SearchResultsViewHolder(@NonNull View itemView) {
        super(itemView);

        // Set the data responsable for meals.
        titleText = itemView.findViewById(R.id.ItemText);
        infoData = itemView.findViewById(R.id.ItemText_Cal);

        // Set the clicker.
        itemView.setOnClickListener(this);


    }



    @Override
    public void onClick(View view){

        AppCompatActivity activity = (AppCompatActivity)view.getContext();

        // Show that fragment.

        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out,android.R.anim.fade_in,android.R.anim.fade_out)
                .show(MainActivity.itemDataFragment).hide(MainActivity.fragment2).addToBackStack(null).commit();

//        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
//              .show(MainActivity.itemDataFragment).hide(MainActivity.fragment1).addToBackStack(null).commit();



        // Tell the code on what fragment we are so we can access it anywhere.
//        MainActivity.foodActiveFragment = getAdapterPosition();

        String ingredients = "";

        Recipe thisRecipe = RecipeListSingleton.getInstance().searchResultsList.get(getAdapterPosition());

        int i =1;
        for(String item : thisRecipe.getIngredients()) {
            ingredients = ingredients.concat(i+".   "+item + "\n\n");
            i++;
        }

        MainActivity.itemDataFragment.getIngredientsData().setText(ingredients);
        MainActivity.itemDataFragment.setHowToMake(thisRecipe.getRecipeURL());
        MainActivity.itemDataFragment.setRecipe(thisRecipe);
        MainActivity.itemDataFragment.getRecipeName().setText(thisRecipe.getLabel());

//        MainActivity.itemDataFragment.getHowToMakeData().setText(MainActivity.itemDataFragment.getHowToMake());
        Toast.makeText(activity,getAdapterPosition()+"", Toast.LENGTH_SHORT).show();

    }






}