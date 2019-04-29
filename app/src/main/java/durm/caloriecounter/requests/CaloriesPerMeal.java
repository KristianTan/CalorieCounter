package durm.caloriecounter.requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CaloriesPerMeal {
    // Return something other than arraylist of calories?
    public LinkedHashMap<String, Integer> caloriesPerMeal(Integer totalCalories) {
        int sum = 0;


        LinkedHashMap<String, Integer> caloriesPerMeal = new LinkedHashMap<>();
        caloriesPerMeal.put("Breakfast", totalCalories / 100 * 20);
        sum += caloriesPerMeal.get("Breakfast") ;

        caloriesPerMeal.put("Lunch", totalCalories / 100 * 40);
        sum += caloriesPerMeal.get("Lunch") * 2; // Add for dinner as well

        int remainder = totalCalories - sum;
        if(remainder > 0) {
            caloriesPerMeal.put("Snack", ((remainder + 99) /100) * 100); // Rounded to the nearest 100 to avoid items with too few calories
        }

        caloriesPerMeal.put("Dinner", totalCalories / 100 * 40);


        return caloriesPerMeal;
    }
}
