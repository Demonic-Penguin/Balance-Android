package com.example.toussaintpeterson.balancegame;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by toussaintpeterson on 5/25/15.
 */
public class LoginActivity extends AbstractActivity implements LocationListener {
    private Handler mHandler;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        ImageView logo = (ImageView)findViewById(R.id.logo);
        logo.setVisibility(View.GONE);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                2000,
                10, this);


        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView logo = (ImageView)findViewById(R.id.logo);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, logo, "logo");
                Intent i = new Intent(LoginActivity.this, LandingActivity.class);
                startActivity(i, options.toBundle());
    }
        });

        Button registrationButton = (Button)findViewById(R.id.registration_button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView logo = (ImageView)findViewById(R.id.logo);
                LinearLayout loginFields = (LinearLayout)findViewById(R.id.login_fields);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_bottom_fast);
                loginFields.setAnimation(anim);
                loginFields.setVisibility(View.GONE);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, logo, "logo");
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i, options.toBundle());
            }
        });

        LinearLayout loginFields = (LinearLayout)findViewById(R.id.login_fields);
        loginFields.setVisibility(View.GONE);
        EditText mUserName = (EditText)findViewById(R.id.username);
        EditText mPass = (EditText)findViewById(R.id.password);

        handler.postDelayed(runnable, 500);
        fieldHandler.postDelayed(fieldRunnable, 1500);
        fieldHandler.removeCallbacks(fieldRunnable);

    }

    @Override
    protected void onResume() {
        super.onResume();

        fieldHandler.postDelayed(fieldRunnable, 1000);
    }

    private Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //handler.postDelayed(runnable, 5000);
            ImageView logo = (ImageView)findViewById(R.id.logo);
            logo.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            logo.setAnimation(anim);
        }
    };

    private Handler fieldHandler = new Handler();

    Runnable fieldRunnable = new Runnable() {
        @Override
        public void run() {
            //fieldHandler.postDelayed(runnable, 5000);
            LinearLayout loginFields = (LinearLayout)findViewById(R.id.login_fields);
            loginFields.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
            loginFields.setAnimation(anim);
        }
    };

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }
    @Override

    public void onLocationChanged(Location location) {

        TextView currentZip = (TextView)findViewById(R.id.zip);
        TextView currentCity = (TextView)findViewById(R.id.city);
        Geocoder geocoder;
        geocoder = new Geocoder(this, Locale.getDefault());
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String postalCode = addresses.get(0).getPostalCode();
            String city = addresses.get(0).getAddressLine(0);
            currentCity.setText(city);
            currentZip.setText(postalCode);
        }
        catch(IOException ie) {
            ie.printStackTrace();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
}
