package com.example.myapplication3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckList_Activity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private Context mContext;
    private ArrayList<Data> mArrayList;
    //사용자 데이터 리스트
    private Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText edit_name, edit_number;
    private Button btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_check_list);
        mContext = CheckList_Activity.this;
        edit_name = findViewById (R.id.edit_name);
        edit_number = findViewById (R.id.edit_number);
        btn_save = findViewById (R.id.btn_save);
        mRecyclerView = findViewById (R.id.recycler);




        initRecyclerView ();

        //버튼 클릭이벤트
        //물건과 수량을 입력한 후 버튼을 클릭하면 어레이리스트에 데이터를 담고 리사이클러뷰에 띄운다.
        btn_save.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (edit_name.getText ().length () == 0 && edit_number.getText ().length () == 0) {
                    Toast.makeText (mContext, "물건과 수량을 입력해주세요", Toast.LENGTH_SHORT).show ();
                } else {
                    String name = edit_name.getText ().toString ();
                    String number = edit_number.getText ().toString ();
                    //해당 액티비티에서 바로 이름이랑 수 입력한걸 name, number 에 담고
                    edit_name.setText ("");
                    edit_number.setText ("");
                    //edit_name, edit_number에 공백을 넣어 텍스트 변경시킨다.

                    Data data = new Data (name, number);
                    // data 객체 생성.

                    mArrayList.add(data);
                    //add()를 이용해서 arraylist에 아이템을 추가하는 코드

                    mAdapter.notifyItemInserted (mArrayList.size () - 1);



                }
            }
        });

        //리사이클러뷰 클릭 이벤트
        mAdapter.setOnItemClickListener (new Adapter.OnItemClickListener () {

            //아이템 클릭시 토스트메시지0
            @Override
            public void onItemClick(View v, int position) {
                String name = mArrayList.get (position).getName ();
                String number = mArrayList.get (position).getNumber ();
                Toast.makeText (mContext, "물건 : " + name + "\n수량 : " + number, Toast.LENGTH_SHORT).show ();
                //엑티비티에서 커스텀 리스너 객체를 생성하고 어댑터에 전달한다.

            }

            //수정
            @Override
            public void onEditClick(View v, int position) {
                String name = mArrayList.get (position).getName ();
                String number = mArrayList.get (position).getNumber ();

                editItem (name, number, position);
            }

            //삭제
            @Override
            public void onDeleteClick(View v, int position) {
                String name = mArrayList.get (position).getName ();
                String number = mArrayList.get (position).getNumber ();


                mArrayList.remove (position);
                mAdapter.notifyItemRemoved (position);
            }

        });

    }



    //리사이클러뷰
    private void initRecyclerView() {
        //레이아웃메니저는 리사이클러뷰의 항목 배치를 어떻게 할지 정하고, 스크롤 동작도 정의한다.
        //수평/수직 리스트 LinearLayoutManager
        //그리드 리스트 GridLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager (mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager (layoutManager);

        mArrayList = new ArrayList<> ();
        mAdapter = new Adapter (mContext, mArrayList);
        mRecyclerView.setAdapter (mAdapter);


    }

    //AlertDialog 를 사용해서 데이터를 수정한다.
    private void editItem(String name, String number, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder (this);
        View view = LayoutInflater.from (this).inflate (R.layout.dialog, null, false);
        builder.setView (view);

        final AlertDialog dialog = builder.create ();

        final Button btn_edit = view.findViewById (R.id.btn_edit);
        final Button btn_cancel = view.findViewById (R.id.btn_cancel);
        final EditText edit_name = view.findViewById (R.id.edit_editName);
        final EditText edit_number = view.findViewById (R.id.edit_editNumber);

        edit_name.setText (name);
        edit_number.setText (number);


        // 수정 버튼 클릭
        //어레이리스트 값을 변경한다.
        btn_edit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String editName = edit_name.getText ().toString ();
                String editNumber = edit_number.getText ().toString ();
                mArrayList.get (position).setName (editName);
                mArrayList.get (position).setNumber (editNumber);



                mAdapter.notifyItemChanged (position);
                dialog.dismiss ();
            }
        });

        // 취소 버튼 클릭
        btn_cancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                dialog.dismiss ();
            }
        });

        dialog.show ();
    }




    }






