package durm.caloriecounter;

import durm.caloriecounter.enumerators.enumGender;
import durm.caloriecounter.enumerators.enumGoal;

public class User {
    // Get variables from user input screen
    private double weight;
    private double height;
    private int age;
    private enumGender gender;
    private enumGoal goal;

    public enumGoal getGoal() {
        return goal;
    }

    public void setGoal(enumGoal goal) {
        this.goal = goal;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public enumGender getGender() {
        return gender;
    }

    public void setGender(enumGender gender) {
        this.gender = gender;
    }
}
