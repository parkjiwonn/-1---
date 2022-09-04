package com.example.myapplication3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PrePlan_Activity extends AppCompatActivity {

    private Button btn_addpreplan;
    RecyclerView pRecyclerView = null;
    PlanAdapter pAdapter = null;
    ArrayList<planitem> pList;
    static int j =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_plan);

        pRecyclerView = findViewById(R.id.planRecyclerView);
        pList = new ArrayList<>();

        pAdapter = new PlanAdapter(pList);
        pRecyclerView.setAdapter(pAdapter);
        pRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        pAdapter.notifyDataSetChanged();



        btn_addpreplan = findViewById(R.id.btn_addpreplan);

        btn_addpreplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrePlan_Activity.this, AddPrePlan_Activity.class);
                startActivityForResult(intent, 3000);


            }
        });

        pAdapter.setOnItemClickListener(new PlanAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(View v, int ppos) {
                Intent intent = new Intent(PrePlan_Activity.this, RewritePlanActivity.class);
                intent.putExtra("plandate", pList.get(ppos).getPday());
                intent.putExtra("plancnt", pList.get(ppos).getPcnt());
                intent.putExtra("planspot", pList.get(ppos).getPspot());
                intent.putExtra("plancontent", pList.get(ppos).getPcontent());
                intent.putExtra("planaddress",pList.get(ppos).getPaddress());

                j = ppos;

                startActivityForResult(intent, 4000);
            }

            @Override
            public void onDeleteClick(View v, int ppos) {

                String pday = pList.get(ppos).getPday();
                String pcnt = pList.get(ppos).getPcnt();
                String pspot = pList.get(ppos).getPspot();
                String pcontent = pList.get(ppos).getPcontent();
                String paddress = pList.get(ppos).getPaddress();

                pList.remove(ppos);
                pAdapter.notifyItemRemoved(ppos);

            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 3000){
            if(resultCode == Activity.RESULT_OK){

                String pday = data.getStringExtra("date");
                String pcnt = data.getStringExtra("exam");
                String pspot = data.getStringExtra("spot");
                String pcontent = data.getStringExtra("plan");
                String paddress = data.getStringExtra("address");
                //String pimage = data.getStringExtra("path");
                //이미지 uri 갖고와야함.

                planitem pitem = new planitem(pday,pcnt,pspot,paddress,pcontent);
                pList.add(pitem);

                System.out.println(pList);

                pAdapter.notifyDataSetChanged();

            }
        }
        else if(requestCode ==4000){
            if(resultCode == Activity.RESULT_OK){

                String Eday = data.getStringExtra("plandate2");
                String Ecnt = data.getStringExtra("plancnt2");
                String Espot = data.getStringExtra("planspot2");
                String Econtent = data.getStringExtra("plancontent2");
                String Eaddress = data.getStringExtra("planaddress2");

                pList.get(j).setPday(Eday);
                pList.get(j).setPcnt(Ecnt);
                pList.get(j).setPspot(Espot);
                pList.get(j).setPcontent(Econtent);
                pList.get(j).setPaddress(Eaddress);

                pAdapter.notifyItemChanged(j,null);



            }
        }




    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PrePlan_Activity.this,Choice1_Activity.class);
        startActivity(intent);
        super.onBackPressed();
    }


    // 이메일 공유
    public void process (View view)
    {
        Intent intent = null, chooser = null;

        if(view.getId()==R.id.sendEmial)
        {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto :"));
            String[] to={"slidener@gamil.com", "dolphindelopers@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "여행 일정 공유 상황");
            intent.putExtra(Intent.EXTRA_TEXT, "어플리케이션으로부터 온 일정상황입니다.");
            intent.setType("message/rfc822");
            chooser = Intent.createChooser(intent, "send email");
            startActivity(chooser);

        }
    }

}