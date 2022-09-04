package com.example.myapplication3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_Activity extends AppCompatActivity {


    private Button btn_login;
    private Button btn_register;
    private EditText log_id, log_pass;
    static String logid;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_id=findViewById(R.id.log_id);
        log_pass=findViewById(R.id.log_pass);

        String shared = "userInfo";
        SharedPreferences preferences = getSharedPreferences(shared, MODE_PRIVATE );



        // 로그인 버튼 누르면 메인화면으로 이동.
        btn_login = findViewById(R.id.btn_login); //btn_login의 아이디를 찾아와라. 연결시켜주는 것.
        btn_login.setOnClickListener(new View.OnClickListener() { //btn_login을 클릭했을대 아래 구문을 실행하라는 뜻.
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login_Activity.this, StartActivity.class ); //첫번째 인자는 현재 액티비티, 두번째 인자는 넘어갈 액티비티를 넣어주면 된다.
                // 인텐트 객체 생성
                logid = log_id.getText().toString();
                //String logpass= log_pass.getText().toString();

                //회원가입 되어있지 않은 아이디와 비번을 입력했을 경우에 key값이 없으니 당연히 반환값이 없어서 response에 아무값도 나오지 않는것.

                String response = preferences.getString(logid,"");
                // 반환값이 없으면 ""이 출력된다.
                // logid 라는 키값을 가진 문자열 형태의 데이터를 response라는 문자열 변수에 넣는다.
                if(response.isEmpty())
                {
                    Toast.makeText(Login_Activity.this, "존재하지 않은 회원 정보 입니다.", Toast.LENGTH_SHORT).show();
                }

                try {
                    Log.e("login", response);
                    JSONObject infoJson = new JSONObject(response);
                    //JSONObject 객체 생성하고 문자열 response를 인자로 받는다.
                    Log.e("login22","login22");
                    String curr_pw = infoJson.get("Upass").toString();
                    // curr_pw 안에 json 객체에 있는 Upass라는 키의 값을 가져온다.
                    String curr_id = infoJson.get("Uemail").toString();
                    // curr_id 안에 json 객체에 있는 Uemail라는 키의 값을 가져온다.


                        Toast.makeText(Login_Activity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        startActivity(intent); //액티비티 이동해주는 구문
                        finish();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //(logid.equals(curr_id) && logpass.equals(curr_pw))

            }
        });

        //회원가입 버튼
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);
                finish();

            }
        });

    }





}