package com.kalash.m3.Main.RecycleView;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalash.m3.Main.MainActivity;
import com.kalash.m3.R;
import com.kalash.m3.Util.KeyValue;
import com.kalash.m3.Util.OnRecycleViewClick;

import java.util.ArrayList;

public class RecycleViewHourAdapter extends RecyclerView.Adapter<RecycleViewHourAdapter.ViewHolder> {

    private Activity activity;
    private Context context;
    private KeyValue keyValue;
    private ArrayList<ArrayList<String>> data = new ArrayList();
    private ArrayList<String> hour = new ArrayList<>();

    public RecycleViewHourAdapter(Activity activity, Context context, ArrayList<ArrayList<String>> data, KeyValue keyValue){
        this.activity = activity;
        this.context = context;
        this.data = data;
        this.keyValue = keyValue;

        for(int i = 0; i != data.size(); i++){
            hour.add(i, data.get(i).get(8));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_hour, parent, false);
        RecycleViewHourAdapter.ViewHolder holder = new RecycleViewHourAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.hour.setText(hour.get(position));


        holder.listitem_hour_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    OnRecycleViewClick onRecycleViewClick = (OnRecycleViewClick)activity;
                    onRecycleViewClick.onRecycleViewClickListener(keyValue.getHourAdapter(),data.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hour;
        ConstraintLayout listitem_hour_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.hour);
            listitem_hour_layout = itemView.findViewById(R.id.listitem_hour_layout);

        }
    }
}
