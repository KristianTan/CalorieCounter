package durm.caloriecounter.viewHolders;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.R;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.models.RecipeListSingleton;
import durm.caloriecounter.requests.ImageDownloadTask;


public class FoodMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView titleText;
    public TextView infoData;

    public FoodMenuViewHolder(@NonNull View itemView) {
        super(itemView);

        // Set the data responsable for meals.
        titleText = itemView.findViewById(R.id.ItemText);
        infoData = itemView.findViewById(R.id.ItemText_Cal);

        // Set the clicker.
        itemView.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        MainActivity.itemDataFragment.getMealImage().setImageResource(R.drawable.white_box);

        AppCompatActivity activity = (AppCompatActivity) view.getContext();

        Recipe thisRecipe = RecipeListSingleton.getInstance().recipeList.get(getAdapterPosition());

        ImageDownloadTask m = new ImageDownloadTask();
        m.activity = activity;
        m.imageView = MainActivity.itemDataFragment.getMealImage();
        m.execute(thisRecipe.getImageURL());

        if(MainActivity.itemDataFragment.getMealImage() == null) {
            MainActivity.itemDataFragment.getMealImage().setImageResource(R.drawable.fork_bg);
        }


        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .show(MainActivity.itemDataFragment).hide(MainActivity.fragment1).addToBackStack(null).commit();


        // Tell the code on what fragment we are so we can access it anywhere.
        MainActivity.foodActiveFragment = getAdapterPosition();

        String ingredients = "";


        int i = 1;
        for (String item : thisRecipe.getIngredients()) {
            ingredients = ingredients.concat(i + ".   " + item + "\n\n");
            i++;
        }

        MainActivity.itemDataFragment.getIngredientsData().setText(ingredients);
        MainActivity.itemDataFragment.setHowToMake(thisRecipe.getRecipeURL());
        MainActivity.itemDataFragment.setRecipe(thisRecipe);
        MainActivity.itemDataFragment.getRecipeName().setText(thisRecipe.getLabel());
    }

}