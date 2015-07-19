package com.example.toussaintpeterson.balancegame;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by toussaintpeterson on 5/25/15.
 */
public class RegisterActivity extends AbstractActivity {
    private Handler mHandler;
    private SharedPreferences register;
    private RelativeLayout water;
    private RelativeLayout earth;
    private RelativeLayout light;
    private RelativeLayout dark;
    private RelativeLayout fire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Parse.initialize(this, "vAwVAGkT0A4iuwTZU3ojah0425hbQrm2rjR1VsHU", "NB5evnF8uensxU41CSAkLzQKh9MCsdsAX3A5hFUZ");

        register = this.getPreferences(Context.MODE_PRIVATE);

        water = (RelativeLayout)findViewById(R.id.water);
        earth = (RelativeLayout)findViewById(R.id.earth);
        light = (RelativeLayout)findViewById(R.id.light);
        dark = (RelativeLayout)findViewById(R.id.dark);
        fire = (RelativeLayout)findViewById(R.id.fire);

        ImageView logo = (ImageView) findViewById(R.id.logo);
        //Button rButton = (Button) findViewById(R.id.register_button);
        Button nextFaction = (Button)findViewById(R.id.next_button);
        nextFaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = register.edit();
                EditText rUser = (EditText) findViewById(R.id.user);
                EditText rPass = (EditText) findViewById(R.id.pword);
                EditText rEmail = (EditText) findViewById(R.id.email);
                editor.putString("name", rUser.getText().toString()).apply();
                editor.putString("pass", rPass.getText().toString()).apply();
                editor.putString("email", rEmail.getText().toString()).apply();

                LinearLayout regFields = (LinearLayout) findViewById(R.id.register_fields);
                LinearLayout factionField = (LinearLayout) findViewById(R.id.faction);
                ImageView logo = (ImageView) findViewById(R.id.logo);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_left);
                Animation animIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                regFields.setAnimation(anim);
                regFields.setVisibility(View.GONE);
                factionField.startAnimation(animIn);
                factionField.setVisibility(View.VISIBLE);
                logo.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(), register.getString("name", "null"), Toast.LENGTH_LONG).show();
            }
        });
        LinearLayout regFields = (LinearLayout)findViewById(R.id.register_fields);
        regFields.setVisibility(View.GONE);

        RelativeLayout divine = (RelativeLayout)findViewById(R.id.divine);
        divine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = register.edit();
                editor.putString("faction", "divine").apply();

                LinearLayout factions = (LinearLayout) findViewById(R.id.faction);
                RelativeLayout classes = (RelativeLayout) findViewById(R.id.classes);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_left);
                Animation animIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                factions.startAnimation(anim);
                classes.startAnimation(animIn);
                classes.setVisibility(View.VISIBLE);
                factions.setVisibility(View.GONE);
                //classes.setBackgroundResource(R.drawable.divine);
            }
        });

        RelativeLayout dark = (RelativeLayout)findViewById(R.id.darkness);
        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = register.edit();
                editor.putString("faction", "darkness").apply();

                LinearLayout factions = (LinearLayout) findViewById(R.id.faction);
                RelativeLayout classes = (RelativeLayout) findViewById(R.id.classes);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_left);
                Animation animIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
                factions.startAnimation(anim);
                classes.startAnimation(animIn);
                classes.setVisibility(View.VISIBLE);
                factions.setVisibility(View.GONE);
                //classes.setBackgroundResource(R.drawable.divine);
            }
        });

        TextView air = (TextView)findViewById(R.id.air_text);
        air.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView air = (TextView)findViewById(R.id.air_text);
                RelativeLayout dark = (RelativeLayout)findViewById(R.id.dark);

                water.setVisibility(View.GONE);
                earth.setVisibility(View.GONE);
                light.setVisibility(View.GONE);
                dark.setVisibility(View.GONE);
                fire.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(), air.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        /*rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister();
            }
        });*/

        fieldHandler.postDelayed(fieldRunnable, 800);
    }

    private Handler fieldHandler = new Handler();

    Runnable fieldRunnable = new Runnable() {
        @Override
        public void run() {
            //fieldHandler.postDelayed(runnable, 5000);
            LinearLayout regFields = (LinearLayout)findViewById(R.id.register_fields);
            regFields.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
            regFields.setAnimation(anim);
        }
    };

    public void onRegister() {
        EditText rUser = (EditText) findViewById(R.id.user);
        EditText rPass = (EditText) findViewById(R.id.pword);
        EditText rEmail = (EditText) findViewById(R.id.email);

        ParseUser user =  new ParseUser();
        user.setUsername(rUser.getText().toString());
        user.setPassword(rPass.getText().toString());
        user.setEmail(rEmail.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    //mHandler.postDelayed(runnable, 500);
                } else {
                    //error
                }
            }
        });

    }
}
