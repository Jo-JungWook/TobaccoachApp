package com.example.jojungwook.tobaccoachapp.deviceServiceController;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jojungwook.tobaccoachapp.R;

/**
 * Created by jojungwook on 2017. 5. 4..
 */

public class MyDeviceController extends AppCompatActivity {
    private TextView mTextMessage;
    private RelativeLayout ntf,home,dashboard;
    Button addBtn;
    ProgressBar pr;
    TextView ts;
    int numSmoking;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                ntf = (RelativeLayout)findViewById(R.id.notificationBtn);
                dashboard = (RelativeLayout)findViewById(R.id.dashboardBtn);
                home = (RelativeLayout)findViewById(R.id.homeBtn);
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        ntf.setVisibility(View.GONE);
                        dashboard.setVisibility(View.GONE);
                        home.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.navigation_dashboard:
                        ntf.setVisibility(View.GONE);
                        dashboard.setVisibility(View.VISIBLE);
                        // View initialize
                        pr = (ProgressBar)findViewById(R.id.progressBar);
                        addBtn = (Button) findViewById(R.id.addBtn);
                        ts = (TextView)findViewById(R.id.numSmoking);

                        // HTTP request
                        // double averageSmoke = (GET 평균 흡연량)
                        // int todaySmoke = (GET 오늘 흡연량)
                        final double averageSmoke = 20.0;    // 저장.
                        final int todaySmoke =0 ;
                        numSmoking = todaySmoke;

                        double smokePercentage = ((double)todaySmoke/averageSmoke) * 100;
                        ts.setText(String.valueOf(todaySmoke));
                        // set Event Listener

                        // 삭제예정
                        addBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                numSmoking = numSmoking+1;
                                int stadard = pr.getProgress();

                                if(pr.getProgress()<100) {
                                    pr.setProgress(stadard + 5);
                                }
                                ts.setText(String.valueOf(numSmoking));


                            }
                        });
                        // - 삭제예정

                        pr.setProgress((int) smokePercentage);
                        home.setVisibility(View.GONE);
                        return true;
                    case R.id.navigation_notifications:
                        ntf.setVisibility(View.VISIBLE);
                        dashboard.setVisibility(View.GONE);
                        home.setVisibility(View.GONE);
                        return true;
                }
                return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_device_controller);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
