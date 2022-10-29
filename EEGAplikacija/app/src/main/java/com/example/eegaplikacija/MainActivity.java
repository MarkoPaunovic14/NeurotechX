package com.example.eegaplikacija;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.eegaplikacija.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    boolean turnedOn = false;

    private static MediaPlayer mp;

    TextView textViewLatitude;
    TextView textViewLongitude;
    Button buttonGPS;

    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        mp = MediaPlayer.create(this, R.raw.ringtone1);
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        textViewLatitude = findViewById(R.id.textview_latitude);
        textViewLongitude = findViewById(R.id.textview_longitude);
        buttonGPS = findViewById(R.id.button);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        final Handler handler = new Handler();
        final int delay = 10000; // 1000 milliseconds == 1 second

        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("Running location fetching every 10 seconds!"); // Do your work here
                handler.postDelayed(this, delay);

                updateLocation();

                checkNotify();
            }

            private void updateLocation() {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        textViewLatitude.setText(String.valueOf(location.getLatitude()));
                        textViewLongitude.setText(String.valueOf(location.getLongitude()));
                    }
                });
            }

            private void checkNotify(){
                // TODO Parsiraj signal od servera i ako je notify == 1 pali zvuk i vibraciju



                int notify = 1;
                int sleepy = 0;

                if(sleepy == 1){

                    if (mp.isPlaying()) {
                        mp.seekTo(0);
                    } else {
                        mp.start();
                        v.vibrate(3000);
                    }

                    Snackbar.make(getWindow().getDecorView(), "Playing sound.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
                if(notify == 1){

//                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
//                            .setSmallIcon(R.drawable.ic_launcher_background) // notification icon
//                            .setContentTitle("Simple notification") // title
//                            .setContentText("Hello word") // body message
//                            .setAutoCancel(true); // clear notification when clicked
//
//                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                    PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//                    mBuilder.setContentIntent(pi);
//
//                    NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                    mNotificationManager.notify(0, mBuilder.build());
                }

                // TODO Vrati notify i sleepy na default vrednost
            }
        }, delay);


        buttonGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLocation();
            }

            private void updateLocation() {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        textViewLatitude.setText(String.valueOf(location.getLatitude()));
                        textViewLongitude.setText(String.valueOf(location.getLongitude()));
                    }
                });
            }
        });


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Playing sound.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}