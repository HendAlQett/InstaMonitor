package com.hend.monitoractivities;

import android.app.Application;
import android.util.Log;

import com.hend.instamonitor.Monitor;



public class MyApplication extends Application {
//long activityTimeStart,fragmentTimeStart ;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplication","time");
//        Monitor monitor = new Monitor();
        Monitor.getInstance().init(this);
//        registerActivityLifecycleCallbacks(this);
//        registerFragmentLifecycleCallbacks(this);


    }

}
