package com.example.nguyenthithu.myapplication;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentStatePagerAdapter mSectionsPagerAdapter;
    //
    private List<Fragment> mFragmentList = new ArrayList<>();
    private int[] tabIcons = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };
    // khai báo biến gửi nhận dữ liệu từ USB
    private static final String ACTION_USB_PERMISSION = "com.android.recipes.USB_PERMISSION";
    private static final String INTENT_ACTION_GRANT_USB = BuildConfig.APPLICATION_ID + ".GRANT_USB";
    UsbSerialPort port;
    String buffer ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        mFragmentList.add(new Tab1Fragment());
        mFragmentList.add(new Tab2Fragment());
        mFragmentList.add(new Tab3Fragment());
        mSectionsPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }
        };

        viewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

       // tabLayout.addTab(tabLayout.newTab().setText("TAB 1 ITEM"));
       // tabLayout.addTab(tabLayout.newTab().setText("TAB 2 ITEM"));
        tabLayout.getTabAt(0).setText("ĐỘ ẨM");
        tabLayout.getTabAt(1).setText("CÀI ĐẶT");
        tabLayout.getTabAt(2).setText("LỊCH SỬ");

        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_doam);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_configuration);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_history2);

        /// GỬI NHẬN DỮ LIỆU TỪ USB
       /* final Button bthuong = findViewById(R.id.button_id);
        bthuong.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
               // openUART("GET_MOISTURE");
            }
        });
        */




    }
    ///gửi nhận data từ microbit
    //NGYAF 17.01.2021
/*
    private void openUART(String temp){
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);

        if (availableDrivers.isEmpty()) {
            Log.d("UART", "UART is not available");

        }else {
            Log.d("UART", "UART is available");

            UsbSerialDriver driver = availableDrivers.get(0);
            UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
            if (connection == null) {

                PendingIntent usbPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(INTENT_ACTION_GRANT_USB), 0);
                manager.requestPermission(driver.getDevice(), usbPermissionIntent);

                manager.requestPermission(driver.getDevice(), PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0));

                return;
            } else {

                port = driver.getPorts().get(0);
                try {
                    port.open(connection);
                    port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
                    port.write((temp+"#").getBytes(),1000);

//  ngày 21.01.21 nhân dữ liệu từ điện thoại
                    SerialInputOutputManager usbIoManager = new SerialInputOutputManager(port, this);
                    Executors.newSingleThreadExecutor().submit(usbIoManager);
                } catch (Exception e) {

                }
            }
        }
    }


    @Override
    public void onNewData(byte[] data) {
        buffer += new String(data);
        Log.d("BUFFER---", buffer);
        // buffer ="!test A123#";
        int startIndex = buffer.indexOf("!");
        int endIndex = buffer.indexOf("#");
        if (startIndex >= 0 && endIndex >= 0 && endIndex > startIndex) {
            String value = buffer.substring(startIndex + 1, endIndex);
            Log.d("VALUE+++", value);
            TextView textView = findViewById(R.id.txt_serial_value);
            textView.setText(value);
            buffer = "";
        }
        if (buffer.length() >= 256) {
            buffer = buffer.substring(1);
        }
    }

    @Override
    public void onRunError(Exception e) {

    }
    */
}
