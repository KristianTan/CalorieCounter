package durm.caloriecounter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.viewAdapters.ViewAdapterFoodItem;

public class Food_menu_fragment_open extends Fragment {

    public String titleOfFragment;
    private TextView number;

    // Food Titles
    public ArrayList<String> titles = new ArrayList<>();

    // Fragment Image;
    private ImageView image;

    public Food_menu_fragment_open(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Wee need an async method for this one
        titleOfFragment = MainActivity.foodActiveFragment+" Frag";
        titles.add("TestFood");
    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipes_fragment_open, container, false);



        // it's established at the creation of the fragment so it won;t work


        number = view.findViewById(R.id.TextNumber_open);

        // We will have to set the images here.
        image = view.findViewById(R.id.imageFragmentOpen);

        // Don't set a null string.
        if(titleOfFragment!=null){
            number.setText(titleOfFragment);
        }
        else {
            number.setText("ERROR");
        }

        // Test
        RecyclerView recyclerView = view.findViewById(R.id.food_item_recycle_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        ViewAdapterFoodItem adapter = new ViewAdapterFoodItem(view.getContext(),titles);

        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);


        return view;
    }




}
