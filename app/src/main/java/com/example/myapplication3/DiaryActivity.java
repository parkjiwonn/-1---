package com.example.myapplication3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DiaryActivity extends AppCompatActivity {

    private Button btn_AddDiary;
    RecyclerView DRecyclerView = null;
    DiaryAdapter DAdapter = null;
    ArrayList<DiaryData> DList;
    private Context dContext;
    static int d=0;//position 값 받아오기.
    String CurPost ;

    Gson gson = new Gson();
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        DRecyclerView = findViewById(R.id.DiaryRecyclerview);
        DList = new ArrayList<>();

        DAdapter = new DiaryAdapter(DList);
        DRecyclerView.setAdapter(DAdapter);
        DRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        // 리사이클러뷰 어댑터 연결, 레이아웃매니저 끝.
        //DAdapter.notifyDataSetChanged();

        // 액티비티가 oncreate 될때 추가한 정보들이 리사이클러뷰에 뿌려져야 한다.

        preferences =  getSharedPreferences("SpotInfo", MODE_PRIVATE );
        //activity 가 실행되야 shared를 써야 한다.
        //실행이 안됐는데 shared를 get해오려고 하니까 안된다.

//        if(!Register_Activity.id.equals(""))
//        {
//            Login_Activity.logid = Register_Activity.id;
//        }

        String json = preferences.getString(Login_Activity.logid, "fail");
        Log.e("json 열기", json);

        if(json == "fail")
        {

            // sharedpreference에 로그인 아이디 키값을 가진 데이터가 없을때 그냥 넘어가기.
        }
        else {
            // sharedpreference에 로그인 아이디 키값을 가진 데이터가 있다면.

            if(json != ""){


                ArrayList<DiaryInfo> arrayList;
                Type type =new TypeToken<ArrayList<DiaryInfo>>(){}.getType();
                arrayList = gson.fromJson(json,type);

                System.out.println(arrayList.get(0).getTitle());
                // shared preference에 저장되어있는 데이터를 arraylist에 담는다.


                for(int i =0; i<arrayList.size();i++)
                {
                    //arrayList의 사이즈만큼 반복문을 돌린다.
                    Log.e("for", arrayList.get(i).toString());

                    String Title = arrayList.get(i).getTitle();
                    String Where = arrayList.get(i).getPlace();
                    String Start = arrayList.get(i).getStart();
                    String finish = arrayList.get(i).getFinish();
                    System.out.println(arrayList.get(i).getTitle());
                    Log.e("title","dd");

                    DList.add(new DiaryData(Title, Where, Start,finish));
                    // 리사이클러 뷰의 DList에 넣는다.

                }

                DAdapter.notifyDataSetChanged();
            }
            else{

            }


        }




        btn_AddDiary = findViewById(R.id.btn_AddDiary);
        btn_AddDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, AddDiaryActivity.class);
                startActivity(intent);
            }
        });

        DAdapter.setOnItemClickListener(new DiaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int dpos) {
                String name = DList.get (dpos).getTitle ();
                Toast.makeText (getApplicationContext(), name+"의 다이어리로 이동합니다.", Toast.LENGTH_SHORT).show ();
                Intent intent = new Intent(DiaryActivity.this, PrePlan_Activity.class);
                startActivity(intent);
                //엑티비티에서 커스텀 리스너 객체를 생성하고 어댑터에 전달한다.
            }

            @Override
            public void onEditClick(View v, int dpos) {
                Intent intent = new Intent(DiaryActivity.this, RewirteDiaryActivity.class);
                d = dpos;

                intent.putExtra("title",DList.get(d).getTitle());
                intent.putExtra("where",DList.get(d).getPlace());
                intent.putExtra("start",DList.get(d).getStart());
                intent.putExtra("finish",DList.get(d).getFinish());

                startActivityForResult(intent, 2000);



            }

            @Override
            public void onDeleteClick(View v, int dpos) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DiaryActivity.this);
                // 현재 액티비티로 해야 한다.

                builder.setTitle("삭제 확인 다이얼로그");
                builder.setMessage("삭제하시겠습니까?");

                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        DList.remove(dpos);
                        DAdapter.notifyItemRemoved(dpos);



                    }
                });

                builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
                //builder.show를 해야 한다.


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2000)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                String etitle = data.getStringExtra("title");
                String ewhere = data.getStringExtra("where");
                String estart = data.getStringExtra("start");
                String efinish = data.getStringExtra("finish");

                DList.get(d).setTitle(etitle);
                DList.get(d).setPlace(ewhere);
                DList.get(d).setStart(estart);
                DList.get(d).setFinish(efinish);

                DAdapter.notifyItemChanged(d,null);
                CurPost = gson.toJson(DList);

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Login_Activity.logid, CurPost);
                editor.commit();

            }
        }
    }
}