//用于实现标题栏按钮
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
        Button titleAttention = (Button)findViewById(R.id.attention);
        Button titleRelease = (Button)findViewById(R.id.release);
        Button titleFind = (Button)findViewById(R.id.find);
        titleAttention.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //销毁当前活动，实现返回功能
//                ((Activity) getContext()).finish();
                //文字弹窗
                Toast.makeText(getContext(),"你的关注~就是我产出最大的动力~",Toast.LENGTH_SHORT).show();
            }

        });
        titleRelease.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(),"你不会以为这个功能真的能用了吧",Toast.LENGTH_SHORT).show();
            }
        });
        titleFind.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(),"你点击了一下edit按钮" +
                        "但是我并没有想好要加什么功能",Toast.LENGTH_SHORT).show();
            }
        });

    }
}

