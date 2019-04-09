package com.miage.weatherapp.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class List_cities {
    ArrayList<City> citys;


    public List_cities(JSONObject jo) throws JSONException {
        citys= new ArrayList<City>();
        for(int i=0;i<jo.length();i++){
            String ai=String.valueOf(i);
            citys.add(new City(jo.getJSONObject(ai)));
        }
    }

    public ArrayList<City> getCitys() {
        return citys;
    }
}
