package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class RewritePlanActivity extends AppCompatActivity {

    private Button btn_editplan;
    private TextView tx_editdate;
    private EditText et_editcnt;
    private EditText et_editspot;
    private EditText et_editcontent;
    private EditText et_editaddress;
    private DatePickerDialog.OnDateSetListener callbackMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewrite_plan);

        this.InitializeView();
        this.InitializeListener();

        tx_editdate = findViewById(R.id.tx_editdate);
        Intent intent = getIntent();
        String str = intent.getStringExtra("plandate");
        tx_editdate.setText(str);

        et_editcnt = findViewById(R.id.et_editcnt);
        Intent intent2 = getIntent();
        String str2 = intent2.getStringExtra("plancnt");
        et_editcnt.setText(str2);


        et_editspot = findViewById(R.id.et_editspot);
        Intent intent3 = getIntent();
        String str3 = intent3.getStringExtra("planspot");
        et_editspot.setText(str3);

        et_editcontent =findViewById(R.id.et_editcontent);
        Intent intent4 = getIntent();
        String str4 = intent4.getStringExtra("plancontent");
        et_editcontent.setText(str4);

        et_editaddress =findViewById(R.id.et_editaddress);
        Intent intent5 = getIntent();
        String str5 = intent5.getStringExtra("planaddress");
        et_editaddress.setText(str5);

        btn_editplan = findViewById(R.id.btn_editplan);
        btn_editplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RewritePlanActivity.this, PrePlan_Activity.class);

                intent.putExtra("plandate2", tx_editdate.getText().toString());
                intent.putExtra("plancnt2", et_editcnt.getText().toString());
                intent.putExtra("planspot2", et_editspot.getText().toString());
                intent.putExtra("plancontent2", et_editcontent.getText().toString());
                intent.putExtra("planaddress2", et_editaddress.getText().toString());

                setResult(RESULT_OK, intent);
                finish();

            }
        });




    }

    //캘린더 다이얼로그 띄우기.
    public void InitializeView()
    {
        tx_editdate = (TextView)findViewById(R.id.tx_editdate);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear += 1;
                tx_editdate.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2019, 5, 24);

        dialog.show();
    }
}