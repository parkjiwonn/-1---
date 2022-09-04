package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button btn_trip;
    private Button btn_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btn_main = findViewById(R.id.btn_main);
        btn_trip = findViewById(R.id.btn_trip);
       //btn_mypage = findViewById(R.id.btn_mypage);

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StartActivity.this, DiaryActivity.class);
                startActivity(intent);
                //finish();

            }
        });

        // 이번여행은 어디로 페이지로 이동함.
        btn_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, NextTrip_Activity.class);
                startActivity(intent);
                //finish();
            }
        });


    }
}