package com.mpapp.mp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DAYMENUMEAL")
public class DayMenuMeal {
    @Id
    @Column(name="DAY")
    private String day;
    @Id
    @Column(name="MENU")
    private String menu;
    @Id
    @Column(name="MEALID")
    private int mealID;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }
}
