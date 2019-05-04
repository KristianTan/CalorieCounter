package durm.caloriecounter.requests;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import durm.caloriecounter.enumerators.enumFoodType;
import durm.caloriecounter.models.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetSearchResults extends AsyncTask<String, Integer, ArrayList<Recipe>> {
    public interface AsyncResponse {
        void processFinish(ArrayList<Recipe> output);
    }

    private SharedPreferences mPreferences;
    public final String id ="0126ac19";
    public final String key = "2e60d0a0911e3803abc6f73880340731";
    public AsyncResponse delegate = null;

    public GetSearchResults(Context c, AsyncResponse delegate) {
        this.delegate = delegate;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(c);
    }


    public Recipe getSingleRecipe(JSONObject json) throws JSONException {
//        ArrayList<Recipe> recipes = new ArrayList<>();
//        recipes.add(new Recipe(json.getJSONObject("recipe")));

        return new Recipe(json.getJSONObject("recipe"));
    }


    @Override
    protected ArrayList<Recipe> doInBackground(String... options) {
        final OkHttpClient client = new OkHttpClient();
        String url = "";
        if(!options[0].isEmpty()) {
            url = "https://api.edamam.com/search?app_id=" + id
                    + "&app_key=" + key
                    + "&calories=" + (Integer.valueOf(options[0]) - 50) + "-" + (Integer.valueOf(options[0]) + 50)
                    + "&q=" + options[1];
        } else {
            url = "https://api.edamam.com/search?app_id=" + id
                    + "&app_key=" + key
                    + "&q=" + options[1];
        }

        enumFoodType foodType = enumFoodType.values()[mPreferences.getInt("foodValue", 0)];

        if(foodType == enumFoodType.Vegan || foodType == enumFoodType.Vegetarian) {
            url = url + "&health=" + foodType.toString().toLowerCase();
        }

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
            int j = hits.length();
            ArrayList<Recipe> returnList = new ArrayList<>();
            if(hits != null) {
                Gson gson = new Gson();
                for (int i = 0; i < hits.length(); i++){
                    returnList.add(new Recipe(hits.getJSONObject(i).getJSONObject("recipe")));
                }
                return returnList;

            } else {
                return null;
            }
//            return returnList;

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
