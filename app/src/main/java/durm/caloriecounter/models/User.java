package durm.caloriecounter.models;

import durm.caloriecounter.enumerators.enumGender;
import durm.caloriecounter.enumerators.enumGoal;
import durm.caloriecounter.enumerators.enumUnit;

public class User {
    // Get variables from user input screen
    private Integer weight;
    private Integer height;
    private Integer age;
    private Integer caloricIntake;
    private enumGender gender;
    private enumGoal goal;



    private enumUnit unit;

    public User(Integer weight_, Integer height_, Integer age_, enumGender gender_, enumGoal goal_, enumUnit unit_) {
        this.weight = weight_;
        this.height = height_;
        this.age = age_;
        this.gender = gender_;
        this.goal = goal_;
        this.unit = unit_;
    }

    public User() {};

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

    public enumUnit getUnit() {
        return unit;
    }

    public void setUnit(enumUnit unit) {
        this.unit = unit;
    }
}
