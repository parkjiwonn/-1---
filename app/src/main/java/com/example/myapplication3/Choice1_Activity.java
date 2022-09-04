package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Choice1_Activity extends AppCompatActivity {

    private Button btn_checklist;
    private Button btn_route;
    private Button btn_money;

    private Button btn_profile;
    //private Button btn_exam;
    private long backBtnTime =0;


    //메인 화면에서 뒤로가기 했을때 로그인화면으로 가지 않게끔 하기.
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        }
        else
        {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다." , Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice1);

        btn_checklist = findViewById(R.id.btn_checklist);
        btn_route = findViewById(R.id.btn_route);
        btn_money = findViewById(R.id.btn_money);

        btn_profile = findViewById(R.id.btn_profile);



        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choice1_Activity.this, Profile_Activity.class);
                startActivity(intent);
            }
        });



        btn_checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choice1_Activity.this, CheckList_Activity.class);
                startActivity(intent);
            }
        });


        btn_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choice1_Activity.this, PrePlan_Activity.class);
                startActivity(intent);
            }
        });

        btn_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choice1_Activity.this, PreMoney_Activity.class);
                startActivity(intent);
            }
        });









    }



}