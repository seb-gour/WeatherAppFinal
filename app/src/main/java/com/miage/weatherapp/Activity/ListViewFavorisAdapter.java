package com.miage.weatherapp.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miage.weatherapp.DTO.City_Favoris;
import com.miage.weatherapp.R;

import java.util.ArrayList;

public class ListViewFavorisAdapter extends ArrayAdapter<City_Favoris>{
    private ArrayList<City_Favoris> list;
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

    public ArrayList<City_Favoris> getList() {
        return list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        view= LayoutInflater.from(getContext()).inflate(R.layout.item_favoris,parent,false);

        ville =(TextView)view.findViewById(R.id.textView4);
        img=(ImageView) view.findViewById(R.id.imageView2);

        ville.setText(list.get(position).name);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FavorisFragment.removeCityFavorisMeteo(list.get(position));
            }
        });;

        return view;
    }
}

