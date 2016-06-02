package com.hend.monitoractivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.hend.instamonitor.Monitor;

import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;


public class NextActivity extends AppCompatActivity {


    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);

//        textView = (TextView) findViewById(R.id.textView);

//        long time = Monitor.getInstance().getTimeInSeconds(getApplicationContext(), Monitor.APPLICATION_KEY);
//
//        textView.setText("Main: " + time);


    }

    public List<Fragment> getVisibleFragments() {
        List<Fragment> allFragments = getSupportFragmentManager().getFragments();
        if (allFragments == null || allFragments.isEmpty()) {
            return Collections.emptyList();
        }
        else
           return allFragments;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO check list no empty
        String fragmentSimpleName=null;
        if (getVisibleFragments().get(0)!=null) {
             fragmentSimpleName = getVisibleFragments().get(0).getClass().getSimpleName();
            Log.d("Monitor", "Simple Name: " + getVisibleFragments().get(0).getClass().getSimpleName());
        }
//        long time = Monitor.getInstance().getTimeInSeconds(getApplicationContext(),fragmentSimpleName);
        long time = Monitor.getInstance().getTimeInSeconds(getApplicationContext(), MainActivity.class.getSimpleName());
//        textView.setText("Main: " + time);
    }
}
