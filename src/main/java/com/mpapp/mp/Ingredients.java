package com.mpapp.mp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredients {

    @Id
    private String name;
    private int amount;
    private String amtmsmt;
    @GeneratedValue
    @GenericGenerator(name="increment", strategy = "increment")
    private int id;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

    public String getAmtmsmt() { return amtmsmt; }

    public void setAmtmsmt(String amtmsmt) { this.amtmsmt = amtmsmt; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
