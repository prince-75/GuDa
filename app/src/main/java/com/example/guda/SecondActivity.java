package com.example.guda;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guda.Network.PopupMenuDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity implements Animator.AnimatorListener {
    @BindView(R.id.botton_intent)
    FloatingActionButton botton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //绑定
        ButterKnife.bind(this);
        //导航栏按钮
        Button button2 = (Button)findViewById(R.id.button1);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SecondActivity.this,FourActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.botton_intent)
    public void onClick(View view) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(botton, "translationY", 0, botton
                    .getHeight() * 2).setDuration(200L);
            objectAnimator.setInterpolator(new AccelerateInterpolator());
            objectAnimator.addListener(this);
            objectAnimator.start();
    }

    @Override
    public void onAnimationStart(Animator animation) {
        new PopupMenuDialog(this).builder().setCancelable(false)
                .setCanceledOnTouchOutside(true).show();
    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}