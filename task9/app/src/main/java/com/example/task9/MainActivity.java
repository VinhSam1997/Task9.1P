package com.example.task9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Addnewplace, Showallplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Addnewplace = findViewById(R.id.addnewplace);
        Showallplace = findViewById(R.id.showallplace);

        Addnewplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newplaceactivity = new Intent(MainActivity.this, Newplace.class);
                startActivity(newplaceactivity);
            }
        });

        Showallplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allplaceactivity = new Intent(MainActivity.this, allplaceactivity.class);
                startActivity(allplaceactivity);
            }
        });
    }


}