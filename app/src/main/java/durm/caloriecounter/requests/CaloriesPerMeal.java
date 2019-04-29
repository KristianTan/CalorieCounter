package durm.caloriecounter.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CaloriesPerMeal {
    // Return something other than arraylist of calories?
    public Map<String, Integer> caloriesPerMeal(Integer totalCalories) {
        Map<String, Integer> caloriesPerMeal = new HashMap<>();
        caloriesPerMeal.put("Breakfast", totalCalories / 100 * 20);
        caloriesPerMeal.put("Lunch", totalCalories / 100 * 40);
        caloriesPerMeal.put("Dinner", totalCalories / 100 * 40);

        int sum = 0;
        for(String key : caloriesPerMeal.keySet()) { sum += caloriesPerMeal.get(key); }

        int remainder = totalCalories - sum;
        if(remainder > 0) {
            caloriesPerMeal.put("Snack", ((remainder + 99) /100) * 100); // Rounded to the nearest 100 to avoid items with too few calories
        }


        return caloriesPerMeal;
    }
}
