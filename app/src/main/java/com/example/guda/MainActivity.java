package com.example.guda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    //滚动目录实列申明
    private List<Contents> contentsList = new ArrayList<>();
    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //菜单响应事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //打印日志
        //Log.d("MainActivity","OnCreate execute");//过滤器、日志级别控制在第二版书1.4

        //隐藏系统自带标题栏
        //getSupportActionBar().hide();

        //导航栏按钮
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        //setOnClickListener：事件监听器
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                //toast：弹出提醒
                Toast.makeText(MainActivity.this, "哇，你学会转换界面了哟，真棒", Toast.LENGTH_SHORT).show();
                //销毁当前活动(和back一样用）
                //finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("param1","data1");
                intent.putExtra("param2","data2");
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FourActivity.class);
                startActivity(intent);
            }
        });

        //隐式Intent访问外部网页
/*
        Button button_intent = (Button) findViewById(R.id.button_intent);
        button_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://github.com/prince-75/GuDa"));
                        startActivity(intent);
                    }
                });
            }
        });
*/

        //RecyclerView滚动屏幕
        initContents();//滚动数据初始化
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);//线性布局
        recyclerView.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//实现横向滚动
        ContentsAdapter adapter = new ContentsAdapter(contentsList);
        recyclerView.setAdapter(adapter);

    }

    private void initContents(){
        for(int i=0;i<2;i++){
            Contents wodeziliao11 = new Contents("运动圈子",R.drawable.gerenziliao);
            contentsList.add(wodeziliao11);
            Contents wodeziliao = new Contents("我的资料",R.drawable.gerenziliao);
            contentsList.add(wodeziliao);
            Contents wodeziliao2 = new Contents("运动计划",R.drawable.gerenziliao);
            contentsList.add(wodeziliao2);
            Contents wodeziliao3 = new Contents("健康分析",R.drawable.gerenziliao);
            contentsList.add(wodeziliao3);
            Contents wodeziliao4 = new Contents("联系客服",R.drawable.gerenziliao);
            contentsList.add(wodeziliao4);
            Contents wodeziliao5 = new Contents("我的资料",R.drawable.gerenziliao);
            contentsList.add(wodeziliao5);
            Contents wodeziliao6 = new Contents("常用联系人",R.drawable.gerenziliao);
            contentsList.add(wodeziliao6);
            Contents wodeziliao7 = new Contents("我的收藏",R.drawable.gerenziliao);
            contentsList.add(wodeziliao7);
            Contents wodeziliao9 = new Contents("申请认证",R.drawable.gerenziliao);
            contentsList.add(wodeziliao9);
            Contents wodeziliao10 = new Contents("用户邀请",R.drawable.gerenziliao);
            contentsList.add(wodeziliao10);
            Contents wodeziliao12 = new Contents("合作邀请",R.drawable.gerenziliao);
            contentsList.add(wodeziliao12);
            Contents wodeziliao13 = new Contents("APP信息",R.drawable.gerenziliao);
            contentsList.add(wodeziliao13);
        }
    }
}

