package durm.caloriecounter.requests;

import durm.caloriecounter.enumerators.enumUnit;
import durm.caloriecounter.models.User;
import durm.caloriecounter.enumerators.enumGender;

public class CalculateCaloricIntake {
    public int calculateCalories(User user) {
        // Use the Mifflin-St Jeor equation to calculate caloric intake
        // Equation uses kg and cm so may need conversion logic

        if(user.getUnit() == enumUnit.IMPERIAL) {
            user.setHeight((int)Math.round(user.getHeight() * 2.54)); // Convert height to cm
            user.setWeight((int)Math.round(user.getWeight() * 0.453592)); // convert lbs to kg
         }

        int s = user.getGender() == enumGender.MALE ? 5 : -161; // s variable is a constant for this equation

        double BMR = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + s;

        // Alter intake based on activity level
        switch(user.getActivityLevel()) {
            case LOW:
                BMR = BMR * 1.3 - 1.375;
                break;

            case MEDIUM:
                BMR = BMR * 1.5 - 1.55;
                break;

            case HIGH:
                BMR = BMR * 1.7;
                break;
        }

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
