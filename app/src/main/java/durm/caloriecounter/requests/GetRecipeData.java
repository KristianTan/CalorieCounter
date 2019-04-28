package durm.caloriecounter.requests;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
gimport durm.caloriecounter.models.Recipe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRecipeData {
    public final String id ="0126ac19";
    public final String key = "2e60d0a0911e3803abc6f73880340731";
    public void api(int calories, String q) {
        String url = "https://api.edamam.com/search?app_id="+ id
                        +"&app_key=" + key
                        + "&calories=" + (calories - 50) +  "-" +  (calories + 50)
                        + "&q="+ q;
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
                        JSONObject val = hits.getJSONObject(0); // val is a single recipe
                        parseJson(hits);

                    } catch (Throwable t) {

                    }
                }
            }
        });
    }

    public void parseJson(JSONArray json) throws JSONException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        JSONObject val = json.getJSONObject(0); // val is a single recipe

        for(int i = 0; i < json.length(); i++) {
            recipes.add(new Recipe(json.getJSONObject(i).getJSONObject("recipe")));
        }

        Random rnd = new Random();
        Recipe returnRecipe = recipes.get(rnd.nextInt(recipes.size()));
    }
}
