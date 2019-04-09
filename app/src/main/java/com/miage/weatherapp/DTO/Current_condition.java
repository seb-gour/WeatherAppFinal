package com.miage.weatherapp.DTO;

import org.json.JSONException;
import org.json.JSONObject;

public class Current_condition {
    private String date;
    private String hour;
    private double tmp;
    private double wnd_spd;
    private double wnd_gust;
    private String wnd_dir;
    private double pressure;
    private double humidity;
    private String condition;
    private String condition_key;
    private String icon;
    private String icon_big;

    public Current_condition(JSONObject jo) {
        try {
            this.date = jo.getString("date");
            this.hour = jo.getString("hour");
            this.tmp = jo.getDouble("tmp");
            this.wnd_spd = jo.getDouble("wnd_spd");
            this.wnd_gust = jo.getDouble("wnd_gust");
            this.wnd_dir = jo.getString("wnd_dir");
            this.pressure = jo.getDouble("pressure");
            this.humidity = jo.getDouble("humidity");
            this.condition = jo.getString("condition");
            this.condition_key = jo.getString("condition_key");
            this.icon = jo.getString("icon");
            this.icon_big = jo.getString("icon_big");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getter Methods

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public double getTmp() {
        return tmp;
    }

    public double getWnd_spd() {
        return wnd_spd;
    }

    public double getWnd_gust() {
        return wnd_gust;
    }

    public String getWnd_dir() {
        return wnd_dir;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
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

    // Setter Methods

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setTmp(double tmp) {
        this.tmp = tmp;
    }

    public void setWnd_spd(double wnd_spd) {
        this.wnd_spd = wnd_spd;
    }

    public void setWnd_gust(double wnd_gust) {
        this.wnd_gust = wnd_gust;
    }

    public void setWnd_dir(String wnd_dir) {
        this.wnd_dir = wnd_dir;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
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
}
