package com.example.guda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.jar.Attributes;

public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_title_layout,this);
        Button titleBack = (Button)findViewById(R.id.back);
        Button titleEdit = (Button)findViewById(R.id.edit);
        titleBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //销毁当前活动
                ((Activity) getContext()).finish();
            }
        });

        titleEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(),"你点击了一下edit按钮" +
                        "但是我并没有想好要加什么功能",Toast.LENGTH_SHORT).show();
            }
        });
//        //导航栏按钮
//        Button button1 = (Button)findViewById(R.id.button1);
//        Button button2 = (Button)findViewById(R.id.button2);
//        Button button3 = (Button)findViewById(R.id.button3);
//        Button button4 = (Button)findViewById(R.id.button4);
//        button1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
////                Intent intent = new Intent(BottomInterface.this,MainActivity.class);
////                startActivity(intent);
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
////                Intent intent = new Intent(BottomInterface.this,SecondActivity.class);
////                startActivity(intent);
//            }
//        });
//        button3.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
////                Intent intent = new Intent(BottomInterface.this,ThirdActivity.class);
////                startActivity(intent);
//            }
//        });
//        button4.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
////                Intent intent = new Intent(BottomInterface.this,FourActivity.class);
////                startActivity(intent);
//            }
//        });
    }
}

