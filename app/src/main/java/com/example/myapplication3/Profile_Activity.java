package com.example.myapplication3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class Profile_Activity extends AppCompatActivity {

    private TextView tx_id;
    private TextView tx_name;
    private TextView tx_age;
    String imagePath = "";


    public static final int CAMERA_ACTION_CODE = 1;
    ImageView imageView;
    Button takePhoto;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tx_id = findViewById(R.id.tx_id);


        tx_name = findViewById(R.id.tx_name);


        tx_age = findViewById(R.id.tx_age);


        String shared = "userInfo";
        SharedPreferences preferences = getSharedPreferences(shared, MODE_PRIVATE );

        Gson gson = new Gson();
        String json = preferences.getString(Register_Activity.id, "fail");
        UserInfo obj = gson.fromJson(json, UserInfo.class);

        // sharedpreference에 저장된 json 데이터를 문자열 타입의 email, name 변수, 정수 타입의 age 변수에 담은다음
        // textview에 뿌려준다.

        tx_id.setText(obj.getUemail());
        tx_name.setText(obj.getUname());
        int value = obj.getUage();
        tx_age.setText(String.valueOf(value) + "세");

        imageView = findViewById(R.id.userImg);
        takePhoto = findViewById(R.id.btn_camera);


        // 카메라로 이미지 찍고 업로드하기.
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageView.setImageBitmap(bitmap);
                }
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // startActivityForResult(intent, CAMERA_ACTION_CODE);
                    activityResultLauncher.launch(intent);
                } else {
                    Toast.makeText(Profile_Activity.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // 갤러리에서 이미지 업로드하기.
        Button imageButton = findViewById(R.id.btn_gall);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 0);
            }
        });






    }

    //갤러리에 이미지 업로드 하기.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) {
                imagePath = data.getDataString();
            }
        }

        if (imagePath.length() > 0) {
            Glide.with(this).load(imagePath).into(imageView);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Profile_Activity.this, StartActivity.class);
        startActivity(intent);

    }
}
