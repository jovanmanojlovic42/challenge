package com.company.model;

public class StrongestSuit {
    private ActivityType type;
    private Double averageSeconds;

    public StrongestSuit(ActivityType type, Double averageSeconds) {
        this.type = type;
        this.averageSeconds = averageSeconds;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public Double getAverageSeconds() {
        return averageSeconds;
    }

    public void setAverageSeconds(Double averageSeconds) {
        this.averageSeconds = averageSeconds;
    }

    @Override
    public String toString() {
        return "StrongestSuit{" +
                "type=" + type +
                ", averageSeconds=" + averageSeconds +
                '}';
    }
}
