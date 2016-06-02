package com.hend.instamonitor;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hend on 6/1/16.
 */
public class Monitor implements FragmentLifecycleCallbacks {

    List<String> ignoredViews;
    static String TAG = "MonitorApp";

    Application application;
    public static String PREF = "InstaMonitor";
    public static String APPLICATION_KEY = "App_Key";
    private static Monitor monitor = new Monitor();

    public static Monitor getInstance() {
        return monitor;
    }


    public void init(Application application) {
        this.application = application;
        ignoredViews = new ArrayList<>();
        registerActivityLifeCycleCallback();

    }

    long timeStartFragment, timeEndFragment;

    public void registerActivityLifeCycleCallback() {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            long timeStartActivity, timeEndActivity;

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                //TODO: before ignoring this I should calculate its time and add it to the application time
//                if (!ignoredViews.contains(activity.getClass().getSimpleName())) {
//                timeStartActivity = System.currentTimeMillis();
//                Log.d(TAG, activity.getClass().getSimpleName() + " " + Long.toString(timeStartActivity));
//                Log.d(TAG, activity.getClass().getSimpleName());
//                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
                timeStartActivity = System.currentTimeMillis();
                Log.d(TAG, activity.getClass().getSimpleName() + " " + Long.toString(timeStartActivity));
            }

            @Override
            public void onActivityPaused(Activity activity) {
                timeEndActivity = System.currentTimeMillis();
                long time = openedTime(timeStartActivity, timeEndActivity);
                Log.d(TAG, activity.getClass().getSimpleName() + " " + Long.toString(time));
                setTimePref(application, APPLICATION_KEY, time);
                if (!ignoredViews.contains(activity.getClass().getSimpleName())) {
                    setTimePref(application, activity.getClass().getSimpleName(), time);
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {
                //TODO save in preference without converting to Seconds
//                timeEndActivity = System.currentTimeMillis();
//                long time = openedTime(timeStartActivity, timeEndActivity);
//                Log.d(TAG, activity.getClass().getSimpleName() + " " + Long.toString(time));
//                setTimePref(application, APPLICATION_KEY, time);
//                if (!ignoredViews.contains(activity.getClass().getSimpleName())) {
//                    setTimePref(application, activity.getClass().getSimpleName(), time);
//                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }



    public void ignoreMonitor( Context context,String classSimpleName) {
        String key = classSimpleName;
        ignoredViews.add(key);
        clearTimePrefForView(context, key);
    }

    public void cancelIgnoreMonitor(String classSimpleName) {

        if (ignoredViews.contains(classSimpleName)) {
            ignoredViews.remove(classSimpleName);
        }
    }


    @Override
    public void onFragmentStarted(Fragment fragment) {
        timeStartFragment = System.currentTimeMillis();
        Log.d(TAG, fragment.getClass().getSimpleName() + " " + Long.toString(timeStartFragment));


    }

    @Override
    public void onFragmentStopped(Fragment fragment) {
        //TODO save in preference without converting to Seconds
        timeEndFragment = System.currentTimeMillis();
        long time = openedTime(timeStartFragment, timeEndFragment);
        setTimePref(application, fragment.getClass().getSimpleName(), time);
        Log.d(TAG, fragment.getClass().getSimpleName() + " " + Long.toString(time));
    }

    long openedTime(long start, long end) {
        long totalTime = (end - start) ;
        return totalTime;
    }

    void setTimePref(Context context, String key, long openedTime) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        long previousTime = sharedPref.getLong(key, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, previousTime + openedTime);
        editor.commit();

    }

    public long getTimeInMillis(Context context,  String key) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        long time = sharedPref.getLong(key, 0);
        return time;
    }

    public long getTimeInSeconds(Context context, String key) {

        long timeInMillis = getTimeInMillis(context, key);
        long timeInSeconds = timeInMillis / 1000;
        return timeInSeconds;
    }

    void clearTimePrefForView(Context context, String key) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, 0);
        editor.commit();

    }

    public void clearTimePref(Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();

    }

    public boolean ignoreContainsActivity(String activitySimpleName)
    {
        if (ignoredViews.contains(activitySimpleName))
        {
            return true;
        }
        else {
            return false;
        }
    }

}
