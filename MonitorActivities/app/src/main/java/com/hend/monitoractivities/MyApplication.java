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


//    @Override
//    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//
//    }
//
//    @Override
//    public void onActivityStarted(Activity activity) {
//
//
//
//
//        Log.d("MyApplication",activity.getClass().getSimpleName());
//
//
//    }
//
//
//
//    @Override
//    public void onActivityResumed(Activity activity) {
//
//    }
//
//    @Override
//    public void onActivityPaused(Activity activity) {
//
//    }
//
//
//    @Override
//    public void onActivityStopped(Activity activity) {
//
//        Log.d("MyApplication","Activity Stopped");
//    }
//
//    @Override
//    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//    }
//
//    @Override
//    public void onActivityDestroyed(Activity activity) {
//
//    }
//


}
