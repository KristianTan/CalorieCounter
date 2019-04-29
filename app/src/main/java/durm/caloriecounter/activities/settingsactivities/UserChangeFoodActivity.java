package durm.caloriecounter.activities.settingsactivities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.enumerators.enumFoodType;
import durm.caloriecounter.fragments.Main_fragment;

public class  UserChangeFoodActivity extends AppCompatActivity {



    private TextView name;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button allfood,vegan,fruit,meat,sweet,vegetarian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_food_preferences_acitvity);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();


        allfood = findViewById(R.id.buttonAllFood);
        vegan = findViewById(R.id.buttonVegan);
        fruit = findViewById(R.id.buttonfruits);
        meat = findViewById(R.id.buttonMeat);
        sweet = findViewById(R.id.buttonSweets);
        vegetarian = findViewById(R.id.buttonVegetarian);

        switch (mPreferences.getInt("foodValue",0)){

            case 0:
                allfood.setBackgroundColor(Color.parseColor("#2196F3"));
                allfood.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 1:
                fruit.setBackgroundColor(Color.parseColor("#2196F3"));
                fruit.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 2:
                vegan.setBackgroundColor(Color.parseColor("#2196F3"));
                vegan.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 3:
                meat.setBackgroundColor(Color.parseColor("#2196F3"));
                meat.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 4:
                sweet.setBackgroundColor(Color.parseColor("#2196F3"));
                sweet.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 5:
                vegetarian.setBackgroundColor(Color.parseColor("#2196F3"));
                vegetarian.setTextColor(Color.parseColor("#FFFFFF"));
                break;
        }


        allfood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mPreferences.getInt("foodValue",0) != 0){
                    mEditor.putInt("foodValue",0);
                    mEditor.commit();
                    finish();
                }
            }
        });
        fruit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mPreferences.getInt("foodValue",0) != 1){
                    mEditor.putInt("foodValue",1);
                    mEditor.commit();
                    finish();
                }
            }
        });

        vegan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mPreferences.getInt("foodValue",0) != 2){
                    mEditor.putInt("foodValue",2);
                    mEditor.commit();
                    finish();
                }
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mPreferences.getInt("foodValue",0) != 3){
                    mEditor.putInt("foodValue",3);
                    mEditor.commit();
                    finish();
                }
            }
        });
        sweet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mPreferences.getInt("foodValue",0) != 4){
                    mEditor.putInt("foodValue",4);
                    mEditor.commit();
                    finish();
                }
            }
        });
        vegetarian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mPreferences.getInt("foodValue",0) != 5){
                    mEditor.putInt("foodValue",5);
                    mEditor.commit();
                    finish();
                }
            }
        });



    }
}
