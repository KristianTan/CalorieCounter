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


public class FragmentSetUp_FoodChoice extends Fragment {


    Button next;
    Button allfood,vegan,fruit,meat,sweet,vegetarian;

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
        fruit = view.findViewById(R.id.buttonfruits);
        meat = view.findViewById(R.id.buttonMeat);
        sweet = view.findViewById(R.id.buttonSweets);
        vegetarian = view.findViewById(R.id.buttonVegetarian);


       allfood.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               mEditor.putInt("foodValue",0);
               completeSetup(activity);
           }
       });
        fruit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditor.putInt("foodValue",1);
                completeSetup(activity);
            }
        });

        vegan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditor.putInt("foodValue",2);
                completeSetup(activity);
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditor.putInt("foodValue",3);
                completeSetup(activity);
            }
        });
        sweet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditor.putInt("foodValue",4);
                completeSetup(activity);
            }
        });
        vegetarian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mEditor.putInt("foodValue",5);
                completeSetup(activity);
            }
        });



        return view;
    }


    public void completeSetup( AppCompatActivity activity){
        mEditor.putInt("setUP",1);
        mEditor.commit();

        Intent profileIntent = new Intent(activity, MainActivity.class);
        profileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(profileIntent);
        ActivityCompat.finishAffinity(activity);
    }


}
