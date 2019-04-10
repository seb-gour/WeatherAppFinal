package com.miage.weatherapp.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.miage.weatherapp.DTO.City_Favoris;
import com.miage.weatherapp.DTO.Liste_Favoris;
import com.miage.weatherapp.R;

public class FavorisFragment extends Fragment {

    public static Liste_Favoris favoris;
    ValueEventListener favorisListener;
    ListView listView;
    public static ListViewFavorisAdapter adapter;
    private String tmp;
    private String icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoris, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar mActionBarToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Favoris");

        listView = (ListView) view.findViewById(R.id.listView);

        if (MainActivity.mAuth.getCurrentUser() != null) {
            MainActivity.mDatabase.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            favoris = dataSnapshot.getValue(Liste_Favoris.class);
                            createListViewFavoris();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
            /*favorisListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    favoris = dataSnapshot.getValue(Liste_Favoris.class);
                    createListViewFavoris();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w("TAG", "loadFavoris:onCancelled", databaseError.toException());
                    // ...
                }
            };
            MainActivity.mDatabase.addValueEventListener(favorisListener);*/

        }
    }

    public void createListViewFavoris() {
        if(favoris != null) {
            /*ArrayList<City_Favoris> city_list = new ArrayList<>();
            for (City_Favoris city: favoris.list) {
                city_list.add(getTempFromCityName(city));
            }*/
            adapter = new ListViewFavorisAdapter(getContext(),0,favoris.list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CityWeather city = new CityWeather();
                    final Bundle bundle = new Bundle();
                    bundle.putString("ville",adapter.getList().get(position).name_url);
                    bundle.putString("ville2",adapter.getList().get(position).name);
                    city.setArguments(bundle);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, city).commit();
                }
            });
        }
    }

    public static void updateListeFavoris() {
        MainActivity.mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        favoris = dataSnapshot.getValue(Liste_Favoris.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    public static void addCityFavorisMeteo(Context c, City_Favoris city) {
        if(favoris == null) {
            favoris = new Liste_Favoris();
        }
        favoris.list.add(city);
        MainActivity.mDatabase.setValue(favoris);

        showAlert(c, "Ville " + city.name + " ajouté aux favoris!");
    }

    public static void removeCityFavorisMeteo(Context c, City_Favoris city) {
        if(favoris.list != null) {
            int i = -1;
            for (City_Favoris city_fav : favoris.list) {
                if (city_fav.name.equals(city.name) && city_fav.name_url.equals(city.name_url)) {
                    i = favoris.list.indexOf(city_fav);
                }
            }
            if (i != -1) {
                favoris.list.remove(i);
                MainActivity.mDatabase.setValue(favoris);
            }
            if(adapter != null) {
                adapter.notifyDataSetChanged();
            }
            showAlert(c, "Ville " + city.name + " supprimé des favoris!");
        }
    }

    public static void showAlert(Context c, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*public City_Favoris getTempFromCityName(City_Favoris city) {
        RequestQueue mQ= Volley.newRequestQueue(getActivity());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, generate_url_ville(city.name_url), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("*********", "onResponse: "+response);
                        DonneesMeteo donneesMeteo = new DonneesMeteo(response);

                        tmp = String.valueOf(donneesMeteo.getCurrent_condition().getTmp());
                        icon = donneesMeteo.getCurrent_condition().getIcon();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }

        });
        mQ.add(request);
        city.tmp = tmp;
        city.icon = icon;
        return city;
    }*/

    public String generate_url_ville(String ville) {

        return "https://www.prevision-meteo.ch/services/json/" + ville;
    }
}
