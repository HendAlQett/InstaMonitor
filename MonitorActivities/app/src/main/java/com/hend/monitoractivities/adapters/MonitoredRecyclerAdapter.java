package com.hend.monitoractivities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hend.monitoractivities.R;
import com.hend.monitoractivities.models.ClassMonitored;

import java.util.List;

public class MonitoredRecyclerAdapter extends RecyclerView.Adapter<MonitoredRecyclerAdapter.ViewHolder> {

    List<ClassMonitored> classMonitoredList;
    public MonitoredRecyclerAdapter(List<ClassMonitored> classMonitoredList) {
        this.classMonitoredList= classMonitoredList;
    }

    @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.monitored_list_item, parent, false);
        final ViewHolder vh = new ViewHolder(view);

        return vh;
     }

     @Override
     public void onBindViewHolder(ViewHolder holder, int position) {
         final ClassMonitored classMonitoredItem = classMonitoredList.get(position);
         holder.tvView.setText(classMonitoredItem.getClassSimpleName());
         holder.tvTime.setText(Long.toString(classMonitoredItem.getTimeOpened()));
     }

     @Override
     public int getItemCount() {
         return classMonitoredList.size();
     }

     public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvView;
        public TextView tvTime;

        public ViewHolder(View view) {
            super(view);

            tvView = (TextView) view.findViewById(R.id.tvActivity);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
        }
    }
}
