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
import android.widget.ImageButton;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.settingsactivities.UserAccountActivity;
import durm.caloriecounter.enumerators.enumActivityLevel;
import durm.caloriecounter.enumerators.enumGender;
import durm.caloriecounter.enumerators.enumGoal;
import durm.caloriecounter.enumerators.enumUnit;
import durm.caloriecounter.models.User;
import durm.caloriecounter.requests.CalculateCaloricIntake;

public class AccountSetGoal_Fragment extends Fragment {


    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button lose, maintain, gain;
    private ImageButton back;

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
        back = view.findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().popBackStack();
            }
        });

        lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = mPreferences.getInt("goal", 9);
                mEditor.putInt("goal", 0);
                mEditor.commit();
                int j = mPreferences.getInt("goal", 9);

                CalculateCaloricIntake calculateCaloricIntake = new CalculateCaloricIntake();

                int cal = calculateCaloricIntake.calculateCalories(new User(
                        mPreferences.getInt("weight", 0),
                        mPreferences.getInt("height", 0),
                        mPreferences.getInt("age", 0),
                        enumGender.values()[mPreferences.getInt("gender", 0)],
                        enumGoal.values()[mPreferences.getInt("goal", 0)],
                        enumUnit.values()[mPreferences.getInt("unit", 0)],
                        enumActivityLevel.values()[mPreferences.getInt("activity", 0)]
                ));

                mEditor.putInt("caloricIntake", cal);

                mEditor.commit();
               // activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
               //         .show(UserAccountActivity.accountSettings).hide(UserAccountActivity.changeGoalFragment).commit();
                activity.getSupportFragmentManager().popBackStack();
            }
        });
        maintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("goal", 1);
                mEditor.commit();

                CalculateCaloricIntake calculateCaloricIntake = new CalculateCaloricIntake();

                int cal = calculateCaloricIntake.calculateCalories(new User(
                        mPreferences.getInt("weight", 0),
                        mPreferences.getInt("height", 0),
                        mPreferences.getInt("age", 0),
                        enumGender.values()[mPreferences.getInt("gender", 0)],
                        enumGoal.values()[mPreferences.getInt("goal", 0)],
                        enumUnit.values()[mPreferences.getInt("unit", 0)],
                        enumActivityLevel.values()[mPreferences.getInt("activity", 0)]
                ));

                mEditor.putInt("caloricIntake", cal);

                mEditor.commit();
               // activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
               //         .show(UserAccountActivity.accountSettings).hide(UserAccountActivity.changeGoalFragment).commit();
                activity.getSupportFragmentManager().popBackStack();

            }
        });
        gain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("goal", 2);
                mEditor.commit();

                CalculateCaloricIntake calculateCaloricIntake = new CalculateCaloricIntake();

                int cal = calculateCaloricIntake.calculateCalories(new User(
                        mPreferences.getInt("weight", 0),
                        mPreferences.getInt("height", 0),
                        mPreferences.getInt("age", 0),
                        enumGender.values()[mPreferences.getInt("gender", 0)],
                        enumGoal.values()[mPreferences.getInt("goal", 0)],
                        enumUnit.values()[mPreferences.getInt("unit", 0)],
                        enumActivityLevel.values()[mPreferences.getInt("activity", 0)]
                ));

                mEditor.putInt("caloricIntake", cal);

                mEditor.commit();
               // activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
               //         .show(UserAccountActivity.accountSettings).hide(UserAccountActivity.changeGoalFragment).commit();
                activity.getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }




}
