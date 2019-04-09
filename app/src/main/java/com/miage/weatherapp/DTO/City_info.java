package com.miage.weatherapp.DTO;

import org.json.JSONException;
import org.json.JSONObject;

public class City_info {
    private String name;
    private String country;
    private String latitude;
    private String longitude;
    private String elevation;
    private String sunrise;
    private String sunset;

    public City_info(JSONObject jo){
        try {
            this.name = jo.getString("name");
            this.country = jo.getString("country");
            this.latitude = jo.getString("latitude");
            this.longitude = jo.getString("longitude");
            this.elevation = jo.getString("elevation");
            this.sunrise = jo.getString("sunrise");
            this.sunset = jo.getString("sunset");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getter Methods

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getElevation() {
        return elevation;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
