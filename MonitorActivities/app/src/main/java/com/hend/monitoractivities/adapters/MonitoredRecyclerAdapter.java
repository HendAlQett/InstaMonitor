package com.hend.monitoractivities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hend.monitoractivities.R;

 public class MonitoredRecyclerAdapter extends RecyclerView.Adapter<MonitoredRecyclerAdapter.ViewHolder> {

     @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         return null;
     }

     @Override
     public void onBindViewHolder(ViewHolder holder, int position) {

     }

     @Override
     public int getItemCount() {
         return 0;
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
