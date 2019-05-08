package durm.caloriecounter.fragments.settingsfragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.settingsactivities.UserAccountActivity;

public class AccountSettings_Fragment extends Fragment {



    Button goal, name, eraseData;
    ImageButton back;


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
        eraseData = view.findViewById(R.id.buttonPreferences);
        back = view.findViewById(R.id.imageButton);

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
        eraseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(UserAccountActivity.eraseDataFragment).hide(UserAccountActivity.accountSettings).addToBackStack(null).commit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });


        return view;
    }


}
