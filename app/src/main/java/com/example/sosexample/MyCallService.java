package com.example.sosexample;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;



public class MyCallService extends Service {

    public MyCallService()
    {}
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);

        final BroadcastReceiver mReceiver = new MyReceiver();
        registerReceiver(mReceiver, filter);
        return super.onStartCommand(intent, flags, startId);
    }

}
