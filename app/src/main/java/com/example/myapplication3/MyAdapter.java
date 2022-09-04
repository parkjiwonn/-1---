package com.example.myapplication3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//tripadapter
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Item> itemList;
    private Context context;
    private Intent intent;
    ImageView imageView;

    public MyAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_view,parent,false);

        return new ViewHolder(view);
    }//아이템 뷰로 사용 될 xml inflate 시킴


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.imageView.setImageResource(itemList.get(position).getItem_image());
        holder.textView.setText(itemList.get(position).getItem_title());   //viewHolder 객체

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), ItemActivity.class);
                intent.putExtra("number", position);
                intent.putExtra("title",itemList.get(position).getItem_title());
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "클릭 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }//뷰안에 필요한 정보 채움

    @Override
    public int getItemCount() {//data set 전체크기
        return itemList.size();
    }


    //ViewHolder 클래스
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            textView = itemView.findViewById(R.id.item_text); //파라메타 id 찾기
        }
    }
}
