package com.hend.monitoractivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hend.instamonitor.Monitor;




public class NextFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next, container, false);
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

}
