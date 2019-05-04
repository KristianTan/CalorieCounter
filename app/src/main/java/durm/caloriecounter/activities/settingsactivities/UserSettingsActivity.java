package durm.caloriecounter.activities.settingsactivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import durm.caloriecounter.R;

public class UserSettingsActivity extends AppCompatActivity {

    // This activity is launched from the hamburger button.

    private TextView name;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button accSettings, updateDetails,preferences;
    private ImageView avatar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings_acitvity);
        final Context c = this;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        name = findViewById(R.id.settings_user);
        avatar = findViewById(R.id.avatar);


        // set the name of the user;
        name.setText("Hi, " + mPreferences.getString("username","User") + "!");

        Animation a = AnimationUtils.loadAnimation(this, R.anim.pop_in);
        avatar.startAnimation(a);


        accSettings = findViewById(R.id.buttonAccount);
        updateDetails = findViewById(R.id.buttonDetails);
        preferences = findViewById(R.id.buttonPreferences);





        accSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(c, UserAccountActivity.class);
                startActivity(profileIntent);
            }
        });

        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(c, UserChangeFoodActivity.class);
                startActivity(profileIntent);
            }
        });
        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(c, UserUpdateDetailsActivity.class);
                startActivity(profileIntent);
            }
        });

    }

    @Override
    public void onResume() {
        name.setText(mPreferences.getString("username", ""));
        if(mPreferences.getInt("gender",0) == 0)
            avatar.setImageResource(R.drawable.ic_avatar_male);
        else
            avatar.setImageResource(R.drawable.ic_avatar_female);

        super.onResume();
    }
}
