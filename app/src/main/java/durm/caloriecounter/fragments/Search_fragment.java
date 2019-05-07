package durm.caloriecounter.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.models.Recipe;
import durm.caloriecounter.models.RecipeListSingleton;
import durm.caloriecounter.requests.GetSearchResults;
import durm.caloriecounter.viewAdapters.SearchResultsViewAdapter;

/*
 *
 * This class represents the fragment that has the ingredients and the how to make section for a food.
 *
 */

public class Search_fragment extends Fragment {

    final public static ArrayList<String> titles = new ArrayList<>();
    final public static ArrayList<String> info = new ArrayList<>();
    private SearchView searchView;
    private static SearchResultsViewAdapter adapter;
    private ProgressBar progressBar;



    public Search_fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Create and search for UI  elements here.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_fragment, container, false);



        titles.clear();
        info.clear();
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerViewSearchResults);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new SearchResultsViewAdapter(view.getContext(), titles, info);



        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);

        searchView = view.findViewById(R.id.searchBar);
        progressBar = view.findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        searchView.setSubmitButtonEnabled(true);
        searchView.setFocusable(true);
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // Clear any existing data and notify the adapter
                titles.clear();
                info.clear();
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.VISIBLE);

                AsyncTask<String, Integer, ArrayList<Recipe>> getSearchResults = new GetSearchResults(view.getContext(), new GetSearchResults.AsyncResponse() {
                    @Override
                    public void processFinish(ArrayList<Recipe> output) {
                        // Display list of recipes
                        titles.clear();
                        info.clear();
                        progressBar.setVisibility(View.GONE);
                        RecipeListSingleton.getInstance().searchResultsList.clear();

                        if (output != null) {
                            if (output.size() != 0) {
                                for (Recipe r : output) {
                                    titles.add(r.getLabel());
                                    info.add(r.getCalories() / r.getServings() + " cal");
                                    adapter.notifyDataSetChanged();
                                    RecipeListSingleton.getInstance().searchResultsList.add(r);
                                }
                            } else {
                                Toast savedToast = Toast.makeText(view.getContext(), "No results found for: '" + query + "'", Toast.LENGTH_SHORT);
                                savedToast.show();
                            }
                        } else {
                            Toast savedToast = Toast.makeText(view.getContext(), "API overloaded.  Please try again in a minute", Toast.LENGTH_SHORT);
                            savedToast.show();
                        }
                    }
                }).execute(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { return false; }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                titles.clear();
                info.clear();
                adapter.notifyDataSetChanged();
                RecipeListSingleton.getInstance().searchResultsList.clear();
                searchView.setQuery("", false);

                return false;
            }
        });

        return view;
    }

}

