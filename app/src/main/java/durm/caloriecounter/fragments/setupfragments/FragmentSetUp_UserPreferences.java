package durm.caloriecounter.fragments.setupfragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.SetUpActivity;


public class FragmentSetUp_UserPreferences extends Fragment {


    Button next;
    private int SystemUsed;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText height,weight,age;
    private Button metric,imperial;
    private Button low,mid,high;
    private TextView heightUnits,weightUnits;
    private TextView textTitle,activityText,ageText,heightText,weightText,yearsText;

    public FragmentSetUp_UserPreferences(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setup_userspreferences_fragment, container, false);

       final AppCompatActivity activity = (AppCompatActivity)view.getContext();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        mEditor = mPreferences.edit();
        next = view.findViewById(R.id.setupbuttonNext);

        // set the edit text
        height = view.findViewById(R.id.setup_height);
        height.setInputType(
                InputType.TYPE_CLASS_NUMBER);
        weight = view.findViewById(R.id.setup_weight);
        weight.setInputType(
                InputType.TYPE_CLASS_NUMBER);
        age = view.findViewById(R.id.setup_age);
        age.setInputType(
                InputType.TYPE_CLASS_NUMBER);

        heightUnits = view.findViewById(R.id.heightUnits);
        weightUnits = view.findViewById(R.id.weightUnits);

        // set the buttons
        metric = view.findViewById(R.id.b_metric);
        imperial = view.findViewById(R.id.b_imperial);
        low = view.findViewById(R.id.b_lose_weight);
        mid = view.findViewById(R.id.b_maintain_weight);
        high = view.findViewById(R.id.b_gain_weight);
        textTitle = view.findViewById(R.id.textTitle);
        activityText = view.findViewById(R.id.activityLevel);
        ageText = view.findViewById(R.id.ageText);
        heightText = view.findViewById(R.id.heightText);
        weightText = view.findViewById(R.id.weightText);
        yearsText = view.findViewById(R.id.years);

       // setAnimations(view);



        metric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SystemUsed = 0; // metric default

                // set to active
                metric.setBackgroundColor(Color.parseColor("#03A9F4"));

                // deactivate the other button
                imperial.setBackgroundColor(Color.parseColor("#002196F3"));

                heightUnits.setText("cm");
                weightUnits.setText("kg");

                mEditor.putInt("units", SystemUsed);

            }
        });

        imperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SystemUsed = 1; // imperial

                // set to active
                imperial.setBackgroundColor(Color.parseColor("#03A9F4"));

                // deactivate the other button
                metric.setBackgroundColor(Color.parseColor("#002196F3"));
                heightUnits.setText("inch");
                weightUnits.setText("pounds");

                mEditor.putInt("units", SystemUsed);
            }
        });

        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("activity",0);
                // set to active
                low.setBackgroundColor(Color.parseColor("#03A9F4"));
                // deactivate the other button
               mid.setBackgroundColor(Color.parseColor("#002196F3"));
               high.setBackgroundColor(Color.parseColor("#002196F3"));

            }
        });
        
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("activity",1);
                // set to active
                mid.setBackgroundColor(Color.parseColor("#03A9F4"));
                // deactivate the other button
                low.setBackgroundColor(Color.parseColor("#002196F3"));
                high.setBackgroundColor(Color.parseColor("#002196F3"));

            }
        });

        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("activity",2);
                // set to active
                high.setBackgroundColor(Color.parseColor("#03A9F4"));
                // deactivate the other button
                mid.setBackgroundColor(Color.parseColor("#002196F3"));
                low.setBackgroundColor(Color.parseColor("#002196F3"));

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // in case the user deletes the text
                if (height.getText().toString().equals("")) {
                    height.setText("0");
                }
                if (weight.getText().toString().equals("")) {
                    weight.setText("0");
                }
                if (age.getText().toString().equals("")) {
                    age.setText("0");
                }

                if (height.getText().toString().equals("0") || weight.getText().toString().equals(0) || age.getText().toString().equals("0")) {
                    Toast.makeText(activity, "Height, weight and age cannot be 0!", Toast.LENGTH_SHORT).show();
                }
                else
                if(Integer.parseInt(age.getText().toString()) > 100){
                    Toast.makeText(activity, "Hey, you are too old for this app!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int heightInt = Integer.parseInt(height.getText().toString());
                    int weightInt = Integer.parseInt(weight.getText().toString());

                    /*
                    if (SystemUsed == 1) {

                        // transform everything back to metric cm, kg
                        heightInt = (int) (heightInt * 2.54);
                        weightInt = (int) (weightInt / 2.2f);
                    }

                    */

                    mEditor.putInt("height", heightInt);
                    mEditor.putInt("weight", weightInt);
                    mEditor.putInt("age", Integer.parseInt(age.getText().toString()));
                    mEditor.commit();


                    activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                            .show(SetUpActivity.userGoalFragment).hide(SetUpActivity.userPreferencesFragment).addToBackStack(null).commit();

                }
            }
        });


        return view;
    }


    private void setAnimations(View view){
        Animation a = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_fall_down);
        textTitle.startAnimation(a);
        metric.startAnimation(a);
        imperial.startAnimation(a);
        heightUnits.startAnimation(a);
        height.startAnimation(a);
        weightUnits.startAnimation(a);
        weight.startAnimation(a);
        activityText.startAnimation(a);
        low.startAnimation(a);
        mid.startAnimation(a);
        high.startAnimation(a);
        age.startAnimation(a);
        ageText.startAnimation(a);
        heightText.startAnimation(a);
        weightText.startAnimation(a);
        yearsText.startAnimation(a);
        next.startAnimation(a);

    }

}
