package durm.caloriecounter.fragments.setupfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.activities.SetUpActivity;


public class FragmentSetUp_Welcome extends Fragment {


    Button next;

    public FragmentSetUp_Welcome(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setup_welcome_fragment, container, false);

       final AppCompatActivity activity = (AppCompatActivity)view.getContext();

        next = view.findViewById(R.id.setupbuttonNext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(SetUpActivity.userNameFragment).hide(SetUpActivity.welcomeFragment).addToBackStack(null).commit();

            }
        });


        return view;
    }



}
