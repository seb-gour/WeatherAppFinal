package com.miage.weatherapp.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miage.weatherapp.DTO.Hour;
import com.miage.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListviewAdapter extends ArrayAdapter<Hour>{

    private ArrayList<Hour> list;
    private TextView tmp1;
    private TextView heure;
    private ImageView img;
    public ListviewAdapter(Context context, int resource,ArrayList<Hour> list) {
        super(context, resource);
        this.list=list;
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
        heure=(TextView)view.findViewById(R.id.textView4);
        img=(ImageView) view.findViewById(R.id.imageView2);

        heure.setText(list.get(position).getHeure());
        tmp1.setText(list.get(position).getTMP2m()+" °C");
        Picasso.with(getContext()).load(list.get(position).getICON()).into(img);

        return view;
    }
}

