package com.mpapp.mp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEAL_ING_JUNC")
public class MealIngJunc {
    @Column(name="MEALID")
    private int mealid;
    @Id
    @Column(name="INGID")
    private int ingid;

    public int getMealid() { return mealid; }

    public void setMealid(int mealid) { this.mealid = mealid; }

    public int getIngid() { return ingid; }

    public void setIngid(int ingid) { this.ingid = ingid; }
}
