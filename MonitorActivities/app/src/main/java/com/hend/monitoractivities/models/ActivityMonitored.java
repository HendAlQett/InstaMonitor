package com.hend.monitoractivities.models;


public class ActivityMonitored {
    boolean isMonitored =true;
    String activitySimpleName;

    public ActivityMonitored(String activitySimpleName) {
        this.activitySimpleName = activitySimpleName;
    }

    public boolean isMonitored() {
        return isMonitored;
    }

    public String getActivitySimpleName() {
        return activitySimpleName;
    }

    public void setMonitored(boolean monitored) {
        isMonitored = monitored;
    }

    public void setActivitySimpleName(String activitySimpleName) {
        this.activitySimpleName = activitySimpleName;
    }
}
