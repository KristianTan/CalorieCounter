package durm.caloriecounter.viewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import durm.caloriecounter.R;
import durm.caloriecounter.viewHolders.FoodItemViewHolder;

public class ViewAdapterFoodItem extends RecyclerView.Adapter<FoodItemViewHolder> {


    private LayoutInflater layoutInflater;

    // Used to set the data for our holders.
    private List<String> titleData;

    public ViewAdapterFoodItem (Context context, List<String> titleData ){
        this.layoutInflater = LayoutInflater.from(context);
        this.titleData = titleData;

    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.layout_food_item,viewGroup,false);

        FoodItemViewHolder holder = new FoodItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder foodItemViewHolder, int i) {

        // Get the data.
        String title = titleData.get(i);



        // Set the title.
        foodItemViewHolder.titleText.setText(title);

    }


    @Override
    public int getItemCount() {

        if(titleData != null) {
            return titleData.size();
        } else {
            return 0;
        }
    }
}
