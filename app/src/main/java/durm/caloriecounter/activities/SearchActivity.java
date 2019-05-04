package durm.caloriecounter.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;

import durm.caloriecounter.R;
import durm.caloriecounter.fragments.Recipe_Item_Data_Fragment;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.requests.GetRecipeData;
import durm.caloriecounter.requests.GetSearchResults;

public class SearchActivity extends AppCompatActivity {

    // This activity is launched from the fab button.
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchBar);
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do the search
                 AsyncTask<String, Integer, ArrayList<Recipe>> getSearchResults = new GetSearchResults(getApplicationContext(), new GetSearchResults.AsyncResponse() {
                     @Override
                     public void processFinish(ArrayList<Recipe> output) {

                     }
                 }).execute("", query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { return false; }
        });

    }

}
