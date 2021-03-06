package durm.caloriecounter.fragments.setupfragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.activities.SetUpActivity;
import durm.caloriecounter.enumerators.enumActivityLevel;
import durm.caloriecounter.enumerators.enumGender;
import durm.caloriecounter.enumerators.enumGoal;
import durm.caloriecounter.enumerators.enumUnit;
import durm.caloriecounter.fragments.Main_fragment;
import durm.caloriecounter.models.User;
import durm.caloriecounter.requests.CalculateCaloricIntake;


public class FragmentSetUp_FoodChoice extends Fragment {


    Button next;
    Button allfood,vegan,meat,vegetarian;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    public FragmentSetUp_FoodChoice(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.setup_foodchoice_fragment, container, false);

       final AppCompatActivity activity = (AppCompatActivity)view.getContext();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        mEditor = mPreferences.edit();

        allfood = view.findViewById(R.id.buttonAllFood);
        vegan = view.findViewById(R.id.buttonVegan);
        meat = view.findViewById(R.id.buttonMeat);
        vegetarian = view.findViewById(R.id.buttonVegetarian);


       allfood.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               mEditor.putInt("foodValue",0);
               completeSetup(activity);
           }
       });

        vegan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditor.putInt("foodValue",1);
                completeSetup(activity);
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditor.putInt("foodValue",2);
                completeSetup(activity);
            }
        });
        vegetarian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditor.putInt("foodValue",3);
                completeSetup(activity);
            }
        });



        return view;
    }


    public void completeSetup( AppCompatActivity activity){
        mEditor.putInt("setUP",1);
        mEditor.commit();

        CalculateCaloricIntake calculateCaloricIntake = new CalculateCaloricIntake();

        User user = new User(
                mPreferences.getInt("weight", 0),
                mPreferences.getInt("height", 0),
                mPreferences.getInt("age", 0),
                enumGender.values()[mPreferences.getInt("gender", 0)],
                enumGoal.values()[mPreferences.getInt("goal", 0)],
                enumUnit.values()[mPreferences.getInt("unit", 0)],
                enumActivityLevel.values()[mPreferences.getInt("activity", 0)]

        );

        user.setCaloricIntake(calculateCaloricIntake.calculateCalories(user));

        mEditor.putInt("caloricIntake", user.getCaloricIntake());
        mEditor.commit();

        Main_fragment.titles.clear();
        Main_fragment.info.clear();

        Intent profileIntent = new Intent(activity, MainActivity.class);
        profileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(profileIntent);
        ActivityCompat.finishAffinity(activity);
    }


}
