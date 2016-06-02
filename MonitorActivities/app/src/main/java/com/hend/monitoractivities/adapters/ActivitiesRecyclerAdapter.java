package com.hend.monitoractivities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hend.monitoractivities.R;
import com.hend.monitoractivities.models.ActivityMonitored;

import java.util.List;

/**
 * Created by hend on 6/2/16.
 */
public class ActivitiesRecyclerAdapter  extends RecyclerView.Adapter<ActivitiesRecyclerAdapter.ViewHolder>{

    private List<ActivityMonitored>  activityList;


    public ActivitiesRecyclerAdapter(List<ActivityMonitored> activityList) {
        this.activityList= activityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_list_item, parent, false);
        final ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final ActivityMonitored activityItem = activityList.get(position);
        holder.tvActivity.setText(activityItem.getActivitySimpleName());
        holder.cbMonitor.setChecked(activityItem.isMonitored());
        holder.cbMonitor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                activityItem.setMonitored(isChecked);
                activityList.add(position,activityItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvActivity;
        public CheckBox cbMonitor;

        public ViewHolder(View view) {
            super(view);

            tvActivity = (TextView) view.findViewById(R.id.tvActivity);
            cbMonitor = (CheckBox) view.findViewById(R.id.cbMonitor);
        }
    }
}
