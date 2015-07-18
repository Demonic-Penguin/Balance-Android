package com.example.toussaintpeterson.balancegame;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

/**
 * Created by toussaintpeterson on 5/25/15.
 */
public class LandingActivity extends AbstractActivity {
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);

        ImageView logo = (ImageView) findViewById(R.id.logo);

        /*Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LandingActivity.class);
                startActivity(i);
            }
        });*/
    }
}
