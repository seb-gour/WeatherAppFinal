package com.miage.weatherapp.Activity;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.miage.weatherapp.DTO.City_Favoris;
import com.miage.weatherapp.DTO.DonneesMeteo;
import com.miage.weatherapp.DTO.Hour;
import com.miage.weatherapp.DTO.Liste_Favoris;
import com.miage.weatherapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ListViewFavorisAdapter extends ArrayAdapter<City_Favoris>{
    private ArrayList<City_Favoris> list;
    private TextView tmp1;
    private TextView ville;
    private ImageView img;
    public ListViewFavorisAdapter(Context context, int resource, ArrayList<City_Favoris> favoris) {
        super(context, resource);
        this.list=favoris;
    }

    @Override
    public int getCount() {
        return list.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view= LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
        tmp1=(TextView)view.findViewById(R.id.textView5);
        ville =(TextView)view.findViewById(R.id.textView4);
        img=(ImageView) view.findViewById(R.id.imageView2);
        ville.setText(list.get(position).name);

        tmp1.setText(list.get(position).tmp+" °C");
        Picasso.with(getContext()).load(list.get(position).icon).into(img);

        return view;
    }
}

