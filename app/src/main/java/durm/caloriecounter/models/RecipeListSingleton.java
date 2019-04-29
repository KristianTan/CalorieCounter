package durm.caloriecounter.models;

import java.util.ArrayList;

public class RecipeListSingleton {
    public ArrayList<Recipe> recipeList = new ArrayList<>();
    private static RecipeListSingleton INSTANCE;

    public static RecipeListSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RecipeListSingleton();
        }

        return INSTANCE;
    }

}
