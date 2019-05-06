package durm.caloriecounter.fragments.setupfragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.SetUpActivity;


public class FragmentSetUp_UserGoal extends Fragment {


    Button next;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private Button lose, maintain, gain;
    private TextView goal;


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

        lose = view.findViewById(R.id.b_lose_weight);
        maintain = view.findViewById(R.id.b_maintain_weight);
        gain = view.findViewById(R.id.b_gain_weight);
        goal = view.findViewById(R.id.activityLevel);

       // Animation a = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_fall_down);
       // lose.startAnimation(a);
       // maintain.startAnimation(a);
       // gain.startAnimation(a);
       // goal.startAnimation(a);




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
                .show(SetUpActivity.userGenderFragment).hide(SetUpActivity.userGoalFragment).addToBackStack(null).commit();
    }


}
