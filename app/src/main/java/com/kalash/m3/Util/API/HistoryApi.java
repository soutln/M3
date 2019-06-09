package com.kalash.m3.Util.API;

import com.kalash.m3.AppData.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HistoryApi {

    @GET("get_data.php")
    Call<List<Data>> getData(@Query("dd") String dd, @Query("mm") String mm, @Query("hh") String hh);

}
