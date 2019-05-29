package com.kalash.m3.AppData;

import android.app.Activity;
import android.content.Context;

import com.kalash.m3.Main.AnimationBtn.AnimationBtnUpdate;
import com.kalash.m3.Util.API.DayApi;
import com.kalash.m3.Util.API.HistoryApi;
import com.kalash.m3.Util.KeyValue;
import com.kalash.m3.Util.Log_m3;
import com.kalash.m3.Util.API.MainDataApi;
import com.kalash.m3.Util.OnDataAvailability;
import com.kalash.m3.Util.OnHistoryAvailability;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetDataFromServer{

    public static String serverURL = "https://arty212.com/";
    KeyValue keyValue;
    AnalysisData analysisData;

    public static boolean validateUrl(){
        return android.util.Patterns.WEB_URL.matcher(serverURL).matches();
    }

    public GetDataFromServer(KeyValue keyValue, AnalysisData analysisData){
        this.keyValue = keyValue;
        this.analysisData = analysisData;
    }

    public void getMainData(Activity activity, Context context, AnimationBtnUpdate animationBtnUpdate){

        animationBtnUpdate.onAnimationUpdate(true, context);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MainDataApi dataApi = retrofit.create(MainDataApi.class);
        
        dataApi.getData().enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, retrofit2.Response<List<Data>> response) {

                try {
                    Data dt = response.body().get(0);
                    new Log_m3("getMainData: данные получены").show("d");
                    ArrayList<String> res = analysisData.analysis(dt, activity,context);
                    OnDataAvailability onDataAvailability = (OnDataAvailability) activity;
                    onDataAvailability.onDataAvailabilityListener(res);
                    getHistoryData(activity, context, res.get(4), res.get(5), res.get(2));

                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
                catch (Exception e){
                    new Log_m3("getMainData: error " + e).show("d");
                }

            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                new Log_m3("failure " + t).show("d");
            }
        });


    }

    public void getHistoryData(Activity activity, Context context, String dd, String mm, String hh){
        new Log_m3("getHistoryData: onStart").show("d");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HistoryApi historyApi = retrofit.create(HistoryApi.class);

        historyApi.getData(dd, mm, hh).enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, retrofit2.Response<List<Data>> response) {

                try {
                    new Log_m3("getHistoryData: данные получены " + response.body().size()).show("d");
                    ArrayList<ArrayList<String>> res = new ArrayList();
                    for(int i = 0; i != response.body().size(); i++){
                        Data dt = response.body().get(i);
                        res.add(i ,analysisData.analysis(dt, activity, context));
                    }
                    OnHistoryAvailability onHistoryAvailability = (OnHistoryAvailability) activity;
                    onHistoryAvailability.onHistoryAvailabilityListener(keyValue.getHistoryAdapter(),res);


                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
                catch (Exception e){
                    new Log_m3("getHistoryData: error " + e).show("d");
                }

            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                new Log_m3("failure " + t).show("d");
            }
        });


    }

    public void getDayData(Activity activity, Context context, String dd, String mm){
        new Log_m3("getDayData: onStart").show("d");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DayApi dayApi = retrofit.create(DayApi.class);

        dayApi.getData(dd, mm).enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, retrofit2.Response<List<Data>> response) {

                try {
                    new Log_m3("getDayData: данные получены " + response.body().size()).show("d");

                    ArrayList<ArrayList<String>> res = new ArrayList();
                    for(int i = 0; i != response.body().size(); i++){
                        Data dt = response.body().get(i);
                        res.add(i ,analysisData.analysis(dt, activity, context));
                    }
                    OnHistoryAvailability onHistoryAvailability = (OnHistoryAvailability) activity;
                    onHistoryAvailability.onHistoryAvailabilityListener(keyValue.getHourAdapter(),res);


                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
                catch (Exception e){
                    new Log_m3("getDayData: error " + e).show("d");
                }

            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                new Log_m3("failure " + t).show("d");
            }
        });


    }


}
