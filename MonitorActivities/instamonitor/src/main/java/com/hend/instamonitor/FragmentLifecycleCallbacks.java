package com.hend.instamonitor;

import android.support.v4.app.Fragment;

/**
 * Created by hend on 6/2/16.
 */
public interface FragmentLifecycleCallbacks  {

    void onFragmentStarted(Fragment fragment);
    void onFragmentStopped(Fragment fragment);
}
