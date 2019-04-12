package durm.caloriecounter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

///
/// Main Screen view.
///

public class Main_fragment extends Fragment {

    TextView titleText;

    // Test Data Arrays.
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> info = new ArrayList<>();
    // Access it from anywhere
    static String FoodChoiceVar = "All food"; // Value set for test only. Maybe make an ENUM instead?

    public  Main_fragment() {
        // Needed empty constructor.
    }

    // Don't edit this method as you won't be able tu use "R".
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titles.add("Breakfast");titles.add("Morning Snack");titles.add("Lunch");titles.add("AfterNoon Snack");titles.add("Dinner"); titles.add("Night Snack");
        info.add("300 Kcal     2 Items");info.add("250 Kcal     3 Items");info.add("100 Kcal     2 Items");info.add("500 Kcal     5 Items");info.add("200 Kcal     3 Items");info.add("600 Kcal     4 Items");
    }

    //Use this instead.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);


        final Context c = getContext();

        titleText = view.findViewById(R.id.textViewFoodType);
        titleText.setText(FoodChoiceVar + "| MENU");


        RecyclerView recyclerView = view.findViewById(R.id.fragmentRecycleView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        ViewAdapter adapter = new ViewAdapter(c,titles,info);
       // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
      //          layoutManager.getOrientation());
       // recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);

        return view;
    }



}

