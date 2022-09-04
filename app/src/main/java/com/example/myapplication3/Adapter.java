package com.example.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private String TAG = "Adapter";
    private Context mContext;
    private ArrayList<Data> mArrayList; //데이터를 담을 어레이리스트


    public Adapter(Context context, ArrayList<Data> arrayList) { //어댑터 생성자
        this.mArrayList = arrayList; //데이터를 담고
        this.mContext =context; //xml 리소스 접근을 위해 필요.

    }

    //어댑터는 하나의 객체로 보여지는 view와  그 view에 올릴 data를 연결해준다.
    //데이터의 원본을 받아서 관리하고, 어댑터 뷰가 출력할 수 있는 형태로 데이터를 제공하는 중간 객체 역할을 한다.



    //아이템 클릭리스너 인터페이스
    interface OnItemClickListener{
        void onItemClick(View v, int position); //뷰와 포지션값
        void onEditClick(View v, int position); //수정
        void onDeleteClick(View v, int position);//삭제
    }

    //액티비티에서 아이템 클릭 이벤트를 처리하고자 할때
    //어댑터에 직접 리스너 인터페이스를 정의하고 해당 리스터 객체를 생성하고, 어댑터에 전달해 호출되도록 만들면 됨.

    private OnItemClickListener mListener = null;
    // 리스터 객체 잠조를 저장하는 변수

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    // OnItemClickListener 리스너 객체를 어댑터에 전달하는 메서드.


    //리스트의 각 항목을 이루는 디자인(xml)을 적용.
    @NonNull
    @Override
    //view type 형태의 아이템뷰를 위한 뷰 홀더 객체 생성함.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        // layoutinflater는 xml에 미리 정의해둔 툴을 실제 메모리에 올려준다. inflater는 부풀리다라는 의미


        View view = inflater.inflate (R.layout.item, parent, false);
        // Xml에 덩의된 resource를 view 객체로 반환해주는 역할을 한다.
        // 레이아웃정의부에서 실행되고 메모리 로딩이 돼 화면에 띄워주는 역할.

        //false는 바로 인플레이션 하지 x, true는 바로 인플에이션 한다.
        ViewHolder vh = new ViewHolder (view);
        return vh;
    }

    //리스트의 각 항목에 들어갈 데이터를 지정.
    //position에 해당하는 데이터를 뷰 홀더의 아이템 뷰에 표시함.

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        Data data = mArrayList.get (position);
        holder.tv_name.setText (data.getName ());
        holder.tv_number.setText (data.getNumber ());

    }

    //화면에 보여줄 데이터의 갯수를 반환.
    //데이터리스트의 사이즈만큼 반환하겠다.
    //총 내가 갖고있는 아이템 개수
    @Override
    public int getItemCount() {

        return mArrayList.size ();
    }

    //뷰홀더 객체에 저장되어 화면에 표시되고, 필요에 따라 생성 또는 재활용 된다.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_number;
        Button btn_edit, btn_delete;
        // 뷰홀더에 들어가야할 아이템들 정의

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            this.tv_name = itemView.findViewById (R.id.tv_name);
            this.tv_number = itemView.findViewById (R.id.tv_number);
            this.btn_edit = itemView.findViewById (R.id.btn_edit);
            this.btn_delete = itemView.findViewById (R.id.btn_delete);
            // 맵핑 시켜주기.


            // 리사이클러뷰 안에있는 요소들을 클릭했을 때 발생하는 이벤트들을 관리하기 위해서는
            // 뷰홀더 안에서 클릭 이벤트들을 처리해줘야 한다.//
            // 이유 : 리사이클러 뷰는 아이템 클릭 이벤트 리스너를 자신이 직접 다루지 않고 아이템 뷰에서 onclicklister를 통해 처리함.
            // 아이템뷰에서 클릭이벤트를 직접처리하고 아이템뷰는 뷰홀더 객체가 가지고 있으니, 아이템 클릭 이벤트는 뷰홀더에서 작성하면 된다.

            //어댑터 내 뷰홀더에서 아이템 클릭 시, 커스텀 이벤트 메서드를 호출하는 코드를 작성한다.
            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition ();
                    if (position!=RecyclerView.NO_POSITION){
                        //리스너 객체의 호출
                        if (mListener!=null){
                            mListener.onItemClick (view,position);

                        }
                    }
                }
            });

            btn_edit.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition ();
                    if (position!=RecyclerView.NO_POSITION){
                        if (mListener!=null){
                            mListener.onEditClick (view,position);
                        }
                    }
                }
            });

            btn_delete.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition ();
                    if (position!=RecyclerView.NO_POSITION){
                        if (mListener!=null){
                            mListener.onDeleteClick(view,position);
                        }
                    }
                }
            });
        }
    }
}
