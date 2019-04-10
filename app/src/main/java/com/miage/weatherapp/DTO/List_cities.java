package com.miage.weatherapp.DTO;

import com.miage.weatherapp.Activity.City;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class List_cities {
    ArrayList<com.miage.weatherapp.Activity.City> citys;

    public List_cities() {
        citys = new ArrayList<com.miage.weatherapp.Activity.City>();
    }

    public List_cities(JSONObject jo) throws JSONException {
        citys= new ArrayList<com.miage.weatherapp.Activity.City>();
        for(int i=0;i<jo.length();i++){
            String ai=String.valueOf(i);
            citys.add(new com.miage.weatherapp.Activity.City(jo.getJSONObject(ai)));
        }
    }

    public ArrayList<City> getCitys() {
        return citys;
    }
}
