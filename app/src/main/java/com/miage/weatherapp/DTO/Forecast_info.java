package com.miage.weatherapp.DTO;

import org.json.JSONException;
import org.json.JSONObject;

public class Forecast_info {
    private String latitude = null;
    private String longitude = null;
    private String elevation;

    public Forecast_info(JSONObject jo) {
        try {
            this.latitude = jo.getString("latitude");
            this.longitude = jo.getString("longitude");
            this.elevation = jo.getString("elevation");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getter Methods

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getElevation() {
        return elevation;
    }

    // Setter Methods

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
}
