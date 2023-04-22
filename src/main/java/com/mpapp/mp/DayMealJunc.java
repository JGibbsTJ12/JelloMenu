package com.mpapp.mp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DAYMEALJUNC")
public class DayMealJunc {
    @Id
    @Column(name = "DAY")
    private String day;
    @Column(name = "MEAL")
    private String meal;
    @Column(name = "MEALID")
    private int mealId;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }
}
