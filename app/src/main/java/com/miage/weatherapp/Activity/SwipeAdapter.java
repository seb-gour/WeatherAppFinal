package com.miage.weatherapp.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miage.weatherapp.DTO.DonneesMeteo;
import com.miage.weatherapp.DTO.Fcst_day_0;
import com.miage.weatherapp.DTO.Hour;
import com.miage.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SwipeAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public ImageView addfav;
    DonneesMeteo donneesMeteo ;
    String ville;
    ArrayList<Fcst_day_0> donees;
    ArrayList<Hour> list;

    public SwipeAdapter(Context context, ArrayList<Fcst_day_0> donnee) {

        this.context = context;
        this.donees=donnee;

    }



    @Override
    public int getCount() {
        return donees.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.page_layout,null);
        TextView textView=(TextView)view.findViewById(R.id.textView);
        TextView textView1=(TextView)view.findViewById(R.id.textView2);
        TextView textView2=(TextView)view.findViewById(R.id.textView3);
        ImageView image =(ImageView)view.findViewById(R.id.imageView);
        ListView listView=(ListView)view.findViewById(R.id.listview);
        this.addfav = (ImageView) view.findViewById(R.id.addfav);
        //textView.setText(message[position]);



        textView2.setText(donees.get(position).getDay_long());
        textView1.setText(donees.get(position).getTmax() + "°C");
        textView.setText(donees.get(position).getTmin() + "°C");
        Picasso.with(context).load(donees.get(position).getIcon_big()).into(image);

        ListAdapter adapter = new ListviewAdapter(context,0,donees.get(position).getHourly_data().getData());
        listView.setAdapter(adapter);

        container.addView(view);


        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }



    /*private void set_Ville_Pays() throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(this.donees.get(donees.size()-1).getCity_info().getLatitude()), Double.parseDouble(this.donees.get(donees.size()-1).getCity_info().getLongitude()), 1);
        this.ville = addresses.get(0).getLocality();
        //this.pays = addresses.get(0).getCountryName();

    }*/

}
