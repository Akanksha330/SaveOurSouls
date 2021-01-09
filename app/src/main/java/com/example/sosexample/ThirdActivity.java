package com.example.sosexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;

import static com.example.sosexample.SecondActivity.RequestPermissionCode;

public class ThirdActivity extends AppCompatActivity  {


    private static final int PICK_CONTACT = 1;
    ArrayList<String> StoreContacts ;
    ArrayAdapter<String> arrayAdapter ;

    Cursor cursor ;
    String name, phonenumber ;

    String val;
    int count=0;
    Mydatabase db;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        db=new Mydatabase(this);
        Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(pickContact,PICK_CONTACT);


         EnableRuntimePermission();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_CONTACT:
                if (resultCode == RESULT_OK) {
                    Uri contactUri = data.getData();
                    Cursor nameCursor = getContentResolver().query(contactUri, null, null, null, null);
                    if (nameCursor.moveToFirst()) {
                        String name = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String number = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        ((EditText) findViewById(R.id.editText)).setText(name);
                        ((EditText) findViewById(R.id.editText2)).setText(number);
                        boolean i;
                        if(count<3) {
                            if (db.InsertDB(name, number)) {
                                i = true;
                                count++;
                            }
                        }
                        else i = false;


                        nameCursor.close();
                    }
                }
                break;


        }
    }

   /*private void GetContactsIntoArrayList() {



        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            StoreContacts.add(name + " "  + ":" + " " + phonenumber);

        }

        cursor.close();


    }*/



    public void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                ThirdActivity.this,
                Manifest.permission.READ_CONTACTS))
        {

            Toast.makeText(ThirdActivity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(ThirdActivity.this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }





}
