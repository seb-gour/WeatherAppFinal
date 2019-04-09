package com.miage.weatherapp.Activity;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.miage.weatherapp.DTO.DonneesMeteo;
import com.miage.weatherapp.R;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CityWeather extends Fragment {

    public static String villeRecherche;
    private ViewPager viewPager;
    private String url;
    private DonneesMeteo donneesMeteo;
    private double longitude;
    private double latitude;
    private String ville;
    private String pays;
    private Context context=getContext();
    private LocationListener locationListener;
    private LocationManager locationManager ;
    private SwipeAdapter swipeAdapter;
    private ArrayList<DonneesMeteo> donnees;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_city, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        donnees =new ArrayList<DonneesMeteo>();





        try {
            place_Info();
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);


        this.jsonParse(villeRecherche);
    }


    private void jsonParse(String villeRecherche) {
        RequestQueue mQ= Volley.newRequestQueue(getActivity());
        String url;
        if(villeRecherche == null || villeRecherche.isEmpty()) {
            url = generate_url_long_lat();
        } else {
            url = generate_url_ville(villeRecherche);
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("*********", "onResponse: "+response);
                        donneesMeteo = new DonneesMeteo(response);

                        try {
                            set_Ville_Pays();

                            Toolbar mActionBarToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                            mActionBarToolbar.setTitle(ville);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        swipeAdapter = new SwipeAdapter(getActivity(),donneesMeteo.getArray_fct());
                        viewPager.setAdapter(swipeAdapter);
                        swipeAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }

        });

        mQ.add(request);


    }


    public void place_Info() throws IOException {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }


        String location_context = getActivity().LOCATION_SERVICE;
        locationManager = (LocationManager) getActivity().getSystemService(location_context);

        List<String> providers = locationManager.getProviders(true);

        for (String provider : providers) {
            try {
                Location location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
                    this.longitude = location.getLongitude();
                    this.latitude = location.getLatitude();
                }

            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    private void set_Ville_Pays() throws IOException {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(this.latitude, this.longitude, 1);
        this.ville = addresses.get(0).getLocality();
        this.pays = addresses.get(0).getCountryName();

    }
    private void makeUseOfNewLocation(Location location) {
        this.longitude=location.getLongitude();
        this.latitude=location.getLatitude();

    }

    public String generate_url_long_lat() {

        return "https://www.prevision-meteo.ch/services/json/lat=" + this.latitude + "lng=" + this.longitude;
    }

    public String generate_url_ville(String ville) {

        return "https://www.prevision-meteo.ch/services/json/" + ville;
    }

    public String getVille() {
        return ville;
    }

    public DonneesMeteo getDonneesMeteo() {
        return donneesMeteo;
    }

    public void setDonneesMeteo(DonneesMeteo donneesMeteo) {
        this.donneesMeteo = donneesMeteo;
    }
}