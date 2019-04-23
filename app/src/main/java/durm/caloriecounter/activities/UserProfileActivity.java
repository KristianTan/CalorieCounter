package durm.caloriecounter.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import durm.caloriecounter.R;

public class UserProfileActivity extends AppCompatActivity {

    // This activity is launched from the hamburger button.

    private TextView name;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_acitvity);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        name = findViewById(R.id.settings_username);

        name.setText("Hi, " + mPreferences.getString("username","User") + "!");


    }
}
