package durm.caloriecounter.activities.settingsactivities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import durm.caloriecounter.R;

public class UserAccountActivity extends AppCompatActivity {



    private TextView name;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button accSettings, updateDetails,preferences,feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_acitvity);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();


    }
}
