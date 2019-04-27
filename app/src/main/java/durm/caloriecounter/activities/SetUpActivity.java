package durm.caloriecounter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import durm.caloriecounter.R;
import durm.caloriecounter.fragments.setupfragments.FragmentSetUp_FoodChoice;
import durm.caloriecounter.fragments.setupfragments.FragmentSetUp_UserGender;
import durm.caloriecounter.fragments.setupfragments.FragmentSetUp_UserGoal;
import durm.caloriecounter.fragments.setupfragments.FragmentSetUp_UserName;
import durm.caloriecounter.fragments.setupfragments.FragmentSetUp_UserPreferences;
import durm.caloriecounter.fragments.setupfragments.FragmentSetUp_Welcome;

public class SetUpActivity extends AppCompatActivity {

    // This activity is launched at the first start of the app.

    // create the welcome fragment
    public static  Fragment welcomeFragment = new FragmentSetUp_Welcome();
    public static  Fragment userPreferencesFragment = new FragmentSetUp_UserPreferences();
    public static  Fragment userNameFragment = new FragmentSetUp_UserName();
    public static  Fragment userGoalFragment = new FragmentSetUp_UserGoal();
    public static  Fragment foodChoiceFragment = new FragmentSetUp_FoodChoice();
    public static  Fragment userGenderFragment = new FragmentSetUp_UserGender();

    // Create a fragment manager
    public final FragmentManager fm = getSupportFragmentManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        fm.beginTransaction().add(R.id.setup_container, userGenderFragment, "6").hide(userGenderFragment).commit();
        fm.beginTransaction().add(R.id.setup_container, userGoalFragment, "4").hide(userGoalFragment).commit();
        fm.beginTransaction().add(R.id.setup_container, foodChoiceFragment, "5").hide(foodChoiceFragment).commit();
        fm.beginTransaction().add(R.id.setup_container, userNameFragment, "3").hide(userNameFragment).commit();
        fm.beginTransaction().add(R.id.setup_container,userPreferencesFragment , "2").hide(userPreferencesFragment).commit();
        fm.beginTransaction().add(R.id.setup_container,welcomeFragment , "1").commit();


    }
}