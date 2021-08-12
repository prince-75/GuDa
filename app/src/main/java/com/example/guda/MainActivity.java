package com.example.guda;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    //滚动目录实列申明
    //private List<Contents> datasetsList = new ArrayList<>();
    //创建菜单
    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    //菜单响应事件
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.add_item:
//                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.remove_item:
//                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//        }
//        return true;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //打印日志
        //Log.d("MainActivity","OnCreate execute");//过滤器、日志级别控制在第二版书1.4

        //隐藏系统自带标题栏
        getSupportActionBar().hide();

        //导航栏按钮
        //setOnClickListener：事件监听器
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
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
                //由一向三实现数据传递
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


    }
}

