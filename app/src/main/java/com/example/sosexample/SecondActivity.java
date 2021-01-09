package com.example.sosexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity{

    public  static final int RequestPermissionCode  = 1 ;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    Switch switch1;
    Button button3;
    Button showcontacts;
    Button sms;
    EditText text1;
    EditText text2;

    Mydatabase db;
    Cursor cursor;
    String msg=new String();
    String[] num=new String[10];

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        switch1 = (Switch) findViewById(R.id.switch1);
        button3 = (Button) findViewById(R.id.button3);
        showcontacts=(Button) findViewById(R.id.button2);
        sms=(Button) findViewById(R.id.button);

        button3.setVisibility(View.INVISIBLE);
        showcontacts.setVisibility(View.INVISIBLE);
        sms.setVisibility(View.INVISIBLE);

        db=new Mydatabase(this);





           switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                   if(isChecked==true)
                   {
                       Toast.makeText(getBaseContext(),"enabled",Toast.LENGTH_SHORT).show();
                       button3.setVisibility(View.VISIBLE);
                       showcontacts.setVisibility(View.VISIBLE);
                       sms.setVisibility(View.VISIBLE);
                   }
                   else if(isChecked==false)
                   {
                       button3.setVisibility(View.INVISIBLE);
                       showcontacts.setVisibility(View.INVISIBLE);
                       sms.setVisibility(View.INVISIBLE);
                   }

               }
           });


            button3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(SecondActivity.this, ThirdActivity.class);
                    startActivity(intent);

                }
            });

            showcontacts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                      Intent intent=new Intent(SecondActivity.this,FourthActivity.class);
                      startActivity(intent);


                }
            });

            sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  sendsms();
                  // startService(new Intent(getApplicationContext(), MyCallService.class));
                }
            });




        }

    private void sendsms()
    {
       /* Intent intent=new Intent(getApplicationContext(),FourthActivity.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        SmsManager msg=SmsManager.getDefault();
        smsManager.sendTextMessage(msg, null, "IN DANGER. HELP ME PLEASE...", null, null);

        Toast.makeText(getApplicationContext(),"message sent successfully",Toast.LENGTH_SHORT).show();*/
        cursor=db.ViewData();

        String sb=new String();


            while(cursor.moveToNext()) {
                msg = sb.concat(cursor.getString(1));

                    num[i]=msg;
                    i++;

                }




                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                    }
                }
                    else
                {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                    }

                }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    for(String number : num) {
                        smsManager.sendTextMessage(number, null, "IN DANGER. HELP ME PLEASE...", null, null);

                    }
                    Toast.makeText(getApplicationContext(), "message sent successfully", Toast.LENGTH_SHORT).show();
                }


                    else
                    {
                        Toast.makeText(getApplicationContext(),"SMS failed..",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        /*Intent homeIntent=new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);*/
        }
    }






