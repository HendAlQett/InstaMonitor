package com.hend.monitoractivities;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hend.instamonitor.Monitor;
import com.hend.monitoractivities.adapters.MonitoredRecyclerAdapter;
import com.hend.monitoractivities.models.ClassMonitored;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NextFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    View rootView;
    List<ClassMonitored> classMonitoredList;


    public NextFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    public static NextFragment newInstance(String param1, String param2) {
        NextFragment fragment = new NextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_next, container, false);
        classMonitoredList = new ArrayList<>();
        //Get App
        String appSimpleName = MyApplication.class.getSimpleName();
        long timeApp = Monitor.getInstance().getTimeInSeconds(getActivity().getApplicationContext(), Monitor.APPLICATION_KEY);
        ClassMonitored classMonitored = new ClassMonitored(appSimpleName, timeApp);
        classMonitoredList.add(classMonitored);


        addActivitiesToList();
        addFragmentsToList();

        MonitoredRecyclerAdapter adapter = new MonitoredRecyclerAdapter(classMonitoredList);
        adapter.setHasStableIds(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        ButterKnife.bind(this, rootView);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    private void addFragmentsToList() {
        ClassMonitored classMonitored;
        if (getFragments() != null && getFragments().size() > 0) {
            String fragmentSimpleName;
            for (int i = 0; i < getFragments().size(); i++) {
                fragmentSimpleName = getFragments().get(i).getClass().getSimpleName();
                long timeFragment = Monitor.getInstance().getTimeInSeconds(getActivity().getApplicationContext(), fragmentSimpleName);
                classMonitored = new ClassMonitored(fragmentSimpleName, timeFragment);
                classMonitoredList.add(classMonitored);
            }

        }
    }

    private void addActivitiesToList() {
        ClassMonitored classMonitored;
        try {
            ActivityInfo[] list = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), PackageManager.GET_ACTIVITIES).activities;

            for (int i = 0; i < list.length; i++) {
                String activityLongName = list[i].name;
                String activitySimpleName = activityLongName.replace(list[i].packageName + ".", "");
                if (!Monitor.getInstance().ignoreContainsActivity(activitySimpleName)) {
                    long timeActivity = Monitor.getInstance().getTimeInSeconds(getActivity().getApplicationContext(), activitySimpleName);
                    classMonitored = new ClassMonitored(activitySimpleName, timeActivity);
                    classMonitoredList.add(classMonitored);
                }

            }
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
    }


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

    public List<Fragment> getFragments() {
        List<Fragment> allFragments = getActivity().getSupportFragmentManager().getFragments();
        if (allFragments == null || allFragments.isEmpty()) {
            return Collections.emptyList();
        } else
            return allFragments;
    }


}
