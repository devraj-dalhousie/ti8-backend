package com.dal.diet.entity;

public class HealthDetails {
    String email;
    double age;
    double weight;
    double height;
    String gender;
    String doYouDrink;//yes,no, occasionally
    String doYouSmoke; // yes,no, occasionally
    boolean doYouWearWearables;// yes,no.
    String wearableDevice; //
    String healthGoals;

    public HealthDetails(String email, double age, double weight, double height, String gender, String doYouDrink, String doYouSmoke, boolean doYouWearWearableswearables, String wearableDevice, String healthGoals) {
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.doYouDrink = doYouDrink;
        this.doYouSmoke = doYouSmoke;
        this.doYouWearWearables = doYouWearWearableswearables;
        this.wearableDevice = wearableDevice;
        this.healthGoals = healthGoals;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDoYouDrink() {
        return doYouDrink;
    }

    public void setDoYouDrink(String doYouDrink) {
        this.doYouDrink = doYouDrink;
    }

    public String getDoYouSmoke() {
        return doYouSmoke;
    }

    public void setDoYouSmoke(String doYouSmoke) {
        this.doYouSmoke = doYouSmoke;
    }

    public boolean isDoYouWearWearables() {
        return this.doYouWearWearables;
    }

    public void setDoYouWearWearables(boolean doYouWearWearables) {
        this.doYouWearWearables = doYouWearWearables;
    }

    public String getWearableDevice() {
        return wearableDevice;
    }

    public void setWearableDevice(String wearableDevice) {
        this.wearableDevice = wearableDevice;
    }

    public String getHealthGoals() {
        return healthGoals;
    }

    public void setHealthGoals(String healthGoals) {
        this.healthGoals = healthGoals;
    }
}
