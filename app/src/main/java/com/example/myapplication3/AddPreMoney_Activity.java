package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class AddPreMoney_Activity extends AppCompatActivity {

    private TextView tx_premoneydate;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private Button btn_addmoney;
    private EditText et_cnt;
    private EditText et_content;
    private EditText et_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pre_money);

        this.InitializeView();
        this.InitializeListener();

        tx_premoneydate = findViewById(R.id.tx_premoneydate);
        btn_addmoney = findViewById(R.id.btn_addmoney);
        et_cnt = findViewById(R.id.et_cnt);
        et_content = findViewById(R.id.et_content);
        et_price = findViewById(R.id.et_price);


        // 경비 추가하기 버튼
        btn_addmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPreMoney_Activity.this, PreMoney_Activity.class);

                intent.putExtra("cnt", et_cnt.getText().toString());
                //intent 에 값 저장.
                //로그 확인 , 값이 담겼는지
                Log.d("cnt",et_cnt.getText().toString());



                intent.putExtra("moneydate",tx_premoneydate.getText().toString());
                Log.d("moneydate",tx_premoneydate.getText().toString());

                intent.putExtra("content", et_content.getText().toString());
                Log.d("content",et_content.getText().toString());

                intent.putExtra("price", et_price.getText().toString());
                Log.d("price",et_price.getText().toString());

                setResult(RESULT_OK,intent);
                //startActivity(intent);
                finish();
            }
        });

    }

    //캘린더 다이얼로그 띄우기.
    public void InitializeView()
    {
        tx_premoneydate = (TextView)findViewById(R.id.tx_premoneydate);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear , int dayOfMonth)
            {
                monthOfYear += 1;
                tx_premoneydate.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2019, 5, 24);

        dialog.show();
    }
}

