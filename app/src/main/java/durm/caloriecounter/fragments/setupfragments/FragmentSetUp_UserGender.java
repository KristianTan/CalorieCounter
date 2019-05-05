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


public class FragmentSetUp_UserGender extends Fragment {


    Button next;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private Button male, female;
    private TextView genderText;

    public FragmentSetUp_UserGender() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setup_usergender_fragment, container, false);

        final AppCompatActivity activity = (AppCompatActivity) view.getContext();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        mEditor = mPreferences.edit();

        male = view.findViewById(R.id.b_male);
        female = view.findViewById(R.id.b_female);
        genderText = view.findViewById(R.id.activityLevel);

       // Animation a = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_fall_down);
       // male.startAnimation(a);
       // female.startAnimation(a);
      //  genderText.startAnimation(a);





        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("gender", 0);
                next(activity);

            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("gender", 1);
                next(activity);


            }
        });



        return view;
    }

    void next( AppCompatActivity activity){
        mEditor.commit();
        activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .show(SetUpActivity.foodChoiceFragment).hide(SetUpActivity.userGenderFragment).addToBackStack(null).commit();
    }


}
