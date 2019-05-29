package com.kalash.m3.Main.RecycleView;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalash.m3.Main.MainActivity;
import com.kalash.m3.R;
import com.kalash.m3.Util.KeyValue;
import com.kalash.m3.Util.OnRecycleViewClick;

import java.util.ArrayList;

import io.rmiri.skeleton.SkeletonViewGroup;

import static com.kalash.m3.Main.MainActivity.mainData;

public class RecycleViewHistoryAdapter extends RecyclerView.Adapter<RecycleViewHistoryAdapter.ViewHolder> {


    ArrayList<String> dayName = new ArrayList();
    ArrayList<String> calendarDate = new ArrayList();
    ArrayList<Integer> dayWeather = new ArrayList();
    ArrayList<String> dayOutTemp = new ArrayList();

    ArrayList<Integer> daySmile = new ArrayList();
    ArrayList<String> dayHomeTemp = new ArrayList();


    ArrayList<ArrayList<String>> data = new ArrayList();
    private ConstraintLayout listitem_history_layout;
    private Context context;
    private Activity activity;
    private KeyValue keyValue;

    ViewHolder holder;


    public RecycleViewHistoryAdapter(Activity activity, Context context,
                                     ArrayList<ArrayList<String>> data, KeyValue keyValue) {
        this.data = data;
        this.activity = activity;
        this.keyValue = keyValue;

        for(int i = 0; i != data.size(); i++){
            dayName.add(i, getNameDay(data.get(i)));
            calendarDate.add(i, data.get(i).get(keyValue.getCalendarDateHistory()));

            dayWeather.add(i, Integer.valueOf(data.get(i).get(keyValue.getWeatherColor())));
            dayOutTemp.add(i, data.get(i).get(keyValue.getOutTempDiv()));

            daySmile.add(i, Integer.valueOf(data.get(i).get(keyValue.getSmile())));
            dayHomeTemp.add(i, data.get(i).get(keyValue.getHomeTempDiv()));

        }
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_history, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.day_name.setText(dayName.get(position));
        holder.calendar_date.setText(calendarDate.get(position));

        holder.day_weather.setImageResource(dayWeather.get(position));
        holder.day_out_temp.setText(dayOutTemp.get(position));


        holder.listitem_history_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    OnRecycleViewClick onRecycleViewClick = (OnRecycleViewClick)activity;
                    onRecycleViewClick.onRecycleViewClickListener(keyValue.getHistoryAdapter(),data.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView day_name;
        TextView calendar_date;
        ImageView day_weather;
        TextView day_out_temp;

        ConstraintLayout listitem_history_layout;
        SkeletonViewGroup historyView;

        public ViewHolder(View itemView) {
            super(itemView);

            listitem_history_layout = itemView.findViewById(R.id.listitem_history_layout);
            day_name = itemView.findViewById(R.id.day_name);
            calendar_date = itemView.findViewById(R.id.calendar_date);
            day_weather = itemView.findViewById(R.id.day_weather);
            day_out_temp = itemView.findViewById(R.id.day_out_temp);
            listitem_history_layout = itemView.findViewById(R.id.listitem_history_layout);

/*
            historyView = new SkeletonViewGroup(context);

            ArrayList<SkeletonModel> historyModel = new ArrayList<>();

            historyModel.add(new SkeletonModelBuilder()
                    .setChildView(day_name)
                    .build());

            historyModel.add(new SkeletonModelBuilder()
                    .setChildView(calendar_date)
                    .build());

            historyModel.add(new SkeletonModelBuilder()
                    .setChildView(day_weather)
                    .build());

            historyModel.add(new SkeletonModelBuilder()
                    .setChildView(day_out_temp)
                    .build());

            historyView.setSkeletonModels(historyModel);

            ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            listitem_history_layout.addView(historyView, layout);

*/


        }
    }

    private String getNameDay(ArrayList<String> data) {
        if (data.get(keyValue.getDay()).equals( mainData.get(keyValue.getDay()))) {
            return "Сегодня";
        } else {
            Integer dayID = Integer.valueOf(data.get(keyValue.getDayID()));
            switch (dayID) {
                case 1:
                    return "Понедельник";
                case 2:
                    return "Вторник";
                case 3:
                    return "Среда";
                case 4:
                    return "Четверг";
                case 5:
                    return "Пятница";
                case 6:
                    return "Суббота";
                case 7:
                    return "Воскресенье";

                default:
                    return "";
            }

        }
    }
}
