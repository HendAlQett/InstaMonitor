package com.hend.instamonitor;

import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by hend on 6/2/16.
 */
public class MonitorApplication extends Application {

    private ArrayList<FragmentLifecycleCallbacks> mFragmentLifecycleCallbacks =
            new ArrayList<>();
    public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks callback) {
        synchronized (mFragmentLifecycleCallbacks) {
            mFragmentLifecycleCallbacks.add(callback);
        }
    }
    public void unregisterFragmentLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        synchronized (mFragmentLifecycleCallbacks) {
            mFragmentLifecycleCallbacks.remove(callback);
        }
    }

    public interface FragmentLifecycleCallbacks {

        void onFragmentStarted(Fragment fragment);
        void onFragmentStopped(Fragment fragment);

    }
}
