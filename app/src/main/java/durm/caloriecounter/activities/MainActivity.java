package durm.caloriecounter.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import durm.caloriecounter.activities.settingsactivities.UserSettingsActivity;
import durm.caloriecounter.fragments.Food_menu_fragment_open;
import durm.caloriecounter.fragments.Main_fragment;
import durm.caloriecounter.fragments.Menu_Item_Data_Fragment;
import durm.caloriecounter.R;
import durm.caloriecounter.fragments.Recipe_Item_Data_Fragment;
import durm.caloriecounter.fragments.Recipes_fragment;

public class MainActivity extends AppCompatActivity {


    // Create the toolbar.
    private  Toolbar toolbar;

    // Create the shared prefs variables.

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    // Create the fragments.
    public static final Fragment fragment1 = new Main_fragment();
    public static final Fragment fragment2 = new Recipes_fragment();
    public static final Fragment itemDataFragment = new Menu_Item_Data_Fragment();
    public static final Fragment saveRecipeDataFragment = new Recipe_Item_Data_Fragment();
    public static final Fragment foodListFragment = new Food_menu_fragment_open();

    // Experimental for now.
    public static int numberOfMeals = 6;
    public static int foodActiveFragment;
    public static int itemOpenedNumber;

    // Create a fragment manager
    public final FragmentManager fmMain = getSupportFragmentManager();

    // Assign an active fragment
    public Fragment active = fragment1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Call the method responsible for creating the toolbar
        toolbarOptions();

        // Set the SharedPrefs variable
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();


        // Check if the first time set up of the app has been completed !IMPORTANT

        if(mPreferences.getInt("setUP",0) == 0){
            // If the setup was never done launch the set up activity

            Intent profileIntent = new Intent(this, SetUpActivity.class);
            profileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           startActivity(profileIntent);
           ActivityCompat.finishAffinity(this);
        }

        // Create nav-bar
        BottomNavigationView navigation = findViewById(R.id.navigation);

        // Assign listener
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Add the fragments to the view
        fmMain.beginTransaction().add(R.id.main_container, foodListFragment, "5").hide(foodListFragment).commit();
        fmMain.beginTransaction().add(R.id.main_container, saveRecipeDataFragment, "4").hide(saveRecipeDataFragment).commit();
        fmMain.beginTransaction().add(R.id.main_container, itemDataFragment, "3").hide(itemDataFragment).commit();
        fmMain.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit(); // hide it so we only have 1 active
        fmMain.beginTransaction().add(R.id.main_container,fragment1, "1").commit();



        // Load the data if it was't loaded yet.
        if(true){ // testing purpose only BUGGY!!!!
            createData();
        }


        mEditor.putInt("dataloaded",1);
        mEditor.commit();
    }


    // Add data here.
    private void createData(){

        // It only works for 6 meals for now.
        if(MainActivity.numberOfMeals == 6) {

            Main_fragment.titles.add("Breakfast");

            Main_fragment.titles.add("Morning Snack");
            Main_fragment.titles.add("Lunch");
            Main_fragment.titles.add("Afternoon Snack");
            Main_fragment.titles.add("Dinner");
            Main_fragment.titles.add("Night Snack");

            Recipes_fragment.titles.add("Recipe 1 test");
            Recipes_fragment.titles.add("Recipe 2 test");
            Recipes_fragment.titles.add("Recipe 3 test");
            Recipes_fragment.titles.add("Recipe 4 test");
            Recipes_fragment.titles.add("Recipe 5 test");
            Recipes_fragment.titles.add("Recipe 6 test");

            Recipes_fragment.info.add("Recipe 1 info");
            Recipes_fragment.info.add("Recipe 2 info");
            Recipes_fragment.info.add("Recipe 3 info");
            Recipes_fragment.info.add("Recipe 4 info");
            Recipes_fragment.info.add("Recipe 5 info");
            Recipes_fragment.info.add("Recipe 6 info");

            Main_fragment.info.add("300 Kcal     2 Items");
            Main_fragment.info.add("250 Kcal     3 Items");
            Main_fragment.info.add("100 Kcal     2 Items");
            Main_fragment.info.add("500 Kcal     5 Items");
            Main_fragment.info.add("200 Kcal     3 Items");
            Main_fragment.info.add("600 Kcal     4 Items");
        }


    }


    // Add information specific to a fragment.
    private void addDataToList(){


        // Titles inside fragments.
        addTitleData(0,"Breakfast");
        addTitleData(1,"Morning Snack");
        addTitleData(2,"Lunch");
        addTitleData(3,"Afternoon Snack");
        addTitleData(4,"Dinner");
        addTitleData(5,"Night Snack");

        // Foods inside fragments;

        // Fragment 0;
        addFoodData(0,"Bread and Butter");
        addFoodData(0,"Black Tea");

        // Fragment 1;
        addFoodData(1,"Energy Baton");
        addFoodData(1,"Water");

        // Fragment 2;
        addFoodData(2,"Fried Chicken");
        addFoodData(2,"French Fries");
        addFoodData(2,"Fizzy Drink");


    }

    // Sets and adds foods for each fragment.
    private void addFoodData(int index, String foodName){
   //    menuFragments.get(index).titles.add(foodName);
    }

    private void addTitleData(int index, String foodName){
   //    menuFragments.get(index).titleOfFragment = foodName;
    }

    private void toolbarOptions(){

        // Create and set the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the title of the toolbar
        getSupportActionBar().setTitle("FOOD PLANNER");
        // Set the icon for the toolbar
      //  getSupportActionBar().setIcon(R.drawable.user_icon);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Need to inflate in order to add elements
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();

       // Find the settings action
        if (id == R.id.action_settings) {

            Intent profileIntent = new Intent(this, UserSettingsActivity.class);
            startActivity(profileIntent);
           // Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    
    // Create listener for the nav-bar
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    if(active == fragment2){ // if we are not on the home fragment
                        fmMain.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).hide(active).hide(foodListFragment)
                                .hide(itemDataFragment).hide(saveRecipeDataFragment).show(fragment1).commit();
                    }
                    active = fragment1;
                    return true;

                case R.id.navigation_dashboard:
                    if(active == fragment1){ // if we are not on the recipes fragment
                        fmMain.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).hide(active).hide(foodListFragment)
                                .hide(itemDataFragment).hide(saveRecipeDataFragment).show(fragment2).commit();


                    }
                    active = fragment2;
                    return true;

            }
            return false;
        }
    };


}
