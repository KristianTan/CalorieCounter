package durm.caloriecounter.fragments.settingsfragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.activities.settingsactivities.UserAccountActivity;
import durm.caloriecounter.viewAdapters.ViewAdapterFoodItem;

public class AccountSettings_Fragment extends Fragment {



    Button goal,name,erasedata;

    public AccountSettings_Fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.user_account_settings_fragment, container, false);

       final AppCompatActivity activity = (AppCompatActivity)view.getContext();

        goal = view.findViewById(R.id.buttonAccount);
        name = view.findViewById(R.id.buttonDetails);
        erasedata = view.findViewById(R.id.buttonPreferences);





       goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(UserAccountActivity.changeGoalFragment).hide(UserAccountActivity.accountSettings).addToBackStack(null).commit();
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(UserAccountActivity.changeNameFragment).hide(UserAccountActivity.accountSettings).addToBackStack(null).commit();
            }
        });
        erasedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(UserAccountActivity.eraseDataFragment).hide(UserAccountActivity.accountSettings).addToBackStack(null).commit();
            }
        });

        return view;
    }





}
