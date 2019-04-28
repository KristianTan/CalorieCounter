package durm.caloriecounter.requests;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import durm.caloriecounter.activities.MainActivity;
import durm.caloriecounter.models.Recipe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRecipeData {

    public void api() {
        String url = "https://api.edamam.com/search?app_id=0126ac19&app_key=2e60d0a0911e3803abc6f73880340731&calories=100-500&q=bacon";
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        // Make an Asynchronous call to the API
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    try {
                        JSONObject obj = new JSONObject(myResponse);
                        JSONArray hits = obj.getJSONArray("hits");
                        JSONObject val = hits.getJSONObject(0);
                        JSONObject recipe = val.getJSONObject("recipe");
                        parseJson(val);

                    } catch (Throwable t) {

                    }
                }
            }
        });
    }

    public void parseJson(JSONObject json) throws JSONException {

        Iterator<String> keys = json.keys();
        ArrayList<Recipe> recipes = new ArrayList<>();
        while (keys.hasNext()) {
            String key = keys.next();

            recipes.add(new Recipe(json.getJSONObject("recipe")));
        }

        // Select meals for the day
        // Set fragment data or save arraylist of recipes for the day somewhere
    }
}
