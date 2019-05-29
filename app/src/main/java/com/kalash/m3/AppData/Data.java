package com.kalash.m3.AppData;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("day")
    private String day;
    @SerializedName("dayID")
    private String dayID;
    @SerializedName("month")
    private String month;
    @SerializedName("year")
    private String year;
    @SerializedName("hour")
    private String hour;
    @SerializedName("min")
    private String min;
    @SerializedName("night")
    private String night;

    @SerializedName("home_temp_div")
    private String home_temp_div;
    @SerializedName("home_temp_mod")
    private String home_temp_mod;
    @SerializedName("home_humidity")
    private String home_humidity;
    @SerializedName("home_co2")
    private String home_co2;

    @SerializedName("out_temp_div")
    private String out_temp_div;
    @SerializedName("out_temp_mod")
    private String out_temp_mod;
    @SerializedName("out_humidity")
    private String out_humidity;
    @SerializedName("out_pressure")
    private String out_pressure;

    @SerializedName("sun")
    private String sun;
    @SerializedName("overcast")
    private String overcast;
    @SerializedName("rain")
    private String rain;
    @SerializedName("show")
    private String show;
    @SerializedName("hail")
    private String hail;
    @SerializedName("thunderstorm")
    private String thunderstorm;
    @SerializedName("fog")
    private String fog;


    @SerializedName("wind_speed")
    private String wind_speed;
    @SerializedName("wind_direction")
    private String wind_direction;

    public String getDay() {
        return day;
    }

    public String getDayID() {
        return dayID;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getHour() {
        return hour;
    }

    public String getMin() {
        return min;
    }

    public String getNight() {
        return night;
    }

    public String getHome_temp_div() {
        return home_temp_div;
    }

    public String getHome_temp_mod() {
        return home_temp_mod;
    }

    public String getHome_humidity() {
        return home_humidity;
    }

    public String getHome_co2() {
        return home_co2;
    }

    public String getOut_temp_div() {
        return out_temp_div;
    }

    public String getOut_temp_mod() {
        return out_temp_mod;
    }

    public String getOut_humidity() {
        return out_humidity;
    }

    public String getOut_pressure() {
        return out_pressure;
    }

    public String getSun() {
        return sun;
    }

    public String getOvercast() {
        return overcast;
    }

    public String getRain() {
        return rain;
    }

    public String getShow() {
        return show;
    }

    public String getHail() {
        return hail;
    }

    public String getThunderstorm() {
        return thunderstorm;
    }

    public String getFog() {
        return fog;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDayID(String dayID) {
        this.dayID = dayID;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public void setHome_temp_div(String home_temp_div) {
        this.home_temp_div = home_temp_div;
    }

    public void setHome_temp_mod(String home_temp_mod) {
        this.home_temp_mod = home_temp_mod;
    }

    public void setHome_humidity(String home_humidity) {
        this.home_humidity = home_humidity;
    }

    public void setHome_co2(String home_co2) {
        this.home_co2 = home_co2;
    }

    public void setOut_temp_div(String out_temp_div) {
        this.out_temp_div = out_temp_div;
    }

    public void setOut_temp_mod(String out_temp_mod) {
        this.out_temp_mod = out_temp_mod;
    }

    public void setOut_humidity(String out_humidity) {
        this.out_humidity = out_humidity;
    }

    public void setOut_pressure(String out_pressure) {
        this.out_pressure = out_pressure;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public void setOvercast(String overcast) {
        this.overcast = overcast;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public void setHail(String hail) {
        this.hail = hail;
    }

    public void setThunderstorm(String thunderstorm) {
        this.thunderstorm = thunderstorm;
    }

    public void setFog(String fog) {
        this.fog = fog;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }
}
