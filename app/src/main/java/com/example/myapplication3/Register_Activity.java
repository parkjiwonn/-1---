package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Register_Activity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_name;
    private EditText et_age;
    private Button btn_register;
    private Button btn_validate;
    private EditText et_pass;
    private EditText et_passck;
    static String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id = findViewById(R.id.et_id);
        et_pass =findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        btn_register = findViewById(R.id.btn_register);
        et_pass = findViewById(R.id.et_pass);
        et_passck = findViewById(R.id.et_passck);
        btn_validate =findViewById(R.id.btn_validate);

        String shared = "userInfo";
        //파일(테이블 이름)선언 해주기.
        SharedPreferences preferences = getSharedPreferences(shared, MODE_PRIVATE );
        SharedPreferences preferences2 = getSharedPreferences("SpotInfo", MODE_PRIVATE );
        // 회원가입을 하게 되면 회원가입시 기입한 id로 spot info에 저장 공간을 만든다.

        //공유 환경설정의 핸들 가져오기.
        SharedPreferences.Editor editor = preferences.edit();
        SharedPreferences.Editor editor2 = preferences2.edit();
        //먼저 데이터를 기록하기 위해 shared preferences 인스턴스 얻어와야 함.
        //sharedpreference 안에 editor 연결시켜줘야함.
        //왜? 공유 환경설정 파일에 쓰기 위해 edit 호출해 editor 만들어줘야 함.


        Gson gson = new GsonBuilder().create();

        //Gson 객체 생성

        //userinfo 객체를 json 문자열로 변한.
        //userinfo 객체는 유저가 입력한 email(즉 id), namd, age 정보를 가지고 있다.
        //Json 객체가 생성된것.



        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valiId = et_id.getText().toString();
                
                String response = preferences.getString(valiId, "");
                
                if(response.isEmpty())
                {
                    Toast.makeText(Register_Activity.this, "회원가입 가능", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Register_Activity.this, "이미 존재하는 아이디 입니다", Toast.LENGTH_SHORT).show();
                }


            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(Register_Activity.this, Profile_Activity.class);

                id = et_id.getText().toString();
                String pass = et_pass.getText().toString();
                String passck = et_passck.getText().toString();
                String name = et_name.getText().toString();
                String age = et_age.getText().toString();

                if(id.length() == 0)
                {
                    Toast.makeText(Register_Activity.this, "Email을 입력하세요", Toast.LENGTH_SHORT).show();
                    et_id.requestFocus();
                    return;
                }

                if(pass.length() == 0)
                {
                    Toast.makeText(Register_Activity.this, "비밀번호을 입력하세요", Toast.LENGTH_SHORT).show();
                    et_pass.requestFocus();
                    return;
                }

                if(passck.length()==0)
                {
                    Toast.makeText(Register_Activity.this, "비밀번호 확인을 입력하세요", Toast.LENGTH_SHORT).show();
                    et_passck.requestFocus();
                    return;
                }

                if(!pass.equals(passck))
                {
                    Toast.makeText(Register_Activity.this, "비밀번호가 맞지 않습니다", Toast.LENGTH_SHORT).show();
                    et_pass.setText("");
                    et_passck.setText("");
                    et_pass.requestFocus();
                    return;
                }

                if(age.length() == 0){
                    Toast.makeText(Register_Activity.this, "나이를 입력하세요", Toast.LENGTH_SHORT).show();
                    et_age.requestFocus();
                    return;
                }

                if(name.length() == 0)
                {
                    Toast.makeText(Register_Activity.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    et_name.requestFocus();
                    return;
                }

                if(id.length() != 0 && pass.equals(passck))
                {
                    int intage = Integer.parseInt(et_age.getText().toString());
                    UserInfo userInfo = new UserInfo(id, pass, name, intage);
                    String userJson = gson.toJson(userInfo);


                    editor.putString(id, userJson);

                    //KEY 값이 유저의 이메일 값이 된다.
                    preferences2.getString("SpotInfo","null");
                    editor2.putString(id, "");

                    editor.commit();
                    editor2.commit();


                    startActivity(intent2);
                    finish();
                }



            }
        });


    }
}