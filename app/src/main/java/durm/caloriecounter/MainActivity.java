package durm.caloriecounter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private  Toolbar toolbar;

    // Create the fragments
    static final Fragment fragment1 = new Main_fragment();
    static final Fragment fragment2 = new Recipes_fragment();

    // Experimental for now.
    static ArrayList<Food_menu_fragment_open> menuFragments = new ArrayList<>();
    static int numberOfMeals = 6;
    static int foodActiveFragment;

    // Create a fragment manager
    final FragmentManager fm = getSupportFragmentManager();

    // Assign an active fragment
    Fragment active = fragment1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Call the method responsible for creating the toolbar
        toolbarOptions();

        // Create nav-bar
        BottomNavigationView navigation = findViewById(R.id.navigation);

        // Assign listener
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Add the fragments to the view
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit(); // hide it so we only have 1 active
        fm.beginTransaction().add(R.id.main_container,fragment1, "1").commit();


        // Experimenting with stuff
        for(int i=0; i< numberOfMeals;i++){

            menuFragments.add(new Food_menu_fragment_open());
            fm.beginTransaction().add(R.id.main_container, menuFragments.get(i),2+i+"").hide(menuFragments.get(i)).commit();

        }



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

            Intent profileIntent = new Intent(this, UserProfileActivity.class);
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
                        fm.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).hide(active)
                                .hide(menuFragments.get(foodActiveFragment)).show(fragment1).commit();
                    }
                    active = fragment1;
                    return true;

                case R.id.navigation_dashboard:
                    if(active == fragment1){ // if we are not on the recipes fragment
                        fm.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).hide(active)
                                .hide(menuFragments.get(foodActiveFragment)).show(fragment2).commit();


                    }
                    active = fragment2;
                    return true;

            }
            return false;
        }
    };


}
