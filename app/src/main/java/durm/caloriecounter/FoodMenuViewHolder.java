package durm.caloriecounter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class FoodMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



    public TextView titleText;
    public TextView infoData;

    public FoodMenuViewHolder(@NonNull View itemView) {
        super(itemView);

        titleText = itemView.findViewById(R.id.ItemText);
        infoData = itemView.findViewById(R.id.ItemText_Cal);
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View view){

        Toast.makeText(view.getContext(), getAdapterPosition()+"", Toast.LENGTH_LONG).show();

        AppCompatActivity activity = (AppCompatActivity)view.getContext();

        MainActivity.menuFragments.get(getAdapterPosition()).number.setText(getAdapterPosition()+"");
        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
                .show(MainActivity.menuFragments.get(getAdapterPosition())).hide(MainActivity.fragment1).addToBackStack(null).commit();

    }
}
