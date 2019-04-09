
package com.miage.weatherapp.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class City {

    private String name;
    private String npa;
    private String region;
    private String country;
    private String url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public City(JSONObject o) throws JSONException {
          this.name=o.getString("name");
          this.npa=o.getString("npa");
          this.region=o.getString("region");
          this.country=o.getString("country");
          this.url=o.getString("url");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNpa() {
        return npa;
    }

    public void setNpa(String npa) {
        this.npa = npa;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
