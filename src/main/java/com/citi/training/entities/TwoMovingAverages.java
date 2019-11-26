package com.citi.training.entities;

import javax.persistence.*;

@Entity @Table(name="two_moving_averages")
public class TwoMovingAverages {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "long_time")
    private Integer longTime;
    @Column(name = "short_time")
    private Integer shortTime;
    @Column(name = "time_purchased")
    private String timePurchased;

    public TwoMovingAverages() {
    }

    public TwoMovingAverages(Integer id, Integer longTime, Integer shortTime, String timePurchased) {
        this.id = id;
        this.longTime = longTime;
        this.shortTime = shortTime;
        this.timePurchased = timePurchased;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLongTime() {
        return longTime;
    }

    public void setLongTime(Integer longTime) {
        this.longTime = longTime;
    }

    public Integer getShortTime() {
        return shortTime;
    }

    public void setShortTime(Integer shortTime) {
        this.shortTime = shortTime;
    }

    public String getTimePurchased() {
        return timePurchased;
    }

    public void setTimePurchased(String timePurchased) {
        this.timePurchased = timePurchased;
    }

    @Override
    public String toString() {
        return "TwoMovingAverages{" +
                "id=" + id +
                ", longTime=" + longTime +
                ", shortTime=" + shortTime +
                ", timePurchased='" + timePurchased + '\'' +
                '}';
    }
}
