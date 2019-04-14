package durm.caloriecounter;

public class CalculateCaloricIntake {
    // Get variables from user input screen
    private double weight;
    private double height;
    private int age;
    private enumGender gender;

    // Convert to kg and cm

    public double calculateCalories() {
        // Use the Mifflin-St Jeor equation to calculate caloric intake

        int s = this.gender == enumGender.MALE ? 5 : -161; // s variable is a constant for this equation

        return 10 * this.weight + 6.25 * this.height - 5 * this.age + s;
    }

}
