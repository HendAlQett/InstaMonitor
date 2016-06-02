package com.hend.monitoractivities.models;

/**
 * Created by hend on 6/2/16.
 */
public class ClassMonitored {
    String classSimpleName;
    long timeOpened;

    public ClassMonitored(String classSimpleName, long timeOpened) {
        this.classSimpleName = classSimpleName;
        this.timeOpened = timeOpened;
    }

    public String getClassSimpleName() {
        return classSimpleName;
    }

    public void setClassSimpleName(String classSimpleName) {
        this.classSimpleName = classSimpleName;
    }

    public long getTimeOpened() {
        return timeOpened;
    }

    public void setTimeOpened(long timeOpened) {
        this.timeOpened = timeOpened;
    }
}
