package com.example.alertonroad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private final String BASE_URL = "http://192.168.0.13:3000/pullUser/?id=2";
    private final String LOCATION_URL = "http://192.168.0.13:3000/locationChange/?id=2&x=";
    private final String SLEEPY_URL = "http://192.168.0.13:3000/resetSleepy/?id=2";


    LocationManager locationManager;
    public static MediaPlayer mp;
    public static Vibrator v;


    Button btnAwake;
    ProgressDialog pd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.ringtone1);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



        btnAwake = findViewById(R.id.btnAwake);
        btnAwake.setEnabled(true);
        btnAwake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO UPDATE server da je sleepy = 0

                new UpdateLocationTask().execute(SLEEPY_URL);
                if (mp != null) {
                    if (mp.isPlaying())
                        mp.stop();
                }
            }
        });


        final Handler handler = new Handler();
        final int delay = 10000; // 1000 milliseconds == 1 second

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("Running location fetching every 10 seconds!"); // Do your work here
                handler.postDelayed(this, delay);

                updateLocation();
                // Notify i Sleepy update
                new UpdateDataTask().execute(BASE_URL);
            }

            private void updateLocation() {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    //Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        StringBuilder locationUrl = new StringBuilder(LOCATION_URL);
                        locationUrl.append(String.valueOf(location.getLatitude()));
                        locationUrl.append("&y=");
                        locationUrl.append(String.valueOf(location.getLongitude()));

                        new UpdateLocationTask().execute(String.valueOf(locationUrl));
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });
            }
        }, delay);

    }


}
