package durm.caloriecounter.activities.settingsactivities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import durm.caloriecounter.R;
import durm.caloriecounter.fragments.settingsfragments.AccountChangeName_Fragment;
import durm.caloriecounter.fragments.settingsfragments.AccountEraseData_Fragment;
import durm.caloriecounter.fragments.settingsfragments.AccountSetGoal_Fragment;
import durm.caloriecounter.fragments.settingsfragments.AccountSettings_Fragment;

public class UserAccountActivity extends AppCompatActivity {



    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private FragmentManager  fm = getSupportFragmentManager();
    public static Fragment accountSettings = new AccountSettings_Fragment();
    public static Fragment changeNameFragment = new AccountChangeName_Fragment();
    public static Fragment eraseDataFragment= new AccountEraseData_Fragment();
    public static Fragment changeGoalFragment = new AccountSetGoal_Fragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_acitvity);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();


        fm.beginTransaction().add(R.id.settings_container, accountSettings, "1").commit();
        fm.beginTransaction().add(R.id.settings_container, changeNameFragment, "2").hide(changeNameFragment).commit();
        fm.beginTransaction().add(R.id.settings_container, eraseDataFragment, "3").hide(eraseDataFragment).commit();
        fm.beginTransaction().add(R.id.settings_container, changeGoalFragment, "4").hide(changeGoalFragment).commit();


    }
}
