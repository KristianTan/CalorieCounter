package durm.caloriecounter.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import durm.caloriecounter.R;
import durm.caloriecounter.fragments.Menu_Item_Data_Fragment;
import durm.caloriecounter.fragments.Search_fragment;

public class SearchActivity extends AppCompatActivity {

    // This activity is launched from the fab button.

    public static Menu_Item_Data_Fragment itemSearchFragment = new Menu_Item_Data_Fragment();
    public static Search_fragment searchFragment = new Search_fragment();
    private final FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        fm.beginTransaction().add(R.id.main_container, itemSearchFragment, "2").hide(itemSearchFragment).commit();
        fm.beginTransaction().add(R.id.main_container, searchFragment, "1").commit();

    }

}
