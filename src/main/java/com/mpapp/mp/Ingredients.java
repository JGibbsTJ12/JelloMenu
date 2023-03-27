package com.mpapp.mp;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredients {


    @Column(name = "NAME")
    private String name;
    @Column(name = "AMOUNT")
    private int amount;
    @Column(name = "AMTMSMT")
    private String amtmsmt;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INGID")
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
