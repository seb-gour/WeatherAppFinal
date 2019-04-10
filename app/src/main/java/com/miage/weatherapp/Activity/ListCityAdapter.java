package com.miage.weatherapp.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miage.weatherapp.R;

import java.util.ArrayList;


public class ListCityAdapter extends ArrayAdapter<City>{

    private ArrayList<City> list;
    private TextView tmp1;
    private TextView tmp2;
    private TextView heure;
    private ImageView img;
    public ListCityAdapter(Context context, int resource, ArrayList<City> list) {
        super(context, resource);
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void update(ArrayList<City> result){
        list=new ArrayList<>();
        list.addAll(result);
        notifyDataSetChanged();
    }

    public ArrayList<City> getList() {
        return list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view= LayoutInflater.from(getContext()).inflate(R.layout.list_citie,parent,false);
        tmp1=(TextView)view.findViewById(R.id.textView2);



        tmp1.setText(list.get(position).getName());


        return view;
    }
}

