package com.mpapp.mp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEALS")
public class Meals {
    @Id
    private String name;
    private int servings;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getServings() { return servings; }

    public void setServings(int servings) { this.servings = servings; }
}
