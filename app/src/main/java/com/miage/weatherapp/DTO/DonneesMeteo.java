package com.miage.weatherapp.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DonneesMeteo {
    private City_info city_info;
    private Forecast_info forecast_info;
    private Current_condition current_condition;
    //private Fcst_day_0 fcst_day_0;
    ArrayList<Fcst_day_0> array_fct;
    /*private Fcst_day_1 fcst_day_1;
    private Fcst_day_2 fcst_day_2;
    private Fcst_day_3 fcst_day_3;
    private Fcst_day_4 fcst_day_4;*/

    public DonneesMeteo(JSONObject jo) {
        try {
            array_fct = new ArrayList<Fcst_day_0>();
            JSONObject ci = jo.getJSONObject("city_info");
            this.city_info = new City_info(ci);

            JSONObject fi = jo.getJSONObject("forecast_info");
            this.forecast_info = new Forecast_info(fi);

            JSONObject cc = jo.getJSONObject("current_condition");
            this.current_condition = new Current_condition(cc);

            JSONObject fd0 = jo.getJSONObject("fcst_day_0");
            //this.fcst_day_0 = new Fcst_day_0(fd0);
            array_fct.add(new Fcst_day_0(fd0));

            JSONObject fd1 = jo.getJSONObject("fcst_day_1");
            //this.fcst_day_1 = new Fcst_day_1(fd1);
            array_fct.add(new Fcst_day_0(fd1));

            JSONObject fd2 = jo.getJSONObject("fcst_day_2");
            //this.fcst_day_2 = new Fcst_day_2(fd2);
            array_fct.add(new Fcst_day_0(fd2));

            JSONObject fd3 = jo.getJSONObject("fcst_day_3");
            //this.fcst_day_3 = new Fcst_day_3(fd3);
            array_fct.add(new Fcst_day_0(fd3));

            JSONObject fd4 = jo.getJSONObject("fcst_day_4");
            //this.fcst_day_4 = new Fcst_day_4(fd4);
            array_fct.add(new Fcst_day_0(fd4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getter Methods

    public City_info getCity_info() {
        return city_info;
    }

    public ArrayList<Fcst_day_0> getArray_fct() {
        return array_fct;
    }

    public Forecast_info getForecast_info() {
        return forecast_info;
    }

    public Current_condition getCurrent_condition() {
        return current_condition;
    }
/*
    public Fcst_day_0 getFcst_day_0() {
        return fcst_day_0;
    }

    public Fcst_day_1 getFcst_day_1() {
        return fcst_day_1;
    }

    public Fcst_day_2 getFcst_day_2() {
        return fcst_day_2;
    }

    public Fcst_day_3 getFcst_day_3() {
        return fcst_day_3;
    }

    public Fcst_day_4 getFcst_day_4() {
        return fcst_day_4;
    }
*/
    // Setter Methods

    public void setCity_info(City_info city_infoObject) {
        this.city_info = city_infoObject;
    }

    public void setForecast_info(Forecast_info forecast_infoObject) {
        this.forecast_info = forecast_infoObject;
    }

    public void setCurrent_condition(Current_condition current_conditionObject) {
        this.current_condition = current_conditionObject;
    }
/*
    public void setFcst_day_0(Fcst_day_0 fcst_day_0Object) {
        this.fcst_day_0 = fcst_day_0Object;
    }

    public void setFcst_day_1(Fcst_day_1 fcst_day_1Object) {
        this.fcst_day_1 = fcst_day_1Object;
    }

    public void setFcst_day_2(Fcst_day_2 fcst_day_2Object) {
        this.fcst_day_2 = fcst_day_2Object;
    }

    public void setFcst_day_3(Fcst_day_3 fcst_day_3Object) {
        this.fcst_day_3 = fcst_day_3Object;
    }

    public void setFcst_day_4(Fcst_day_4 fcst_day_4Object) {
        this.fcst_day_4 = fcst_day_4Object;
    }*/
}