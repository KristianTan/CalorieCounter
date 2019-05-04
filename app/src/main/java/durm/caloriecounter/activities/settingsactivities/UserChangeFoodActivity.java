package durm.caloriecounter.activities.settingsactivities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import durm.caloriecounter.R;

public class UserChangeFoodActivity extends AppCompatActivity {


    public final FragmentManager fm = getSupportFragmentManager();

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button allfood,vegan,meat,vegetarian;
    private ImageButton back;
    private String buttonPressedColor = "#03A9F4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_food_preferences_acitvity);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();


        allfood = findViewById(R.id.buttonAllFood);
        vegan = findViewById(R.id.buttonVegan);
        meat = findViewById(R.id.buttonMeat);
        vegetarian = findViewById(R.id.buttonVegetarian);
        back = findViewById(R.id.imageButton);

        switch (mPreferences.getInt("foodValue",0)){

            case 0:
                allfood.setBackgroundColor(Color.parseColor(buttonPressedColor));
                allfood.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 1:
                vegan.setBackgroundColor(Color.parseColor(buttonPressedColor));
                vegan.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 2:
                meat.setBackgroundColor(Color.parseColor(buttonPressedColor));
                meat.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case 3:
                vegetarian.setBackgroundColor(Color.parseColor(buttonPressedColor));
                vegetarian.setTextColor(Color.parseColor("#FFFFFF"));
                break;
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        allfood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mPreferences.getInt("foodValue",0) != 0){
                    mEditor.putInt("foodValue",0);
                    mEditor.commit();
                    fm.popBackStack();
                    finish();
                }
            }
        });

        vegan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(mPreferences.getInt("foodValue",0) != 1){
                    mEditor.putInt("foodValue",1);
                    mEditor.commit();
                    fm.popBackStack();
                    finish();
                }
            }
        });

        meat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mPreferences.getInt("foodValue",0) != 2){
                    mEditor.putInt("foodValue",2);
                    mEditor.commit();
                    fm.popBackStack();
                    finish();
                }
            }
        });
        vegetarian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mPreferences.getInt("foodValue",0) != 3){
                    mEditor.putInt("foodValue",3);
                    fm.popBackStack();
                    mEditor.commit();
                    finish();
                }
            }
        });



    }


}
