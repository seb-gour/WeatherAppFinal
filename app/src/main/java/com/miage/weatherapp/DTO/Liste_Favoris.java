package com.miage.weatherapp.DTO;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Liste_Favoris {
    public ArrayList<City_Favoris> list;

    public Liste_Favoris() {
        this.list = new ArrayList<City_Favoris>();
    }

    public Liste_Favoris(ArrayList<City_Favoris> list) {
        if(list == null) {
            list = new ArrayList<City_Favoris>();
        } else {
            this.list = list;
        }
    }
}
