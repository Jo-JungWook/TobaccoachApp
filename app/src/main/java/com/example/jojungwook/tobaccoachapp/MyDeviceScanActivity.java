package com.example.jojungwook.tobaccoachapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jojungwook.tobaccoachapp.deviceServiceController.DeviceControlActivity;
import com.example.jojungwook.tobaccoachapp.deviceServiceController.MyDeviceController;

/**
 * Created by jojungwook on 2017. 5. 3..
 */

public class MyDeviceScanActivity extends AppCompatActivity {

    private String ipAddress;
    private String deviceAddress; // 고객의 스마트 담배케이스 Mac 주소

    private BluetoothAdapter mBluetoothAdapter;
    private TextView myDeviceName;
    private TextView myDeviceAddress;
    private Button connectBtn;

    private BluetoothDevice scannedDevice;  //블루투스 연결됐는지 확인
    private boolean mScanning;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_device_scan);

        myDeviceName = (TextView)findViewById(R.id.my_device_name);
        myDeviceAddress = (TextView)findViewById(R.id.my_device_address);
        connectBtn = (Button)findViewById(R.id.connect_btn);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scannedDevice == null) {
                    return;
                }

                final Intent intent = new Intent(getApplicationContext(), MyDeviceController.class);
                intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, scannedDevice.getName());
                intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, scannedDevice.getAddress());
                intent.putExtra(DeviceControlActivity.EXTRAS_SERVER_IP, ipAddress);

                if(mScanning){
                    mScanning = false;
                }

                startActivity(intent);
            }
        });

        /*
            DeviceSettingActivity로부터 IP 주소 얻기
         */
        Intent inputAddressIntent = getIntent();
        ipAddress = inputAddressIntent.getStringExtra("ipAddress");
        deviceAddress = inputAddressIntent.getStringExtra("deviceAddress");

        mHandler = new Handler();

        /*
            BLE를 지원하지 않는 앱으로 만들고자 한다면 여전히 동일한  feature를 추가해야 하며 다만 required="false"로
            하면 된다. 런타임에 BLE활성화 여부를 PackageManager.hasSystemFeature로 알아낼 수 있다.
         */
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "BLE is not supported", Toast.LENGTH_SHORT).show();
            finish();
        }

        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        if(mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
            }
        }
        scannedDevice = mBluetoothAdapter.getRemoteDevice(deviceAddress);
        if(scannedDevice == null){
            Toast.makeText(this, "Can't find your smart cigarette case", Toast.LENGTH_SHORT).show();

            // 가까이 간 후에 다시 시도할 수 있도록 코드 추가

        }
        else {
            myDeviceName.setText(scannedDevice.getName());
            myDeviceAddress.setText(scannedDevice.getAddress());
            connectBtn.setEnabled(true); // 버튼 활성화
        }
        //scanMyLeDevice(true); // 10초 후 stopLeScan() 호출
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBluetoothAdapter.isEnabled()) {
            if (mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.disable();
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        connectBtn.setEnabled(false);
    }
}
