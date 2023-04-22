package com.mpapp.mp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WEEKDAYJUNC")
public class WeekDayJunc {
    @Id
    @Column(name = "WEEK")
    private String weekID;
    @Column(name = "DAY")
    private String day;

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
}
