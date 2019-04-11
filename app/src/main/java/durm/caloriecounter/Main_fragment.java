package durm.caloriecounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

///
/// Main Screen view
///

public class Main_fragment extends Fragment {


    public  Main_fragment() {

    }

    // DON"T EDIT THE ON CREATE
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); }

    //USE THIS ONE INSTEAD
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);



        TextView testtext = view.findViewById(R.id.textView);
        testtext.setText("All food | Menu");

        return view;
    }
}