package com.company.model;

import java.time.temporal.ChronoUnit;

public class BestTaskDTO {
    public Long timeInSeconds;
    public ActivityType activityType;
    public String userForHelp;
    public StrongestSuit strongestSuit;

    public BestTaskDTO(Activity activity, String userForHelp, StrongestSuit strongestSuit) {
        this.timeInSeconds = ChronoUnit.SECONDS.between(activity.getStart(), activity.getStop());
        this.activityType = activity.getType();
        this.userForHelp = userForHelp;
        this.strongestSuit = strongestSuit;
    }

    @Override
    public String toString() {
        return "BestTaskDTO{" +
                "timeInSeconds=" + timeInSeconds +
                ", activityType=" + activityType +
                ", userForHelp='" + userForHelp + '\'' +
                ", strongestSuit=" + strongestSuit +
                '}';
    }
}
