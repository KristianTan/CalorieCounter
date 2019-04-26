package durm.caloriecounter.fragments.setupfragments;

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
import durm.caloriecounter.activities.SetUpActivity;


public class FragmentSetUp_UserGoal extends Fragment {


    Button next;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private Button lose, maintain, gain;


    public FragmentSetUp_UserGoal() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setup_usergoals_fragment, container, false);

        final AppCompatActivity activity = (AppCompatActivity) view.getContext();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        mEditor = mPreferences.edit();

        next = view.findViewById(R.id.setupbuttonNext);

        lose = view.findViewById(R.id.b_lose_weight);
        maintain = view.findViewById(R.id.b_maintain_weight);
        gain = view.findViewById(R.id.b_gain_weight);


        lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("goal", 0);
                next(activity);

            }
        });
        maintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("goal", 1);
                next(activity);


            }
        });
        gain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("goal", 2);
                next(activity);

            }
        });


        return view;
    }

    void next( AppCompatActivity activity){
        mEditor.commit();
        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .show(SetUpActivity.foodChoiceFragment).hide(SetUpActivity.userGoalFragment).addToBackStack(null).commit();
    }


}
