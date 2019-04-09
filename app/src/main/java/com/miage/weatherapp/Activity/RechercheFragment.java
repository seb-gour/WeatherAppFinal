package com.miage.weatherapp.Activity;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.miage.weatherapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RechercheFragment extends Fragment {
    private RequestQueue mQ;
    private  SearchView searchView;
    private EditText a;
    private ListView v;
    private double longe;
    private double lat;
    private LocationListener locationListener;
    List_cities cities;
    ListCityAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_recherche, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //a = (EditText) findViewById(R.id.editText);
        searchView = view.findViewById(R.id.searchView);
        //final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        v =(ListView) view.findViewById(R.id.textv);

        mQ = Volley.newRequestQueue(getContext());

        jsonParse("https://www.prevision-meteo.ch/services/json/list-cities");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<City> ar=new ArrayList<>();
                for (City x:cities.getCitys()){
                    if (x.getName().toLowerCase().contains(newText.toLowerCase())){
                        ar.add(x);
                    }
                }
                adapter.update(ar);

                return false;
            }
        });
        /*searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                                                      @Override
                                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                                      }
                                                  });*/


/*        a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
    }

    private void makeUseOfNewLocation(Location location) {
        this.longe = location.getLongitude();
        this.lat = location.getLatitude();
        this.a.setText(this.longe + " " + this.lat);
    }



    private void jsonParse(String url){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("I", "onResponse: "+response);
                        try {
                            cities = new List_cities(response);
                            adapter = new ListCityAdapter(getContext(),0,cities.getCitys());
                            v.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // call to method

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", "Error occurred ", error);
                    }
                });

        requestQueue.add(jsonObject);

    }
}
