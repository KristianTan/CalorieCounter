package durm.caloriecounter.viewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

import durm.caloriecounter.R;
import durm.caloriecounter.viewHolders.SavedRecipesViewHolder;

public class SavedRecipesViewAdapter extends RecyclerView.Adapter<SavedRecipesViewHolder> {


    private LayoutInflater layoutInflater;

    // Used to set the data for our holders.
    private List<String> titleData,infoData;

    public SavedRecipesViewAdapter(Context context, List<String> titleData, List<String> info ){
        this.layoutInflater = LayoutInflater.from(context);
        this.titleData = titleData;
        this.infoData = info;
    }

    @NonNull
    @Override
    public SavedRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.layout_menu_item,viewGroup,false);

        SavedRecipesViewHolder holder = new SavedRecipesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedRecipesViewHolder foodMenuViewHolder, int i) {

        // Get the data.
        String title = titleData.get(i);
        String info = infoData.get(i);


        // Set the title.
        foodMenuViewHolder.titleText.setText(title);


        // Set the info.
        foodMenuViewHolder.infoData.setText(info);






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
