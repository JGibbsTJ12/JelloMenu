package com.mpapp.mp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PLAN")
public class Plan {

    @Column(name = "WEEKID")
    private String weekID;
    @Column(name = "DAY")
    private String day;
    @Id
    @Column(name = "MEALID")
    private int mealID;
    @Column(name = "MEAL")
    private int meal;
    @Column(name = "NOTES")
    private String notes;

    @Column(name = "WEEKHASINFO")
    private int weekHasInfo;
    public String getWeekID() {
        return weekID;
    }

    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public int getMeal() {
        return meal;
    }

    public void setMeal(int meal) {
        this.meal = meal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getWeekHasInfo() { return weekHasInfo; }

    public void setWeekHasInfo(int weekHasInfo) { this.weekHasInfo = weekHasInfo; }
}
