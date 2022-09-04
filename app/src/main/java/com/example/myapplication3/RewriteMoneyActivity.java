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

public class RewriteMoneyActivity extends AppCompatActivity {

    private Button btn_editmoney;
    private TextView tx_editmoneydate;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private EditText et_editcnt;
    private EditText et_editcontent;
    private EditText et_editprice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewrite_money);

        this.InitializeView();
        this.InitializeListener();

        tx_editmoneydate = findViewById(R.id.tx_editmoneydate);
        Intent intent = getIntent();
        String str = intent.getStringExtra("dayy");
        tx_editmoneydate.setText(str);

        et_editcnt = findViewById(R.id.et_editcnt);
        Intent intent1 = getIntent();
        String str2 = intent1.getStringExtra("cntt");
        et_editcnt.setText(str2);

        et_editcontent = findViewById(R.id.et_editcontent);
        Intent intent2 = getIntent();
        String str3 =intent2.getStringExtra("titlee");
        et_editcontent.setText(str3);

        et_editprice = findViewById(R.id.et_editprice);
        Intent intent3 = getIntent();
        String str4 = intent3. getStringExtra("pricee");
        et_editprice.setText(str4);





        btn_editmoney = findViewById(R.id.btn_editmoney);
        btn_editmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RewriteMoneyActivity.this, PreMoney_Activity.class);

                intent.putExtra("cnt2",et_editcnt.getText().toString());
                Log.d("cnt2",et_editcnt.getText().toString());

                intent.putExtra("moneydate2",tx_editmoneydate.getText().toString());
                Log.d("moneydate2",tx_editmoneydate.getText().toString());

                intent.putExtra("content2",et_editcontent.getText().toString());
                Log.d("content2",et_editcontent.getText().toString());

                intent.putExtra("price2",et_editprice.getText().toString());
                Log.d("price2",et_editprice.getText().toString());




                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    //캘린더 다이얼로그 띄우기.
    public void InitializeView()
    {
        tx_editmoneydate = (TextView)findViewById(R.id.tx_editmoneydate);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                tx_editmoneydate.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2019, 5, 24);

        dialog.show();
    }
}