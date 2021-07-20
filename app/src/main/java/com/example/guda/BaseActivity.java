//为了方便多人协同工作中找到每一个界面对应的活动
//在Logcat中打印出当前进入的活动的名称
package com.example.guda;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //打印日志
        Log.d("BaseActivity",getClass().getSimpleName());
    }
}
