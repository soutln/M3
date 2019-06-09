package com.kalash.m3.Util.API;

import com.kalash.m3.AppData.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainDataApi {

    @GET("get_data.php?real=1")
    Call<List<Data>> getData();
}
