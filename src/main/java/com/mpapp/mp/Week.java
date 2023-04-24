package com.mpapp.mp;

import jakarta.persistence.*;

@Entity
@Table(name = "WEEK")
public class Week {
    @Column(name = "WEEK")
    private String weekID;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "DAY")
    private String day;
    @Column(name="MENU")
    private String menu;
    @Column(name="MEALID")
    private int mealID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DAYID")
    private int dayID;

    public String getWeekID() { return weekID; }
    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

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
