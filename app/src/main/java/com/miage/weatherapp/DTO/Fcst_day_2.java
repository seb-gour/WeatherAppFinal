package com.miage.weatherapp.DTO;

import org.json.JSONException;
import org.json.JSONObject;

public class Fcst_day_2 {
    private String date;
    private String day_short;
    private String day_long;
    private double tmin;
    private double tmax;
    private String condition;
    private String condition_key;
    private String icon;
    private String icon_big;
    Hourly_data Hourly_dataObject;

    public Fcst_day_2(JSONObject jo) {
        try {
            this.date = jo.getString("date");
            this.day_short = jo.getString("day_short");
            this.day_long = jo.getString("day_long");
            this.tmin = jo.getDouble("tmin");
            this.tmax = jo.getDouble("tmax");
            this.condition = jo.getString("condition");
            this.condition_key = jo.getString("condition_key");
            this.icon = jo.getString("icon");
            this.icon_big = jo.getString("icon_big");
            this.Hourly_dataObject = new Hourly_data(jo.getJSONObject("hourly_data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getter Methods

    public String getDate() {
        return date;
    }

    public String getDay_short() {
        return day_short;
    }

    public String getDay_long() {
        return day_long;
    }

    public double getTmin() {
        return tmin;
    }

    public double getTmax() {
        return tmax;
    }

    public String getCondition() {
        return condition;
    }

    public String getCondition_key() {
        return condition_key;
    }

    public String getIcon() {
        return icon;
    }

    public String getIcon_big() {
        return icon_big;
    }

    public Hourly_data getHourly_data() {
        return Hourly_dataObject;
    }

    // Setter Methods

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay_short(String day_short) {
        this.day_short = day_short;
    }

    public void setDay_long(String day_long) {
        this.day_long = day_long;
    }

    public void setTmin(double tmin) {
        this.tmin = tmin;
    }

    public void setTmax(double tmax) {
        this.tmax = tmax;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setCondition_key(String condition_key) {
        this.condition_key = condition_key;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setIcon_big(String icon_big) {
        this.icon_big = icon_big;
    }

    public void setHourly_data(Hourly_data hourly_dataObject) {
        this.Hourly_dataObject = hourly_dataObject;
    }
}