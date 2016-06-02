package com.hend.monitoractivities.models;

/**
 * Created by hend on 6/2/16.
 */
public class ClassMonitored {
    String className;
    long timeOpened;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getTimeOpened() {
        return timeOpened;
    }

    public void setTimeOpened(long timeOpened) {
        this.timeOpened = timeOpened;
    }
}
