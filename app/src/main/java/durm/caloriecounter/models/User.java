package durm.caloriecounter.models;

import durm.caloriecounter.enumerators.enumGender;
import durm.caloriecounter.enumerators.enumGoal;

public class User {
    // Get variables from user input screen
    private Integer weight;
    private Integer height;
    private Integer age;
    private Integer caloricIntake;
    private enumGender gender;
    private enumGoal goal;

    public User(Integer weight_, Integer height_, Integer age_, enumGender gender_, enumGoal goal_) {
        this.weight = weight_;
        this.height = height_;
        this.age = age_;
        this.gender = gender_;
        this.goal = goal_;
    }

    public enumGoal getGoal() {
        return goal;
    }

    public void setGoal(enumGoal goal) {
        this.goal = goal;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
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

    public Integer getCaloricIntake() {
        return caloricIntake;
    }

    public void setCaloricIntake(Integer caloricIntake) {
        this.caloricIntake = caloricIntake;
    }
}
