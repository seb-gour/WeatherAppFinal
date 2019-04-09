package com.miage.weatherapp.DTO;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class City_Favoris {
    public String name;
    public String name_url;
    public String tmp;
    public String icon;

    public City_Favoris() {
    }

    public City_Favoris(String name, String name_url) {
        this.name = name;
        this.name_url = name_url;
    }

    public City_Favoris(String name, String name_url, String tmp, String icon) {
        this.name = name;
        this.name_url = name_url;
        this.tmp = tmp;
        this.icon = icon;
    }
}
