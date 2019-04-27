package durm.caloriecounter.fragments.settingsfragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.SetUpActivity;
import durm.caloriecounter.activities.settingsactivities.UserAccountActivity;

public class AccountChangeName_Fragment extends Fragment {


    EditText name;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Button save;

    public AccountChangeName_Fragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_account_changename_fragment, container, false);

        final AppCompatActivity activity = (AppCompatActivity)view.getContext();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        mEditor = mPreferences.edit();

        save = view.findViewById(R.id.setupbuttonNext);
        name = view.findViewById(R.id.editUserName);

        name.setText(mPreferences.getString("username","User"));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(name.getText().toString().equals("")){
                    name.setText("User");
                }

                mEditor.putString("username",name.getText().toString());
                mEditor.commit();
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(UserAccountActivity.accountSettings).hide(UserAccountActivity.changeNameFragment).commit();

            }
        });



        return view;
    }




}
