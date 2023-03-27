package com.mpapp.mp;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MEALS")
public class Meals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEALID")
    private int mealid;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SERVINGS")
    private int servings;


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getServings() { return servings; }

    public void setServings(int servings) { this.servings = servings; }

    public int getMealid() { return mealid; }

    public void setMealid(int mealid) { this.mealid = mealid; }
}
