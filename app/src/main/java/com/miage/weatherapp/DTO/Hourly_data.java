package com.miage.weatherapp.DTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hourly_data {
 Hour _0H00Object;
 Hour _1H00Object;
 Hour _2H00Object;
 Hour _3H00Object;
 Hour _4H00Object;
 Hour _5H00Object;
 Hour _6H00Object;
 Hour _7H00Object;
 Hour _8H00Object;
 Hour _9H00Object;
 Hour _10H00Object;
 Hour _11H00Object;
 Hour _12H00Object;
 Hour _13H00Object;
 Hour _14H00Object;
 Hour _15H00Object;
 Hour _16H00Object;
 Hour _17H00Object;
 Hour _18H00Object;
 Hour _19H00Object;
 Hour _20H00Object;
 Hour _21H00Object;
 Hour _22H00Object;
 Hour _23H00Object;
  private ArrayList<Hour> data;

    public ArrayList<Hour> getData() {
        return data;
    }

    public Hourly_data(JSONObject jo) {
        try {
            data=new ArrayList<Hour>();
            data.add (new Hour(jo.getJSONObject("0H00"),"0H00"));
            data.add(new Hour(jo.getJSONObject("1H00"),"1H00"));
            data.add(new Hour(jo.getJSONObject("2H00"),"2H00"));
            data.add(new Hour(jo.getJSONObject("3H00"),"3H00"));
            data.add(new Hour(jo.getJSONObject("4H00"),"4H00"));
            data.add(new Hour(jo.getJSONObject("5H00"),"5H00"));
            data.add(new Hour(jo.getJSONObject("6H00"),"6H00"));
            data.add(new Hour(jo.getJSONObject("7H00"),"7H00"));
            data.add(new Hour(jo.getJSONObject("8H00"),"8H00"));
            data.add(new Hour(jo.getJSONObject("9H00"),"9H00"));
            data.add(new Hour(jo.getJSONObject("10H00"),"10H00"));
            data.add(new Hour(jo.getJSONObject("11H00"),"11H00"));
            data.add(new Hour(jo.getJSONObject("12H00"),"12H00"));
            data.add(new Hour(jo.getJSONObject("13H00"),"13H00"));
            data.add(new Hour(jo.getJSONObject("14H00"),"14H00"));
            data.add(new Hour(jo.getJSONObject("15H00"),"15H00"));
            data.add(new Hour(jo.getJSONObject("16H00"),"16H00"));
            data.add(new Hour(jo.getJSONObject("17H00"),"17H00"));
            data.add(new Hour(jo.getJSONObject("18H00"),"18H00"));
            data.add(new Hour(jo.getJSONObject("19H00"),"19H00"));
            data.add(new Hour(jo.getJSONObject("20H00"),"20H00"));
            data.add(new Hour(jo.getJSONObject("21H00"),"21H00"));
            data.add(new Hour(jo.getJSONObject("22H00"),"22H00"));
            data.add(new Hour(jo.getJSONObject("23H00"),"23H00"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getter Methods


}