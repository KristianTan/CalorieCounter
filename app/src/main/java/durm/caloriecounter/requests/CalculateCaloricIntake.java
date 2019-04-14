package durm.caloriecounter.requests;

import durm.caloriecounter.User;
import durm.caloriecounter.enumerators.enumGender;

public class CalculateCaloricIntake {
    public int calculateCalories(User user) {
        // Use the Mifflin-St Jeor equation to calculate caloric intake
        // Equation uses kg and cm so may need conversion logic
        int s = user.getGender() == enumGender.MALE ? 5 : -161; // s variable is a constant for this equation

        double BMR = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + s;

        // Alter caloric intake based on user goal
        switch(user.getGoal()) {
            case LOSEWEIGHT:
                BMR -= 500;
                break;
            case GAINWEIGHT:
                BMR += 500;
                break;
        }

        return (int)Math.round(BMR);
    }

}
