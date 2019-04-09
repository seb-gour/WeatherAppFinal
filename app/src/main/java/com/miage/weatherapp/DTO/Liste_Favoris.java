package com.miage.weatherapp.DTO;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Liste_Favoris {
    public ArrayList<City_Favoris> list;

    public Liste_Favoris() {
        this.list = new ArrayList<>();
    }

    public Liste_Favoris(ArrayList<City_Favoris> list) {
        this.list = list;
    }
}
