package durm.caloriecounter.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.activities.SearchActivity;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.models.RecipeListSingleton;
import durm.caloriecounter.requests.ImageDownloadTask;


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

        SearchActivity.itemSearchFragment.getMealImage().setImageResource(R.drawable.white_box);

        AppCompatActivity activity = (AppCompatActivity)view.getContext();

        // Show that fragment.

        // Tell the code on what fragment we are so we can access it anywhere.
//        MainActivity.foodActiveFragment = getAdapterPosition();
        Recipe thisRecipe = RecipeListSingleton.getInstance().searchResultsList.get(getAdapterPosition());

        ImageDownloadTask m = new ImageDownloadTask();
        m.activity = activity;
        m.imageView = SearchActivity.itemSearchFragment.getMealImage();
        m.execute(thisRecipe.getImageURL());

        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                .show(SearchActivity.itemSearchFragment).hide(SearchActivity.searchFragment).addToBackStack(null).commit();

        String ingredients = "";

        int i =1;
        for(String item : thisRecipe.getIngredients()) {
            ingredients = ingredients.concat(i + ".   " + item + "\n");
            i++;
        }

        SearchActivity.itemSearchFragment.getIngredientsData().setText(ingredients);
        SearchActivity.itemSearchFragment.setHowToMake(thisRecipe.getRecipeURL());
        SearchActivity.itemSearchFragment.setRecipe(thisRecipe);
        SearchActivity.itemSearchFragment.getRecipeName().setText(thisRecipe.getLabel());
    }


}