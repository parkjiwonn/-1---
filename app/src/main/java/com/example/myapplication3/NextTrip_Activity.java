package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class NextTrip_Activity extends AppCompatActivity {

    public RecyclerView recyclerView;
    ArrayList<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_trip);

        recyclerView = findViewById(R.id.recycler_view);

//      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); //상하
//      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
//                LinearLayoutManager.HORIZONTAL,false); //좌우
//      RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3); //3개 상하 나오게
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2,
                LinearLayoutManager.HORIZONTAL,false); //2개 좌우 나오게

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter(insertItem()));

    }

    ArrayList insertItem()
    {
        itemList = new ArrayList<>();
        Item item1 = new Item(R.drawable.banner, "인천은 어떠신가요?");
        Item item2 = new Item(R.drawable.banner, "서울은 어떠신가요?");
        Item item3 = new Item(R.drawable.banner , "수원은 어떠신가요?");
        Item item4 = new Item(R.drawable.banner, "강릉은 어떠신가요?");
        Item item5 = new Item(R.drawable.banner, "부산은 어떠신가요?");
        Item item6 = new Item(R.drawable.banner, "전주은 어떠신가요?");
        Item item7 = new Item(R.drawable.banner, "경주은 어떠신가요?");
        Item item8 = new Item(R.drawable.banner, "순천은 어떠신가요?");
        Item item9 = new Item(R.drawable.banner, "춘천은 어떠신가요?");

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        itemList.add(item6);
        itemList.add(item7);
        itemList.add(item8);
        itemList.add(item9);

        return itemList;
    }

}