package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;
import java.util.ArrayList;

public class AddDiaryActivity extends AppCompatActivity {

    private TextView tx_startday;
    private TextView tx_finishday;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private DatePickerDialog.OnDateSetListener callbackMethod2;
    private Button btn_add;
    private EditText et_triptitle;
    private EditText et_where;
    static String title;
    private String where;
    private String start;
    private String finish;
    ArrayList<DiaryInfo> datalist ;
    ArrayList<DiaryInfo> datalist2;
    //ArrayList<DiaryInfo> dataList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);

        tx_finishday = findViewById(R.id.tx_finishday);
        tx_startday = findViewById(R.id.tx_startday);
        et_triptitle = findViewById(R.id.et_triptitle);
        et_where = findViewById(R.id.et_where);

        this.InitializeView2();
        this.InitializeListener2();

        this.InitializeView();
        this.InitializeListener();




        SharedPreferences preferences = getSharedPreferences("SpotInfo", MODE_PRIVATE );
        //공유 환경설정의 핸들 가져오기.
        //SpotInfo라는 파일 이름을 가진 shared preference 생성.
        SharedPreferences.Editor editor = preferences.edit();
        //먼저 데이터를 기록하기 위해 shared preferences 인스턴스 얻어와야 함.
        //sharedpreference 안에 editor 연결시켜줘야함.
        //왜? 공유 환경설정 파일에 쓰기 위해 edit 호출해 editor 만들어줘야 함.
        Gson gson = new GsonBuilder().create();


        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDiaryActivity.this, DiaryActivity.class);

                //여행 제목, 여행국가및지역, 시작 날짜, 종료 날짜 변수를 선언하고 입력된 값을 변수안에 넣기.
                title = et_triptitle.getText().toString();
                where = et_where.getText().toString();
                start = tx_startday.getText().toString();
                finish = tx_finishday.getText().toString();


                DiaryInfo diaryInfo1 = new DiaryInfo(title, where, start, finish);
                Log.e("diaryinfo1", diaryInfo1.getTitle());
                Log.e("diaryinfo1", diaryInfo1.getPlace());
                Log.e("diaryinfo1", diaryInfo1.getStart());
                Log.e("diaryinfo1", diaryInfo1.getFinish());
                // 객체에 정보들이 잘 담기는지 확인



                //회원가입 하고 바로 여행지 기본정보 추가하려고 할때 shared preference에는 회원가입의 아이디 키값이 없는 상태.
                //회원가입을 하고 여행지 기본정보 추가페이지에 즐어오면

//                if(!Register_Activity.id.equals(""))
//                {

                  //회원가입하면서 spotinfo 에 저장공간이 이미 생김.

//                    Login_Activity.logid = Register_Activity.id;
                // 그다음 회원가입한 아이디와 로그인해서 들어오는 id가 같아야 한다.

//                }


                //DiaryInfo를 담는 arraylist를 만들어줌.
                    String datalist_s = preferences.getString(Login_Activity.logid, "null");
                    //shared preference에 저장되어있는 value값을 불러옴.
                    Log.e("datalist_s",datalist_s);

                    if(datalist_s == "")
                    {
                        //로그인 아이디와 같은 키값이 가진 데이터가 없는 경우
                        //새로운 데이터를 저장할수있게 해야한다.
                        //datalist2 = new ArrayList<>();


                    }
                    else{
                        // 로그인 아이디와 같은 키값이 가진 데이터가 있는 부분.
                        //기존에 있어야하는 부분에서 불러오고 쌓는부분

                        datalist = new ArrayList<>();

                        datalist = gson.fromJson(datalist_s, new TypeToken<ArrayList<DiaryInfo>>(){}.getType());
                        //shared prefernced에 있는 정보를 arraylist로 불러오기.
                        //gson.fromJson 불러오기.

                        // datalist_s에 null값이 들어있을 때 그 null값을 처리해주는 부분에서 오류가 자꾸 생김 예외 처리를 해줘야 함.

                        Log.e("datalist", datalist.toString());

                        datalist.add(diaryInfo1);



                        //이전에 저장된 정보를 list로 불러온 list에 새로운 diaryinfo1을 추가해주기.

                        Log.e("datalist", datalist.toString());

                        editor.putString(Login_Activity.logid, gson.toJson(datalist));
                        // 추가한 arraylist를 문자열로 바꿔서 다시 저장하기.

                        editor.commit();
                    }



                startActivity(intent);
                finish();


            }
        });


    }

    //캘린더 다이얼로그 띄우기. 끝 날짜
    public void InitializeView()
    {
        tx_finishday = (TextView)findViewById(R.id.tx_finishday);
    }

    public void InitializeListener()
    {
        callbackMethod2 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear , int dayOfMonth)
            {
                monthOfYear += 1;
                tx_finishday.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");


            }
        };
    }

    //캘린더 다이얼로그 띄우기. 끝 날짜
    public void InitializeView2()
    {
        tx_startday = (TextView)findViewById(R.id.tx_startday);
    }

    public void InitializeListener2()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear , int dayOfMonth)
            {
                monthOfYear += 1;
                tx_startday.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");

            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2019, 5, 24);

        dialog.show();
    }


    public void OnClickHandler2(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod2, 2019, 5, 24);

        dialog.show();
    }



}