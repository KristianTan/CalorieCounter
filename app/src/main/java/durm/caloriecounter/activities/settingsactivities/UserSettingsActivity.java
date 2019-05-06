package durm.caloriecounter.activities.settingsactivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mPreferences.getInt("easterEgg",0) == 0){


                    Toast.makeText(c,easterEggCounter + " CLICKS!",Toast.LENGTH_SHORT).show();

                    if(easterEggCounter >4){

                        final MediaPlayer easterEggSound = MediaPlayer.create(getApplicationContext(),R.raw.sound);

                        if(mPreferences.getInt("gender",0) == 0)
                            avatar.setImageResource(R.drawable.ic_male_eyes);
                        else
                            avatar.setImageResource(R.drawable.ic_female_eyes);

                        easterEggSound.start();
                        mEditor.putInt("easterEgg",1);
                        mEditor.commit();
                    }

                    easterEggCounter++;
                }
            }
        });

    }

    private int easterEggCounter = 1;

    @Override
    public void onResume() {
        name.setText(mPreferences.getString("username", ""));
        if(mPreferences.getInt("gender",0) == 0){

            if(mPreferences.getInt("easterEgg",0) == 0)
            avatar.setImageResource(R.drawable.ic_avatar_male);
            else
                avatar.setImageResource(R.drawable.ic_male_eyes);
        }

        else{
            if(mPreferences.getInt("easterEgg",0) == 0)
            avatar.setImageResource(R.drawable.ic_female_2);
            else avatar.setImageResource(R.drawable.ic_female_eyes);
        }


        super.onResume();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_none, R.anim.slide_down);
    }
}
