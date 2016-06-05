package com.hend.monitoractivities.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hend.instamonitor.Monitor;
import com.hend.monitoractivities.MainActivity;
import com.hend.monitoractivities.R;
import com.hend.monitoractivities.models.ActivityMonitored;

import java.util.List;

/**
 * Created by hend on 6/2/16.
 */
public class ActivitiesRecyclerAdapter extends RecyclerView.Adapter<ActivitiesRecyclerAdapter.ViewHolder> {

    private List<ActivityMonitored> activityList;

    Context context;

    public ActivitiesRecyclerAdapter(Context context, List<ActivityMonitored> activityList) {
        this.context = context;
        this.activityList = activityList;
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
                activityList.set(position, activityItem);
                if (!isChecked) {
                    Monitor.getInstance().ignoreMonitor(context,activityItem.getActivitySimpleName());

                }
                else if (isChecked) {
                    Monitor.getInstance().cancelIgnoreMonitor(activityItem.getActivitySimpleName());
                }

                if (context instanceof MainActivity)
                {
                    MainActivity.updateData(position,isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView tvActivity;
        public CheckBox cbMonitor;


        public ViewHolder(View view) {
            super(view);

            tvActivity = (TextView) view.findViewById(R.id.tvActivity);
            cbMonitor = (CheckBox) view.findViewById(R.id.cbMonitor);


        }


    }
}
