package com.example.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder> {

    private ArrayList<moneyitem> moneyData = null;

    public MoneyAdapter(ArrayList<moneyitem> data){
        moneyData = data;
    }

    interface OnItemClickListener{
        void onEditClick(View v, int pos); //수정
        void onDeleteClick(View v, int pos);//삭제
    }

    private OnItemClickListener moListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.moListener = listener;
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.moneyitem,parent,false);
        MoneyAdapter.ViewHolder vh = new MoneyAdapter.ViewHolder(view);

        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        moneyitem item = moneyData.get(position);

        holder.cnt.setText(item.getCnt());
        holder.day.setText(item.getDay());
        holder.price.setText(item.getPrice());
        holder.title.setText(item.getTitle());
        //holder.textview.setTag(position);

    }

    @Override
    public int getItemCount() {
        return moneyData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cnt;
        TextView day;
        TextView title;
        TextView price;


        Button edit_money, delete_money;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            cnt = itemView.findViewById(R.id.cnt_container);
            day = itemView.findViewById(R.id.day_container);
            title = itemView.findViewById(R.id.title_container);
            price = itemView.findViewById(R.id.price_container);

            edit_money = itemView.findViewById(R.id.edit_money);
            delete_money = itemView.findViewById(R.id.delete_money);

            edit_money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(moListener != null){
                            moListener.onEditClick(view,pos);
                        }
                    }
                }
            });

            delete_money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (moListener != null) {
                            moListener.onDeleteClick(view, pos);
                        }

                    }
                }
            });



        }
    }
}
