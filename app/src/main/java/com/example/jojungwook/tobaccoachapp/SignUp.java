package com.example.jojungwook.tobaccoachapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by jojungwook on 2017. 4. 29..
 */

public class SignUp extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        String[] str = getResources().getStringArray(R.array.spinnercity);  // 배열을 목록에 담는다
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, str); //ArrayAdapter객체를 생성해서 레이아웃과 데이터 매핑
        Spinner s = (Spinner) findViewById(R.id.spinner);    //
        s.setAdapter(adapter);

    }
}
