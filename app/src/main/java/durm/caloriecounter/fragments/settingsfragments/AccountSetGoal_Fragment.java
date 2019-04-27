package durm.caloriecounter.fragments.settingsfragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.settingsactivities.UserAccountActivity;

public class AccountSetGoal_Fragment extends Fragment {


    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button lose, maintain, gain;

    public AccountSetGoal_Fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_account_changegoal_fragment, container, false);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        mEditor = mPreferences.edit();

        final AppCompatActivity activity = (AppCompatActivity)view.getContext();



        lose = view.findViewById(R.id.b_lose_weight);
        maintain = view.findViewById(R.id.b_maintain_weight);
        gain = view.findViewById(R.id.b_gain_weight);


        lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("goal", 0);
                mEditor.commit();
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(UserAccountActivity.accountSettings).hide(UserAccountActivity.changeGoalFragment).commit();

            }
        });
        maintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("goal", 1);
                mEditor.commit();
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(UserAccountActivity.accountSettings).hide(UserAccountActivity.changeGoalFragment).commit();


            }
        });
        gain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("goal", 2);
                mEditor.commit();
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(UserAccountActivity.accountSettings).hide(UserAccountActivity.changeGoalFragment).commit();

            }
        });

        return view;
    }




}
