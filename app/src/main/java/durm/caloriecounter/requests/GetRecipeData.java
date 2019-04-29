package durm.caloriecounter.requests;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Random;
import durm.caloriecounter.models.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRecipeData extends AsyncTask<String, Integer, Recipe>{
    public interface AsyncResponse {
        void processFinish(Recipe output);
    }

    public final String id ="0126ac19";
    public final String key = "2e60d0a0911e3803abc6f73880340731";
    public AsyncResponse delegate = null;

    public GetRecipeData(AsyncResponse delegate) {
        this.delegate = delegate;
    }


    public Recipe getSingleRecipe(JSONArray json) throws JSONException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        JSONObject val = json.getJSONObject(0); // val is a single recipe

        for(int i = 0; i < json.length(); i++) {
            recipes.add(new Recipe(json.getJSONObject(i).getJSONObject("recipe")));
        }

        Random rnd = new Random();
        return recipes.get(rnd.nextInt(recipes.size()));

    }


    @Override
    protected Recipe doInBackground(String... options) {

        final OkHttpClient client = new OkHttpClient();
        String url = "https://api.edamam.com/search?app_id="+ id
                        +"&app_key=" + key
                        + "&calories=" + (Integer.valueOf(options[0]) - 50) +  "-" +  (Integer.valueOf(options[0]) + 50)
                        + "&q="+ options[1];
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

            return getSingleRecipe(hits);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Recipe recipe) {
        super.onPostExecute(recipe);
        delegate.processFinish(recipe);
    }
}
