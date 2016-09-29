package com.example.csi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class CrimiReportActivity extends Activity implements LocationListener{


    public Button Back;
    private LocationManager locationManager;
    private Criminal criminal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crimireport);
        Back = (Button)findViewById(R.id.button4);
        Intent intent = getIntent();
        CriminalProvider criminalProvider = new CriminalProvider(this);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageDrawable(criminal.mugshot);
        final int chosenCriminalPosition = Integer.valueOf(intent.getStringExtra("position"));
        this.criminal = criminalProvider.GetCriminal(chosenCriminalPosition);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000, 1, this);

        Back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrimiReportActivity.this , CrimiActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        String msg = "New Latitude: " + location.getLatitude()
                + "New Longitude: " + location.getLongitude();

        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

        float distance = location.distanceTo(criminal.lastKnownLocation);

        Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (distance < 100) {

                long[] pattern = new long[] { 20, 50, 100, 200, 40, 100 } ;
                v.vibrate(pattern, 0);

                v.vibrate(500);
            }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    //Geen tijd meer voor jammer genoeg

    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();

    }
}

