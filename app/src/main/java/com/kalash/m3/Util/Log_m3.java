package com.kalash.m3.Util;


import android.util.Log;

import static com.kalash.m3.SplashActivity.getLog_on;

public class Log_m3 {
    private String log_text;
    private String log_tag = "log.m3";

    Log_m3(String log_text, String log_tag){
        this.log_text = log_text;
        this.log_tag = log_tag;
    }

    public Log_m3(String log_text){
        this.log_text = log_text;
    }
    public Log_m3(Integer log_text){
        this.log_text = String.valueOf(log_text);
    }
    public Log_m3(float log_text){
        this.log_text = String.valueOf(log_text);
    }
    public Log_m3(double log_text){
        this.log_text = String.valueOf(log_text);
    }

    public boolean show(String log_type){
        if(getLog_on()){
            switch (log_type){
                case "e":
                    Log.e(log_tag,log_text);
                    break;
                case "w":
                    Log.w(log_tag,log_text);
                    break;
                case "i":
                    Log.i(log_tag,log_text);
                    break;
                case "d":
                    Log.d(log_tag,log_text);
                    break;
                case "v":
                    Log.v(log_tag,log_text);
                    break;
                case "wtf":
                    Log.wtf(log_tag,log_text);
                    break;

                    default:
                        Log.d(log_tag, "Некорректный тег");
            }
            return true;
        }
        else{
            return false;
        }
    }


}