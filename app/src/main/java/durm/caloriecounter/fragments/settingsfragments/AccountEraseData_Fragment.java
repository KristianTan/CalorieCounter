package durm.caloriecounter.fragments.settingsfragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.SetUpActivity;
import durm.caloriecounter.activities.settingsactivities.UserAccountActivity;

public class AccountEraseData_Fragment extends Fragment {


    Button no,yes;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public AccountEraseData_Fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_account_erasedata_fragment, container, false);
        final AppCompatActivity activity = (AppCompatActivity)view.getContext();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        mEditor = mPreferences.edit();

        no = view.findViewById(R.id.buttonAccountNo);
        yes = view.findViewById(R.id.buttonAccountYes);


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
             //           .show(UserAccountActivity.accountSettings).hide(UserAccountActivity.eraseDataFragment).commit();
                activity.getSupportFragmentManager().popBackStack();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.clear().apply();
                Intent profileIntent = new Intent(activity, SetUpActivity.class);
                profileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
                ActivityCompat.finishAffinity(activity);
            }
        });
        return view;
    }




}
