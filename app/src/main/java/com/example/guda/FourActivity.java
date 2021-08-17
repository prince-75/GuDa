package com.example.guda;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.guda.recyclerviewcontants.Contents;
import com.example.guda.recyclerviewcontants.ContentsAdapter;

import java.util.ArrayList;
import java.util.List;


public class FourActivity extends BaseActivity {
    //滚动目录实列申明
    private List<Contents> contentsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        //隐藏系统自带标题栏
        getSupportActionBar().hide();

        //导航栏按钮
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FourActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FourActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FourActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });

        //ListView滚动屏幕
//        initContents();//滚动数据初始化
//        ContentsAdapter adapter = new ContentsAdapter(FourActivity.this,R.layout.forth_item,contentsList);
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);

        //RecyclerView滚动屏幕
        initContents();//滚动数据初始化
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //设置布局方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//实现横向滚动
        ContentsAdapter adapter = new ContentsAdapter(contentsList);
        recyclerView.setAdapter(adapter);
        //获得屏幕、按钮高度
//        int screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
//        int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）
//        int buttonHeight = button2.getHeight();
        //控制滚动目录范围
//        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
//        layoutParams.height=screenHeight*5/6;

        //分割线 ItemDecoration

        //Item增删动画 ItemAnimator

    }

    private void initContents(){
//        for(int i=0;i<2;i++){
            Contents yundongquanzi = new Contents("运动圈子",R.drawable.gerenziliao);
            contentsList.add(yundongquanzi);
            Contents wodeziliao = new Contents("我的资料",R.drawable.gerenziliao);
            contentsList.add(wodeziliao);
            Contents yundongjihua = new Contents("运动计划",R.drawable.gerenziliao);
            contentsList.add(yundongjihua);
            Contents jiankangfenxi = new Contents("健康分析",R.drawable.gerenziliao);
            contentsList.add(jiankangfenxi);
            Contents lianxikefu = new Contents("联系客服",R.drawable.gerenziliao);
            contentsList.add(lianxikefu);
            Contents changyonglianxiren = new Contents("常用联系人",R.drawable.gerenziliao);
            contentsList.add(changyonglianxiren);
            Contents wodeshoucang = new Contents("我的收藏",R.drawable.gerenziliao);
            contentsList.add(wodeshoucang);
            Contents shenqingrenzheng = new Contents("申请认证",R.drawable.gerenziliao);
            contentsList.add(shenqingrenzheng);
            Contents yonghuyaoqing = new Contents("用户邀请",R.drawable.gerenziliao);
            contentsList.add(yonghuyaoqing);
            Contents hezuoyixiang = new Contents("合作意向",R.drawable.gerenziliao);
            contentsList.add(hezuoyixiang);
            Contents APPxinxi = new Contents("APP信息",R.drawable.gerenziliao);
            contentsList.add(APPxinxi);
//        }
    }


}

