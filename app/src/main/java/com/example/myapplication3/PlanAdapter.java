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

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private ArrayList<planitem> planData =null;

    public PlanAdapter(ArrayList<planitem> pdata) { planData = pdata; }

    interface OnItemClickListener{
        void onEditClick(View v, int ppos); //수정
        void onDeleteClick(View v, int ppos);//삭제
    }

    private PlanAdapter.OnItemClickListener pListener = null;

    public void setOnItemClickListener(PlanAdapter.OnItemClickListener listener){
        this.pListener = listener;
    }


    @NonNull
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.planitem, parent,false);
        PlanAdapter.ViewHolder vh = new PlanAdapter.ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, int position) {

        planitem item = planData.get(position);

        holder.pday.setText(item.getPday());
        holder.pcnt.setText(item.getPcnt());
        holder.pspot.setText(item.getPspot());
        holder.paddress.setText(item.getPaddress());
        holder.pcontent.setText(item.getPcontent());




    }

    @Override
    public int getItemCount() {
        return planData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pday;
        TextView pcnt;
        TextView pspot;
        TextView paddress;
        TextView pcontent;
        //ImageView pimage;

        Button edit_plan, delete_plan;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            pday = itemView.findViewById(R.id.planday_container);
            pcnt = itemView.findViewById(R.id.plancnt_container);
            pspot = itemView.findViewById(R.id.spot_container);
            paddress = itemView.findViewById(R.id.address_container);
            pcontent = itemView.findViewById(R.id.content_container);
            //pimage =itemView.findViewById(R.id.image_container);

            edit_plan = itemView.findViewById(R.id.edit_plan);
            delete_plan = itemView.findViewById(R.id.delete_plan);

            edit_plan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ppos = getAdapterPosition();
                    if(ppos != RecyclerView.NO_POSITION){
                        if(pListener != null){
                            pListener.onEditClick(view, ppos);
                        }
                    }
                }
            });

            delete_plan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ppos = getAdapterPosition();
                    if(ppos != RecyclerView.NO_POSITION){
                        if(pListener != null){
                            pListener.onDeleteClick(view, ppos);
                        }
                    }
                }
            });

        }
    }
}
