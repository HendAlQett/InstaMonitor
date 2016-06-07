package com.hend.monitoractivities;

import android.app.Application;

import com.hend.instamonitor.Monitor;



public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Monitor.getInstance().init(this);
    }

}
