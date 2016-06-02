package com.hend.monitoractivities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.hend.instamonitor.Monitor;
import com.hend.monitoractivities.adapters.ActivitiesRecyclerAdapter;
import com.hend.monitoractivities.models.ActivityMonitored;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    MyApplication mMyApplication;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    List<ActivityMonitored> activityMonitoredList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyApplication = (MyApplication) getApplication();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        activityMonitoredList= new ArrayList<>();

        try {
            ActivityInfo[] list = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES).activities;

            for(int i = 0;i< list.length;i++)
            {
//                System.out.println("List of running activities"+list[i].name);
                String activityLongName =  list[i].name;
                String activityName = activityLongName.replace(list[i].packageName+".","");
                ActivityMonitored activityMonitored = new ActivityMonitored(activityName);
                activityMonitoredList.add(activityMonitored);
//                System.out.println("List of running activities "+activityName);
                Log.d("Monitor",activityName);

            }
        }

        catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

//

        ActivitiesRecyclerAdapter adapter = new ActivitiesRecyclerAdapter(activityMonitoredList);
        adapter.setHasStableIds(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }


    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnView:
                Intent intent = new Intent(this, NextActivity.class);
                startActivity(intent);
                break;
            case R.id.btnClear:
                Monitor.getInstance().clearTimePref(getApplicationContext());

        }

    }
}
