package durm.caloriecounter;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateCaloricIntakeTest {

    @Test
    public void givenValidUser_thenReturncorrectBMR() {
        User user = new User();
        user.setAge(20);
        user.setGender(enumGender.MALE);
        user.setGoal(enumGoal.MAINTAINWEIGHT);
        user.setHeight(175);
        user.setWeight(83);

        CalculateCaloricIntake calculateCaloricIntake = new CalculateCaloricIntake();

        assertEquals(1829, calculateCaloricIntake.calculateCalories(user));
    }

}