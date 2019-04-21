package durm.caloriecounter.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import durm.caloriecounter.R;

public class UserProfileActivity extends AppCompatActivity {

    // This activity is launched from the hamburger button.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_acitvity);
    }
}
