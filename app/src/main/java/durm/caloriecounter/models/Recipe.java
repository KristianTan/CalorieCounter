package durm.caloriecounter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Recipe {
    private ArrayList<String> ingredients;
    private String recipeURL;
    private String label;
    private int calories;
    private int servings;

    public Recipe(JSONObject json) throws JSONException {

        this.recipeURL = json.getString("url");
        this.label = json.getString("label");
        this.calories = json.getInt("calories");
        this.servings = json.getInt("yield");

        JSONArray ingredients = json.getJSONArray("ingredientLines");
        ArrayList<String> ingredientsList = new ArrayList<>();
        for (int i = 0; i < ingredients.length(); i++) {
            ingredientsList.add(ingredients.getString(i));
        }

        this.ingredients = ingredientsList;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipeURL() {
        return recipeURL;
    }

    public void setRecipeURL(String recipeURL) {
        this.recipeURL = recipeURL;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getServings() {return servings; }

    public void setServings(int servings){this.servings = servings; }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
