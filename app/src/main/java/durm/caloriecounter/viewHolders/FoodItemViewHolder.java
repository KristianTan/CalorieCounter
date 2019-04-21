package durm.caloriecounter.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;

public class FoodItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



public TextView titleText;


public FoodItemViewHolder(@NonNull View itemView) {
        super(itemView);

        // Set the data responsable for meals.
        titleText = itemView.findViewById(R.id.ItemText);

          // Set the clicker.
        itemView.setOnClickListener(this);
        }



@Override
public void onClick(View view){

    AppCompatActivity activity = (AppCompatActivity)view.getContext();

    // Show that fragment.
    activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
            .show(MainActivity.itemDataFragment).hide(MainActivity.menuFragments.get(MainActivity.foodActiveFragment)).addToBackStack(null).commit();


    MainActivity.itemOpenedNumber = getAdapterPosition();


    }
}
