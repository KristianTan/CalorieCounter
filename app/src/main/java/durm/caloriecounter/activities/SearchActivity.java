package durm.caloriecounter.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import durm.caloriecounter.R;
import durm.caloriecounter.fragments.Recipe_Item_Data_Fragment;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.models.RecipeListSingleton;
import durm.caloriecounter.requests.GetRecipeData;
import durm.caloriecounter.requests.GetSearchResults;
import durm.caloriecounter.viewAdapters.SearchResultsViewAdapter;
import durm.caloriecounter.viewAdapters.ViewAdapter;

public class SearchActivity extends AppCompatActivity {
    final public static ArrayList<String> titles = new ArrayList<>();
    final public static ArrayList<String> info = new ArrayList<>();
    // This activity is launched from the fab button.
    private SearchView searchView;
    private static SearchResultsViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        titles.clear();
        info.clear();
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewSearchResults);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new SearchResultsViewAdapter(this, titles, info);

        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);

        searchView = findViewById(R.id.searchBar);
        searchView.setSubmitButtonEnabled(true);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Do the search
                 AsyncTask<String, Integer, ArrayList<Recipe>> getSearchResults = new GetSearchResults(getApplicationContext(), new GetSearchResults.AsyncResponse() {
                     @Override
                     public void processFinish(ArrayList<Recipe> output) {
                        // Display list of recipes
                         titles.clear();
                         info.clear();
                         RecipeListSingleton.getInstance().searchResultsList.clear();

                         if (output.size() != 0) {
                             for (Recipe r : output) {
                                 titles.add(r.getLabel());
                                 info.add(r.getCalories() + " cal");
                                 adapter.notifyDataSetChanged();
                                 RecipeListSingleton.getInstance().searchResultsList.add(r);
                             }
                         } else {
                             Toast savedToast = Toast.makeText(getApplicationContext(), "No results found for: '" + query + "'", Toast.LENGTH_SHORT);
                             savedToast.show();
                         }
                     }
                 }).execute(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { return false; }
        });

    }

}
