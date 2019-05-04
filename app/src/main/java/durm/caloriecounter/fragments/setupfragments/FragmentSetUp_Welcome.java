package durm.caloriecounter.fragments.setupfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.activities.SetUpActivity;
import durm.caloriecounter.models.RecipeListSingleton;


public class FragmentSetUp_Welcome extends Fragment {


    Button next;
    private TextView welcome,desc1,desc2;

    public FragmentSetUp_Welcome(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setup_welcome_fragment, container, false);

       final AppCompatActivity activity = (AppCompatActivity)view.getContext();

       RecipeListSingleton.getInstance().recipeList.clear();
       RecipeListSingleton.getInstance().savedRecipeList.clear();

       next = view.findViewById(R.id.setupbuttonNext);
       welcome = view.findViewById(R.id.welcome);
       desc1 = view.findViewById(R.id.description1);
       desc2 = view.findViewById(R.id.description2);

        Animation a = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_fall_down);
        welcome.startAnimation(a);
        desc1.startAnimation(a);
        desc2.startAnimation(a);
        next.startAnimation(a);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .show(SetUpActivity.userNameFragment).hide(SetUpActivity.welcomeFragment).addToBackStack(null).commit();

            }
        });


        return view;
    }



}
