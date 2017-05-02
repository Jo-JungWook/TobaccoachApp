package com.example.jojungwook.tobaccoachapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by jojungwook on 2017. 4. 29..
 */

public class State extends Activity {

    Button addBtn;
    ProgressBar pr;
    TextView ts;
    int numSmoking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

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
    }
}
