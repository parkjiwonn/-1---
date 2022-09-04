package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//trip 화면 넘어갔을때
public class ItemActivity extends AppCompatActivity {

    private Intent intent;
    private int number;
    private String title;
    private ImageView imageView;
    private TextView textView;
    private Button link;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail); //전환된 xml

        intent = getIntent();
        number = intent.getIntExtra("number", -1);
        title = intent.getStringExtra("title");

        imageView = findViewById(R.id.item_detail_image);
        textView = findViewById(R.id.item_detail_text);

        link = findViewById(R.id.link);

        switch (number)
        {
            case 0:
                imageView.setImageResource(R.drawable.banner);
                textView.setText(title);
                textView.append("\n");
                textView.append("여행 유튜버가 추천하는 경주 여행!\n요즘 떠오르는 경주!\n테마파크같은 특별한 경험을 해보세요! ");

                link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jigutour.co.kr/search_list.html?nation=&top_mode=top&tym=&td=&keyword=%BA%CE%BB%EA&n_media=27758&n_query=%EB%B6%80%EC%82%B0%EC%97%AC%ED%96%89&n_rank=5&n_ad_group=grp-m001-01-000000010812712&n_ad=nad-a001-01-000000002379758&n_keyword_id=nkw-m001-01-000000023820207&n_keyword=%EB%B6%80%EC%82%B0%EC%97%AC%ED%96%89&n_campaign_type=1&n_ad_group_type=1&NaPm=ct%3Dl1n9kqyg%7Cci%3D0AC0002Vf4PwemvDiL1p%7Ctr%3Dsa%7Chk%3D25b88cd9869263645d74ccfba7917f021faf17b0"));
                        startActivity(urlintent);

                    }
                });


                break;

            case 1:
                imageView.setImageResource(R.drawable.banner);
                textView.setText(title);
                textView.append("\n");
                textView.append("여행 블로거가 추천하는 전주 여행!\n특별한 날, 소중한 사람들과 특별한 기억을 남기는건 어떠신가요?");

                link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jigutour.co.kr/search_list.html?nation=&top_mode=top&tym=&td=&keyword=%BA%CE%BB%EA&n_media=27758&n_query=%EB%B6%80%EC%82%B0%EC%97%AC%ED%96%89&n_rank=5&n_ad_group=grp-m001-01-000000010812712&n_ad=nad-a001-01-000000002379758&n_keyword_id=nkw-m001-01-000000023820207&n_keyword=%EB%B6%80%EC%82%B0%EC%97%AC%ED%96%89&n_campaign_type=1&n_ad_group_type=1&NaPm=ct%3Dl1n9kqyg%7Cci%3D0AC0002Vf4PwemvDiL1p%7Ctr%3Dsa%7Chk%3D25b88cd9869263645d74ccfba7917f021faf17b0"));
                        startActivity(urlintent);

                    }
                });


                break;

            case 2:
                imageView.setImageResource(R.drawable.banner);
                textView.setText(title);
                textView.append("\n");
                textView.append("여행 블로거가 추천하는 경주 여행!\n특별한 날, 소중한 사람들과 특별한 기억을 남기는건 어떠신가요?");

                break;

            case 3:
                imageView.setImageResource(R.drawable.banner);
                textView.setText(title);
                textView.append("\n");
                textView.append("여행 에디터가 추천하는 부산 여행!\n여름에는 해운대로 떠나야죠!\n가족들과의 부산여행을 계획하는건 어떠신가요?");

                break;

            case 4:
                imageView.setImageResource(R.drawable.banner);
                textView.setText(title);
                textView.append("\n");
                textView.append("여행 에디터가 추천하는 춘천 여행!\n여름에는 해운대로 떠나야죠!\n가족들과의 부산여행을 계획하는건 어떠신가요?");
                break;

            case 5:
                imageView.setImageResource(R.drawable.banner);
                textView.setText(title);
                textView.append("\n");
                textView.append("여행 에디터가 추천하는 순천 여행!\n여름에는 해운대로 떠나야죠!\n가족들과의 부산여행을 계획하는건 어떠신가요?");

                break;

            case 6:
                imageView.setImageResource(R.drawable.banner);
                textView.setText(title);
                textView.append("\n");
                textView.append("여행 에디터가 추천하는 제주도 여행!\n여름에는 해운대로 떠나야죠!\n가족들과의 부산여행을 계획하는건 어떠신가요?");
                break;

            case 7:
                imageView.setImageResource(R.drawable.banner);
                textView.setText(title);
                textView.append("\n");
                textView.append("여행 에디터가 추천하는 영종도 여행!\n여름에는 해운대로 떠나야죠!\n가족들과의 부산여행을 계획하는건 어떠신가요?");
                break;

            case 8:
                imageView.setImageResource(R.drawable.banner);
                textView.setText(title);
                textView.append("\n");
                textView.append("여행 에디터가 추천하는 대전 여행!\n여름에는 해운대로 떠나야죠!\n가족들과의 부산여행을 계획하는건 어떠신가요?");
            break;
        }
    }
}