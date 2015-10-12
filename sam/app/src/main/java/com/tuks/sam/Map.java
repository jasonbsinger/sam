package com.tuks.sam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.gms.maps.GoogleMap;

public class Map extends AppCompatActivity implements OnClickListener {


    UserLocalStorage userLocalStorage;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        userLocalStorage = new UserLocalStorage(this);
    }


    @Override
    public void onClick(View v) {

    }
}
