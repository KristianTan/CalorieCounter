package durm.caloriecounter;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // Create the fragments
    final Fragment fragment1 = new Main_fragment();
    final Fragment fragment2 = new Recipes_fragment();

    // Create a fragment manager
    final FragmentManager fm = getSupportFragmentManager();

    // Assign an active fragment
    Fragment active = fragment1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create navbar
        BottomNavigationView navigation = findViewById(R.id.navigation);

        // Assign listener
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Add the fragments to the view
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit(); // hide it so we only have 1 active
        fm.beginTransaction().add(R.id.main_container,fragment1, "1").commit();
    }





    
    // create listener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(active == fragment2){
                        fm.beginTransaction().setCustomAnimations(R.anim.enter_from_left,R.anim.exit_to_right).hide(active).show(fragment1).commit();
                    }
                    active = fragment1;
                    return true;

                case R.id.navigation_dashboard:
                    if(active == fragment1){
                        fm.beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left).hide(active).show(fragment2).commit();
                    }
                    active = fragment2;
                    return true;

            }
            return false;
        }
    };



}
