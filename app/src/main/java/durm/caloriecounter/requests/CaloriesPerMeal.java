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

        int onePercent = totalCalories / 100;

        caloriesPerMeal.put("Breakfast", 20 * onePercent);


        caloriesPerMeal.put("Lunch",  (ThreadLocalRandom.current().nextInt(500, 700) / 10) * 10);
        caloriesPerMeal.put("Dinner", (ThreadLocalRandom.current().nextInt(500, 700) / 10) * 10);

        int sum = 0;
        for(String key : caloriesPerMeal.keySet()) { sum += caloriesPerMeal.get(key); }

        int remainder = totalCalories - sum;
        int count = 1;

        while(remainder > 150) {
            int minus = 10 * ThreadLocalRandom.current().nextInt(10, 31);
            caloriesPerMeal.put("Snack " + count, minus);
            remainder -= minus;
            count ++;
        }

        return caloriesPerMeal;
    }
}
