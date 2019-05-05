package durm.caloriecounter.fragments.setupfragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.SetUpActivity;


public class FragmentSetUp_UserName extends Fragment {


    Button next;
    EditText name;
    TextView textTitle;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public FragmentSetUp_UserName(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setup_username_fragment, container, false);

       final AppCompatActivity activity = (AppCompatActivity)view.getContext();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        mEditor = mPreferences.edit();

        next = view.findViewById(R.id.setupbuttonNext);
        name = view.findViewById(R.id.editUserName);
        textTitle = view.findViewById(R.id.description2);
       // Animation a = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_fall_down);
        //textTitle.startAnimation(a);
       // name.startAnimation(a);
       // next.startAnimation(a);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(name.getText().toString().equals("")){
                    name.setText("User");
                }

                mEditor.putString("username",name.getText().toString());
                mEditor.commit();
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(SetUpActivity.userPreferencesFragment).hide(SetUpActivity.userNameFragment).addToBackStack(null).commit();

            }
        });


        return view;
    }



}
