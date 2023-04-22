package com.mpapp.mp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WEEK")
public class Week {
    @Id
    @Column(name = "WEEK")
    private String weekID;
    @Column(name = "NOTES")
    private String notes;

    public String getWeekID() { return weekID; }
    public void setWeekID(String weekID) {
        this.weekID = weekID;
    }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
