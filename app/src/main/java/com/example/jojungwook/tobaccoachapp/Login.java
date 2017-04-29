package com.example.jojungwook.tobaccoachapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView inputText = (TextView) findViewById(R.id.InputID);  //ID 입력란
        final TextView inputPass = (TextView) findViewById(R.id.InputPass);    //Password 입력란

        ImageButton LoginBtn = (ImageButton) findViewById(R.id.LoginBtn);    //Login Button
        ImageButton SignUpBtn = (ImageButton) findViewById(R.id.SignUpBtn);  //SignUp Button

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputText.toString().equals("jo") && inputPass.toString().equals("1234")) {
                    Intent intent = new Intent(getApplicationContext(), State.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this, "아이디 또는 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        SignUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });
    }

}
