package durm.caloriecounter.requests;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import durm.caloriecounter.models.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetSearchResults extends AsyncTask<String, Integer, ArrayList<Recipe>> {
    public interface AsyncResponse {
        void processFinish(ArrayList<Recipe> output);
    }

    private SharedPreferences mPreferences;
    public final String id = "0126ac19";
    public final String key = "2e60d0a0911e3803abc6f73880340731";
    public AsyncResponse delegate = null;

    public GetSearchResults(Context c, AsyncResponse delegate) {
        this.delegate = delegate;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(c);
    }

    @Override
    protected ArrayList<Recipe> doInBackground(String... options) {
        final OkHttpClient client = new OkHttpClient();


        String url = "https://api.edamam.com/search?app_id=" + id
                + "&app_key=" + key
                + "&q=" + options[0];

        final Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                return null;
            }

            JSONObject obj = new JSONObject(response.body().string());
            JSONArray hits = obj.getJSONArray("hits");

            ArrayList<Recipe> returnList = new ArrayList<>();
            if (hits != null) {

                for (int i = 0; i < hits.length(); i++) {
                    returnList.add(new Recipe(hits.getJSONObject(i).getJSONObject("recipe")));
                }
                return returnList;

            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> recipes) {
        super.onPostExecute(recipes);
        delegate.processFinish(recipes);
    }
}
