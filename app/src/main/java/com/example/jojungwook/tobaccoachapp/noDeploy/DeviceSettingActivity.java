package com.example.jojungwook.tobaccoachapp.noDeploy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jojungwook.tobaccoachapp.MyDeviceScanActivity;
import com.example.jojungwook.tobaccoachapp.R;

/**
 * Created by jojungwook on 2017. 5. 3..
 */

public class DeviceSettingActivity extends AppCompatActivity {
    private EditText inputIpAddress;
    private EditText inputDeviceAddress;
    private String ipAddress;
    private String deviceAddress;
    private Button submitIpAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dev_setting);

        /* IP주소 */
        inputIpAddress = (EditText)findViewById(R.id.edit_ip_address);
        inputDeviceAddress = (EditText)findViewById(R.id.edit_device_address);
        submitIpAddress = (Button)findViewById(R.id.submit_ip_address);
        submitIpAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ipAddress = inputIpAddress.getText().toString();
                deviceAddress = inputDeviceAddress.getText().toString();

                /* DeviceScanActivity */
                //Intent intent = new Intent(getApplicationContext(), DeviceScanActivity.class);
                Intent intent = new Intent(getApplicationContext(), MyDeviceScanActivity.class);
                intent.putExtra("ipAddress", ipAddress);
                intent.putExtra("deviceAddress", deviceAddress);
                startActivity(intent);
            }
        });

    }
}
