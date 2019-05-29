package com.kalash.m3.AppData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import com.kalash.m3.Util.KeyValue;
import com.kalash.m3.Util.Log_m3;
import com.kalash.m3.R;

import java.util.ArrayList;

public class AnalysisData extends Activity {

    private KeyValue keyValue;

    public AnalysisData(KeyValue keyValue){
        this.keyValue = keyValue;
    }

    @SuppressLint("ResourceType")
    public ArrayList<String> analysis(Data data, Activity activity, Context context){

        if(data != null) {
            ArrayList<String> res = new ArrayList();
            for(int i = 0; i < 40; i++){
                res.add("");
            }
            String hour_primary;
            if(Integer.valueOf(data.getHour()) < 10)
                hour_primary = "0" + data.getHour();
            else
                hour_primary = data.getHour();

            String min_primary;
            if(Integer.valueOf(data.getMin()) < 10)
                min_primary = "0" + data.getMin();
            else
                min_primary = data.getMin();

            String day_primary;
            if(Integer.valueOf(data.getDay()) < 10)
                day_primary = "0" + data.getDay();
            else
                day_primary = data.getDay();

            String month_primary;
            if(Integer.valueOf(data.getMonth()) < 10)
                month_primary = "0" + data.getMonth();
            else
                month_primary = data.getMonth();


            String calendarDate = hour_primary + ":" + min_primary + " "
                        + day_primary + "." + month_primary + "." + data.getYear();

            String calendarDateHistory = day_primary + "." + month_primary + "." + data.getYear();

            String hour = hour_primary + ":" + min_primary;

            String smile = "";
            String weather = "";
            String weatherColor = "";

            int sun = Integer.valueOf(data.getSun());
            int overcast = Integer.valueOf(data.getOvercast());
            int rain = Integer.valueOf(data.getRain());
            int snow = Integer.valueOf(data.getShow());
            int hail = Integer.valueOf(data.getHail());
            int thunderstorm = Integer.valueOf(data.getThunderstorm());
            int fog = Integer.valueOf(data.getFog());
            int wind_speed = Integer.valueOf(data.getWind_speed());
            int night = Integer.valueOf(data.getNight());

            int home_temp_div = Integer.valueOf(data.getHome_temp_div());
            int home_humidity = Integer.valueOf(data.getHome_humidity());
            int home_co2 = Integer.valueOf(data.getHome_co2());


            if (sun == 1) {

                if (fog == 1) {
                    weather = String.valueOf(R.drawable.ic_sun_fog);
                    weatherColor = String.valueOf(R.drawable.ic_sun_fog_color);
                } else if (hail == 1) {
                    weather = String.valueOf(R.drawable.ic_sun_overcast_hail);
                    weatherColor = String.valueOf(R.drawable.ic_sun_overcast_hail_color);
                } else if (snow == 1 && rain == 1) {
                    weather = String.valueOf(R.drawable.ic_night_overcast_rain_snow_2);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_rain_snow_2_color);
                } else if (snow == 1) {
                    weather = String.valueOf(R.drawable.ic_sun_overcast_snow);
                    weatherColor = String.valueOf(R.drawable.ic_sun_overcast_snow_color);
                } else if (rain == 1 && thunderstorm == 1) {
                    weather = String.valueOf(R.drawable.ic_sun_overcast_rain_storm);
                    weatherColor = String.valueOf(R.drawable.ic_sun_overcast_rain_storm_color);
                } else if (rain == 1 && wind_speed >= 6) {
                    weather = String.valueOf(R.drawable.ic_sun_overcast_rain_wind);
                    weatherColor = String.valueOf(R.drawable.ic_sun_overcast_rain_wind_color);
                } else if (rain == 1) {
                    weather = String.valueOf(R.drawable.ic_sun_overcast_rain);
                    weatherColor = String.valueOf(R.drawable.ic_sun_overcast_rain_color);
                } else if (overcast == 1) {
                    weather = String.valueOf(R.drawable.ic_sun_overcast);
                    weatherColor = String.valueOf(R.drawable.ic_sun_overcast_color);
                } else {
                    weather = String.valueOf(R.drawable.ic_sun);
                    weatherColor = String.valueOf(R.drawable.ic_sun_color);
                }
            } else if (sun == 0 && overcast == 1 && night == 0) {

                if (fog == 1) {
                    weather = String.valueOf(R.drawable.ic_overcast_fog);
                    weatherColor = String.valueOf(R.drawable.ic_overcast_fog_color);
                } else if (hail == 1) {
                    weather = String.valueOf(R.drawable.ic_overcast_hail);
                    weatherColor = String.valueOf(R.drawable.ic_overcast_hail_color);
                } else if (snow == 1 && rain == 1) {
                    weather = String.valueOf(R.drawable.ic_night_overcast_rain_snow_2);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_rain_snow_2_color);
                } else if (snow == 1) {
                    weather = String.valueOf(R.drawable.ic_overcast_snow);
                    weatherColor = String.valueOf(R.drawable.ic_overcast_snow_color);
                } else if (rain == 1 && thunderstorm == 1) {
                    weather = String.valueOf(R.drawable.ic_overcast_rain_storm);
                    weatherColor = String.valueOf(R.drawable.ic_overcast_rain_storm_color);
                } else if (rain == 1 && wind_speed >= 6) {
                    weather = String.valueOf(R.drawable.ic_overcast_rain_wind);
                    weatherColor = String.valueOf(R.drawable.ic_overcast_rain_wind_color);
                } else if (rain == 1) {
                    weather = String.valueOf(R.drawable.ic_overcast_rain);
                    weatherColor = String.valueOf(R.drawable.ic_overcast_rain_color);
                } else {
                    weather = String.valueOf(R.drawable.ic_overcast_2);
                    weatherColor = String.valueOf(R.drawable.ic_overcast_2_color);
                }
            } else if (night == 1) {

                if (fog == 1) {
                    weather = String.valueOf(R.drawable.ic_night_fog);
                    weatherColor = String.valueOf(R.drawable.ic_night_fog_color);
                } else if (hail == 1) {
                    weather = String.valueOf(R.drawable.ic_night_overcast_hail);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_hail_color);
                } else if (snow == 1 && rain == 1) {
                    weather = String.valueOf(R.drawable.ic_night_overcast_rain_snow_2);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_rain_snow_2_color);
                } else if (snow == 1) {
                    weather = String.valueOf(R.drawable.ic_night_overcast_snow);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_snow_color);
                } else if (rain == 1 && thunderstorm == 1) {
                    weather = String.valueOf(R.drawable.ic_night_overcast_rain_storm);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_rain_storm_color);
                } else if (rain == 1 && wind_speed >= 6) {
                    weather = String.valueOf(R.drawable.ic_night_overcast_rain_wind);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_rain_wind_color);
                } else if (rain == 1) {
                    weather = String.valueOf(R.drawable.ic_night_overcast_rain);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_rain_color);
                } else if (overcast == 1 && wind_speed >= 6) {
                    weather = String.valueOf(R.drawable.ic_night_overcast_wind_);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_wind_color);
                } else if (overcast == 1) {
                    weather = String.valueOf(R.drawable.ic_night_overcast);
                    weatherColor = String.valueOf(R.drawable.ic_night_overcast_color);
                } else {
                    weather = String.valueOf(R.drawable.ic_night);
                    weatherColor = String.valueOf(R.drawable.ic_night_color);
                }
            } else if (fog == 1 && sun == 0 && overcast == 0 && night == 0) {
                weather = String.valueOf(R.drawable.ic_fog);
                weatherColor = String.valueOf(R.drawable.ic_fog_color);
            }



            if (home_temp_div >= 20 && home_temp_div <= 26) {
                smile = String.valueOf(R.drawable.ic_smile);
            } else if (home_temp_div <= 20 && home_temp_div >= 18) {
                smile = String.valueOf(R.drawable.ic_sad);
            } else if (home_temp_div >= 26) {
                smile = String.valueOf(R.drawable.ic_sad);
            } else if (home_temp_div <= 18) {
                smile = String.valueOf(R.drawable.ic_pain);
            }


            res.add(keyValue.getCalendarData(), calendarDate);
            res.add(keyValue.getDayID(), data.getDayID());

            res.add(keyValue.getHour(), data.getHour());
            res.add(keyValue.getMin(), data.getMin());
            res.add(keyValue.getDay(), data.getDay());
            res.add(keyValue.getMonth(), data.getMonth());
            res.add(keyValue.getYear(), data.getYear());
            res.add(keyValue.getCalendarDateHistory(), calendarDateHistory);
            res.add(keyValue.getHourHistory(), hour);

            res.add(keyValue.getHomeTempDiv(), data.getHome_temp_div());
            res.add(keyValue.getHomeTempMod(), data.getHome_temp_mod());
            res.add(keyValue.getHomeHumidity(), data.getHome_humidity());
            res.add(keyValue.getHomeCO2(), data.getHome_co2());
            res.add(keyValue.getSmile(), smile);

            res.add(keyValue.getOutTempDiv(), data.getOut_temp_div());
            res.add(keyValue.getOutTempMod(), data.getOut_temp_mod());
            res.add(keyValue.getOutHumidity(), data.getOut_humidity());
            res.add(keyValue.getOutPressure(), data.getOut_pressure());
            res.add(keyValue.getWeather(), weather);
            res.add(keyValue.getWeatherColor(), weatherColor);

            res.add(keyValue.getWindDirection(), data.getWind_direction());
            res.add(keyValue.getWindSpeed(), data.getWind_speed());

            return res;

        }
        else
            new Log_m3("data = null").show("d");
        return null;
    }

   public ArrayList<String> homeView(ArrayList<String> data){
        if(data != null){
            new Log_m3("analysisData.homeView").show("i");
            ArrayList<String> arrayList = new ArrayList<>();

            arrayList.add(0, data.get(keyValue.getSmile()));
            arrayList.add(1, data.get(keyValue.getHomeTempDiv()));
            arrayList.add(2, data.get(keyValue.getHomeTempMod()));
            arrayList.add(3, data.get(keyValue.getHomeHumidity()));
            arrayList.add(4, data.get(keyValue.getHomeCO2()));
            arrayList.add(5, data.get(keyValue.getCalendarData()));

            return arrayList;
        }
        else
            return null;

   }

   public ArrayList<String> outView(ArrayList<String> data){

       if(data != null) {
           new Log_m3("analysisData.outView").show("i");
           ArrayList<String> arrayList = new ArrayList<>();

           arrayList.add(0, data.get(keyValue.getWeather()));
           arrayList.add(1, data.get(keyValue.getOutTempDiv()));
           arrayList.add(2, data.get(keyValue.getOutTempMod()));
           arrayList.add(3, data.get(keyValue.getOutHumidity()));
           arrayList.add(4, data.get(keyValue.getOutPressure()));
           arrayList.add(5, data.get(keyValue.getCalendarData()));

           return arrayList;
       }
       else
           return null;

   }

    public ArrayList<String> windView(ArrayList<String> data){

        if(data != null) {
            new Log_m3("analysisData.windView").show("i");
            String wind_direction_text = "";
            int wd = Integer.valueOf(data.get(31));

            if(Integer.valueOf(data.get(32)) != 0){
                if((wd > 350 && wd <= 360)||(wd >= 0 && wd < 10)){
                    wind_direction_text = "ЗАПАДНЫЙ";
                }
                else if(wd >= 10 && wd < 80){
                    wind_direction_text = "С-В";
                }
                else if(wd >= 80 && wd < 100){
                    wind_direction_text = "ВОСТОЧНЫЙ";
                }
                else if(wd >= 100 && wd < 170){
                    wind_direction_text = "Ю-В";
                }
                else if(wd >= 170 && wd < 190){
                    wind_direction_text = "ЮЖНЫЙ";
                }
                else if(wd >= 190 && wd < 260){
                    wind_direction_text = "Ю-З";
                }
                else if(wd >= 260 && wd < 280){
                    wind_direction_text = "ЗАПАДНЫЙ";
                }
                else if(wd >= 280 && wd < 350){
                    wind_direction_text = "С-З";
                }
            }
            else{
                wind_direction_text = "ШТИЛЬ";
            }

            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(0, data.get(keyValue.getWindDirection()));
            arrayList.add(1, wind_direction_text);
            arrayList.add(2, data.get(keyValue.getWindSpeed()));
            arrayList.add(3, data.get(keyValue.getCalendarData()));

            return arrayList;
        }
        else
        return null;
    }

}
