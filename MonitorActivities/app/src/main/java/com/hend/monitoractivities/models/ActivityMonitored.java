package com.hend.monitoractivities.models;


import android.os.Parcel;
import android.os.Parcelable;

public class ActivityMonitored implements Parcelable{
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


    protected ActivityMonitored(Parcel in) {
        isMonitored = in.readByte() != 0;
        activitySimpleName = in.readString();
    }

    public static final Creator<ActivityMonitored> CREATOR = new Creator<ActivityMonitored>() {
        @Override
        public ActivityMonitored createFromParcel(Parcel in) {
            return new ActivityMonitored(in);
        }

        @Override
        public ActivityMonitored[] newArray(int size) {
            return new ActivityMonitored[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isMonitored ? 1 : 0));
        dest.writeString(activitySimpleName);
    }
}
