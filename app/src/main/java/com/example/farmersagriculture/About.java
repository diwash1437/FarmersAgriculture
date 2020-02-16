package com.example.farmersagriculture;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class About extends Fragment {

    View rootView;
    WebView webView;
    String URL = "https://www.google.com/maps/place/Softwarica+College+Of+IT+%26+E-Commerce/@27.7061997,85.3278456,17z/data=!3m1!4b1!4m5!3m4!1s0x39eb190a74aa1f23:0x74ebef82ad0e5c15!8m2!3d27.706195!4d85.3300396";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle b) {
        rootView = inflater.inflate(R.layout.activity_about, group, false);

        webView = rootView.findViewById(R.id.map);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(URL);

        return rootView;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

}


