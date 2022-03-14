/*
用于实现瀑布流布局的信息显示
利用StaggerGirdLayoutManager
*/
package com.example.guda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.guda.recyclerviewcommunity.Community;
import com.example.guda.recyclerviewcommunity.CommunityAdapter;
import com.example.guda.recyclerviewcontants.Content;
import com.example.guda.recyclerviewinformation.Information;
import com.example.guda.recyclerviewinformation.InformationAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FiveActivity extends BaseActivity {
    //瀑布流布局实列申明
    private List<Community> communityList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        //隐藏系统自带标题栏
        getSupportActionBar().hide();
        //导航栏按钮
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
//        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(com.example.guda.FiveActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
//        button3.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
//                startActivity(intent);
//            }
//        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiveActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(com.example.guda.FiveActivity.this,FourActivity.class);
                startActivity(intent);
            }
        });
        //RecyclerView滚动屏幕
        initCommunity();//滚动数据初始化
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_contents);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager((layoutManager));
        CommunityAdapter adapter = new CommunityAdapter(communityList);
        recyclerView.setAdapter(adapter);

    }
    private void initCommunity(){
        for(int i=0;i<2;i++){
        Community yundongquanzi = new Community("运动圈子",R.drawable.gerenziliao);
        communityList.add(yundongquanzi);
        Community wodeziliao = new Community("我的资料",R.drawable.gerenziliao);
        communityList.add(wodeziliao);
        Community yundongquanzi1 = new Community("运动圈子",R.drawable.gerenziliao);
        communityList.add(yundongquanzi1);
        Community wodeziliao1 = new Community("我的资料",R.drawable.gerenziliao);
        communityList.add(wodeziliao1);
        }
    }

    private String getRandomLengthName(String name){
        Random random = new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }


}