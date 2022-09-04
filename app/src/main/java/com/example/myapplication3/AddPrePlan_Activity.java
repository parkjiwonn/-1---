package com.example.myapplication3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;


public class AddPrePlan_Activity extends AppCompatActivity  {

    private Button btn_move;
    private EditText et_precnt;
    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private EditText et_spot;
    private EditText et_plan;
    private EditText et_address;
    ImageView imageView;

    String imagePath ="";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat imageDate = new SimpleDateFormat("yyyyMMdd_HHmmss");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pre_plan);
        this.InitializeView();
        this.InitializeListener();

        et_precnt = findViewById(R.id.et_precnt000);
        btn_move = findViewById(R.id.btn_move);
        textView_Date = findViewById(R.id.textView_date);
        et_spot = findViewById(R.id.et_spot);
        et_plan = findViewById(R.id.et_plan);
        et_address = findViewById(R.id.et_address);
        imageView = findViewById(R.id.imageView);



        //갤러리에서 이미지 업로드하기 버튼.
        Button imageButton = findViewById(R.id.btn_gallery);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,0);
            }
        });




        // 추가 버튼 눌렀을때 이벤트

        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddPrePlan_Activity.this, PrePlan_Activity.class);
                //화면 전환

                intent.putExtra("exam", et_precnt.getText().toString());
                //intent 에 값 저장.
                intent.putExtra("date",textView_Date.getText().toString());
                intent.putExtra("spot", et_spot.getText().toString());
                intent.putExtra("plan", et_plan.getText().toString());
                intent.putExtra("address", et_address.getText().toString());


                if(imagePath.length()>0){
                   // intent = new Intent(getApplicationContext(), PrePlan_Activity.class);
                    intent.putExtra("path", imagePath);//string값을 받아온다.
                }


                setResult(RESULT_OK, intent);
                finish();

            }
        });


    }

    // 날짜 다이얼로그 구현

    public void InitializeView()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                textView_Date.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2019, 5, 24);

        dialog.show();
    }

//    @Override
//    public void onClick(View view) {
//
//    }


    // 갤러리에서 이미지 불러오기.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if(resultCode == Activity.RESULT_OK){
            if(requestCode ==0){
                imagePath = data.getDataString();
            }
        }
        
        if(imagePath.length()>0){
            Glide.with(this).load(imagePath).into(imageView);

        }
        
        
        
//        if(requestCode == 0){
//            if(resultCode == RESULT_OK){
////                Glide.with(getApplicationContext()).load(data.getData()).override(500,500).into(imageView);
//            }
//        }
        
        
    }


}