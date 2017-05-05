package com.example.jojungwook.tobaccoachapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.jojungwook.tobaccoachapp.noDeploy.DeviceSettingActivity;

public class StartingLoading extends AppCompatActivity {
    private ActionBar actionBar;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            actionBar = getSupportActionBar();
            actionBar.hide();
            setContentView(R.layout.activity_starting_loading);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(getApplicationContext(), DeviceSettingActivity.class);
                    startActivity(intent);

                    finish();
                }
            }).start();
        }

}
