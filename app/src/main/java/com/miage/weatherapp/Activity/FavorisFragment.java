package com.miage.weatherapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.miage.weatherapp.DTO.City_Favoris;
import com.miage.weatherapp.DTO.City_info;
import com.miage.weatherapp.DTO.DonneesMeteo;
import com.miage.weatherapp.DTO.Liste_Favoris;
import com.miage.weatherapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

public class FavorisFragment extends Fragment {

    private DonneesMeteo donneesMeteo;
    Liste_Favoris favoris;
    ValueEventListener favorisListener;
    ListView listView;
    ListViewFavorisAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoris, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoris = new Liste_Favoris();

        listView = (ListView) view.findViewById(R.id.listView);

        if (MainActivity.mAuth.getCurrentUser() != null) {
            MainActivity.mDatabase = FirebaseDatabase.getInstance().getReference(MainActivity.mAuth.getUid());

            favorisListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    favoris = dataSnapshot.getValue(Liste_Favoris.class);
                    createListViewFavoris(dataSnapshot);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w("TAG", "loadFavoris:onCancelled", databaseError.toException());
                    // ...
                }
            };
            MainActivity.mDatabase.addValueEventListener(favorisListener);

        }
    }

    public void createListViewFavoris(DataSnapshot dataSnapshot) {
        if(favoris != null) {
            ArrayList<City_Favoris> city_list = new ArrayList<>();
            for (City_Favoris city: favoris.list) {
                city_list.add(getTempFromCityName(city));
            }
            adapter = new ListViewFavorisAdapter(getContext(),0,city_list);
            listView.setAdapter(adapter);
        }
    }

    public void addCityFavorisMeteo(String city, String city_url) {
        this.favoris.list.add(new City_Favoris(city, city_url));
        MainActivity.mDatabase.setValue(favoris);
    }

    public void removeCityFavorisMeteo(String city, String city_url) {
        int i = -1;
        for (City_Favoris city_fav:favoris.list) {
            if(city_fav.name.equals(city) && city_fav.name_url.equals(city_url)) {
                i = favoris.list.indexOf(city_fav);
            }
        };
        if(i != -1) {
            favoris.list.remove(i);
            MainActivity.mDatabase.setValue(favoris);
        }
    }

    public City_Favoris getTempFromCityName(City_Favoris city) {
        final City_Favoris new_city = city;
        RequestQueue mQ= Volley.newRequestQueue(getContext());


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, generate_url_ville(city.name_url), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("*********", "onResponse: "+response);
                        donneesMeteo = new DonneesMeteo(response);

                        new_city.tmp = String.valueOf(donneesMeteo.getCurrent_condition().getTmp());
                        new_city.icon = donneesMeteo.getCurrent_condition().getIcon();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }

        });

        mQ.add(request);

        return new_city;
    }

    public String generate_url_ville(String ville) {

        return "https://www.prevision-meteo.ch/services/json/" + ville;
    }
}
