package com.example.sosexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

       final Mydatabase db=new Mydatabase(this);

        final TextView textView=(TextView) findViewById(R.id.textView);
        Button remove=(Button) findViewById(R.id.remove);

        Cursor  cursor=db.ViewData();

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("NAME\t\t\t\t\t\t\tNUMBER\n\n");

        while(cursor.moveToNext())
        {
            stringBuilder.append(cursor.getString(0)+ "\t\t\t\t\t\t  "+cursor.getString(1)+"\n");
        }
        textView.setText(stringBuilder);


          remove.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  textView.setText("");
                  db.deletedata();

              }
          });


    }


}
