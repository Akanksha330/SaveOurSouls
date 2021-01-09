package com.example.sosexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class MyReceiver extends BroadcastReceiver {
    static int countPowerOff=0;
    public static boolean wasScreenOn = true;
    private static final int code=1;


    @Override
    public void onReceive(Context context, Intent intent){
        Log.v("onReceive","Power button is pressed");

        Toast.makeText(context,"power button clicked",Toast.LENGTH_SHORT).show();

        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            countPowerOff++;
            Toast.makeText(context,"Screen off",Toast.LENGTH_SHORT).show();
            if(countPowerOff==2){




            }
            }
            else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
                Log.e("LOB","userpresent");
                Log.e("LOB","wasScreenOn"+wasScreenOn);
            }

        }
    }



