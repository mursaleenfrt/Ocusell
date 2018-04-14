package com.ocusell.ocusellapp.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ocusell.ocusellapp.BaseActivity;
import com.ocusell.ocusellapp.R;
import com.ocusell.ocusellapp.signin.SignInActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startSplashTimer();
    }

    private void startSplashTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
            }
        }, 2000);
    }
}
