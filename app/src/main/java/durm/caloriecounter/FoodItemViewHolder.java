package durm.caloriecounter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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



    }
}
