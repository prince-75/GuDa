package com.example.guda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FourActivity extends BaseActivity {
    private List<Contents> contentsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        //隐藏系统自带标题栏
        getSupportActionBar().hide();

        //导航栏按钮
//        Button button1 = (Button)findViewById(R.id.button1);
//        Button button2 = (Button)findViewById(R.id.button2);
//        Button button3 = (Button)findViewById(R.id.button3);
//        button1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(FourActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(FourActivity.this,SecondActivity.class);
//                startActivity(intent);
//            }
//        });
//        button3.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(FourActivity.this,ThirdActivity.class);
//                startActivity(intent);
//            }
//        });

        //ListView滚动屏幕
//        initContents();//滚动数据初始化
//        ContentsAdapter adapter = new ContentsAdapter(FourActivity.this,R.layout.forth_item,contentsList);
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);

    }

//    private void initContents(){
//        for(int i=0;i<2;i++){
//            Contents wodeziliao11 = new Contents("运动圈子",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao11);
//            Contents wodeziliao = new Contents("我的资料",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao);
//            Contents wodeziliao2 = new Contents("运动计划",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao2);
//            Contents wodeziliao3 = new Contents("健康分析",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao3);
//            Contents wodeziliao4 = new Contents("联系客服",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao4);
//            Contents wodeziliao5 = new Contents("我的资料",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao5);
//            Contents wodeziliao6 = new Contents("常用联系人",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao6);
//            Contents wodeziliao7 = new Contents("我的收藏",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao7);
//            Contents wodeziliao9 = new Contents("申请认证",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao9);
//            Contents wodeziliao10 = new Contents("用户邀请",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao10);
//            Contents wodeziliao12 = new Contents("合作邀请",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao12);
//            Contents wodeziliao13 = new Contents("APP信息",R.drawable.gerenziliao);
//            contentsList.add(wodeziliao13);
//        }
//    }


}

