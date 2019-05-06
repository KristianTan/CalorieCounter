package durm.caloriecounter.viewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import durm.caloriecounter.R;
import durm.caloriecounter.viewHolders.FoodMenuViewHolder;
import durm.caloriecounter.viewHolders.SearchResultsViewHolder;

public class SearchResultsViewAdapter extends RecyclerView.Adapter<SearchResultsViewHolder> {


    private LayoutInflater layoutInflater;

    // Used to set the data for our holders.
    private List<String> titleData,infoData;


    public SearchResultsViewAdapter(Context context, List<String> titleData, List<String> info ){
        this.layoutInflater = LayoutInflater.from(context);
        this.titleData = titleData;
        this.infoData = info;
    }

    @NonNull
    @Override
    public SearchResultsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.layout_menu_item,viewGroup,false);
        SearchResultsViewHolder holder = new SearchResultsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultsViewHolder searchResultsViewHolder, int i) {

        // Get the data.
        String title = titleData.get(i);
        String info = infoData.get(i);




        // Set the title.
        searchResultsViewHolder.titleText.setText(title);


        // Set the info.
        if(info!=null){
            searchResultsViewHolder.infoData.setText(info);
        }
        else {
            searchResultsViewHolder.infoData.setText("ERROR GETTING DATA");
        }



    }

    @Override
    public int getItemCount() {
        if(titleData != null) {
            return titleData.size();
        } else {
            return 0;
        }
    }

    public void updateAll(ArrayList titles, ArrayList calories) {
        this.titleData = titles;
        this.infoData = calories;
    }
}
