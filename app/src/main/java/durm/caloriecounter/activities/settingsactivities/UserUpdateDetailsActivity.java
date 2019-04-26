package durm.caloriecounter.activities.settingsactivities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import durm.caloriecounter.R;

public class UserUpdateDetailsActivity extends AppCompatActivity {



    private TextView name;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText height,weight,age;
    private Button metric,imperial;
    private Button low,mid,high,save;
    private TextView heightUnits,weightUnits;
    private int SystemUsed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_details_acitvity);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        save = findViewById(R.id.setupbuttonNext);

        // set the edit text
        height = findViewById(R.id.setup_height);
        height.setInputType(
                InputType.TYPE_CLASS_NUMBER);
        weight = findViewById(R.id.setup_weight);
        weight.setInputType(
                InputType.TYPE_CLASS_NUMBER);
        age = findViewById(R.id.setup_age);
        age.setInputType(
                InputType.TYPE_CLASS_NUMBER);

        heightUnits = findViewById(R.id.heightUnits);
        weightUnits = findViewById(R.id.weightUnits);

        // set the buttons
        metric = findViewById(R.id.b_metric);
        imperial = findViewById(R.id.b_imperial);
        low = findViewById(R.id.b_lose_weight);
        mid = findViewById(R.id.b_maintain_weight);
        high = findViewById(R.id.b_gain_weight);


        metric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SystemUsed = 0; // metric default

                // set to active
                metric.setBackgroundColor(Color.parseColor("#2196F3"));

                // deactivate the other button
                imperial.setBackgroundColor(Color.parseColor("#002196F3"));

                heightUnits.setText("cm");
                weightUnits.setText("kg");

            }
        });

        imperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SystemUsed = 1; // imperial

                // set to active
                imperial.setBackgroundColor(Color.parseColor("#2196F3"));

                // deactivate the other button
                metric.setBackgroundColor(Color.parseColor("#002196F3"));
                heightUnits.setText("inch");
                weightUnits.setText("pounds");
            }
        });

        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("activity",0);
                // set to active
                low.setBackgroundColor(Color.parseColor("#2196F3"));
                // deactivate the other button
                mid.setBackgroundColor(Color.parseColor("#002196F3"));
                high.setBackgroundColor(Color.parseColor("#002196F3"));

            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("activity",1);
                // set to active
                mid.setBackgroundColor(Color.parseColor("#2196F3"));
                // deactivate the other button
                low.setBackgroundColor(Color.parseColor("#002196F3"));
                high.setBackgroundColor(Color.parseColor("#002196F3"));

            }
        });
        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEditor.putInt("activity",2);
                // set to active
                high.setBackgroundColor(Color.parseColor("#2196F3"));
                // deactivate the other button
                mid.setBackgroundColor(Color.parseColor("#002196F3"));
                low.setBackgroundColor(Color.parseColor("#002196F3"));

            }
        });





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // in case the user deletes the text
                if(height.getText().toString().equals("")){
                    height.setText("0");
                }
                if(weight.getText().toString().equals("")){
                    weight.setText("0");
                }
                if(age.getText().toString().equals("")){
                    age.setText("0");
                }

                int heightInt = Integer.parseInt(height.getText().toString());
                int weightInt = Integer.parseInt(weight.getText().toString());

                if(SystemUsed == 1){

                    // transform everything back to metric cm, kg
                    heightInt =(int) (heightInt *  2.54);
                    weightInt =(int)(weightInt / 2.2f);
                }



                mEditor.putInt("height",heightInt);
                mEditor.putInt("weight",weightInt);
                mEditor.putInt("age",Integer.parseInt(age.getText().toString()));
                mEditor.commit();


                finish();

            }
        });

    }
}