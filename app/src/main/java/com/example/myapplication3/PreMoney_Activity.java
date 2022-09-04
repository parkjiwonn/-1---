package com.example.myapplication3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PreMoney_Activity extends AppCompatActivity {

    private Button btn;
    RecyclerView moRecyclerView = null;
    MoneyAdapter moAdapter = null;
    ArrayList<moneyitem> moList;
    static int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_money);


        moRecyclerView = findViewById(R.id.moneyrecycle);
        moList = new ArrayList<>();

        moAdapter = new MoneyAdapter(moList);
        moRecyclerView.setAdapter(moAdapter);
        moRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));



        moAdapter.notifyDataSetChanged();


        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreMoney_Activity.this, AddPreMoney_Activity.class);
                startActivityForResult(intent, 1000);
                //finish 시키지 말고 그대로 남겨야 한다.
            }
        });

        moAdapter.setOnItemClickListener(new MoneyAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(View v, int pos) {

                Intent intent = new Intent(PreMoney_Activity.this, RewriteMoneyActivity.class);
                intent.putExtra("dayy",moList.get(pos).getDay());
                intent.putExtra("cntt",moList.get(pos).getCnt());
                intent.putExtra("titlee",moList.get(pos).getTitle());
                intent.putExtra("pricee",moList.get(pos).getPrice());

                i= pos;

                startActivityForResult(intent, 2000);


            }

            @Override
            public void onDeleteClick(View v, int pos) {

                String day = moList.get(pos).getDay();
                String cnt = moList.get(pos).getCnt();
                String title = moList.get(pos).getTitle();
                String price = moList.get(pos).getPrice();


                moList.remove(pos);
                moAdapter.notifyItemRemoved(pos);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1000){
            Log.e("requestcode1","첫번째분기");
            if(resultCode == Activity.RESULT_OK){

                Log.e("resultcode1","첫번째분기");
                String day = data.getStringExtra("moneydate");

                String cnt = data.getStringExtra("cnt");

                String title = data.getStringExtra("content");


                String price = data.getStringExtra("price");

                moneyitem item = new moneyitem(day, cnt, title, price);


                moList.add(item);

                System.out.println(moList);

                moAdapter.notifyDataSetChanged();



            }
        }
        else if(requestCode == 2000){
            Log.e("resultcode2","두번째분기");
            if(resultCode == Activity.RESULT_OK){
                Log.e("resultcode2","두번째분기");

                String day = data.getStringExtra("moneydate2");
                Log.e("날짜",day);

                String cnt = data.getStringExtra("cnt2");
                Log.e("일수",cnt);
                String title = data.getStringExtra("content2");
                Log.e("내용",title);
                String price = data.getStringExtra("price2");

                Log.e("가격",price);

                moList.get(i).setDay(day);
                moList.get(i).setCnt(cnt);
                moList.get(i).setTitle(title);
                moList.get(i).setPrice(price);
                //특정 포지션에 있는 값들을 수정해주는 것.

                //string= 다 넣어버리기. gson.toJson(moList);
                //shared edit해주고 다시 해주기.
                moAdapter.notifyItemChanged(i,null);



            }
        }
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PreMoney_Activity.this,Choice1_Activity.class);
        startActivity(intent);
        super.onBackPressed();
    }


}