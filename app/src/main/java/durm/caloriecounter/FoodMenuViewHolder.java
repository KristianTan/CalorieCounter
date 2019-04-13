package durm.caloriecounter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class FoodMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



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
    public void onClick(View view){



        AppCompatActivity activity = (AppCompatActivity)view.getContext();

        // Show that fragment.
        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
                .show(MainActivity.menuFragments.get(getAdapterPosition())).hide(MainActivity.fragment1).addToBackStack(null).commit();

        // Tell the code on what fragment we are so we can access it anywhere.
        MainActivity.foodActiveFragment = getAdapterPosition();

    }






}
