# InstaMonitor
Monitor application, activties, fragments open time. 

##How to use InstaMonitor

###Application  
```
public class MyApplication extends Application {
    @Override
    public void onCreate() {
       super.onCreate();
       Monitor.getInstance().init(this);
    }
}
```

###Activity
No required implementation for activity.

###Fragment
```
 @Override
    public void onStart() {
        super.onStart();
        Monitor.getInstance().onFragmentStarted(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Monitor.getInstance().onFragmentStopped(this);
    }
```



