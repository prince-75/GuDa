/*
用于实现瀑布流布局的信息显示
利用StaggerGirdLayoutManager
*/
package com.example.guda;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
//    菜单响应事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attention_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.release_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            case R.id.find_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_community);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager((layoutManager));
        CommunityAdapter adapter = new CommunityAdapter(communityList);
        recyclerView.setAdapter(adapter);

    }
    private void initCommunity(){
        for(int i=0;i<2;i++){
        Community jianqing = new Community("减轻颈椎不适——颈后伸抗阻\n",R.drawable.jianqing);
        communityList.add(jianqing);
        Community guanjie = new Community("关节炎发作不能滑雪了QAQ好气好气好气\n",R.drawable.guanjie);
        communityList.add(guanjie);
        Community jianjia = new Community("肩胛骨稳定训练\n",R.drawable.jianjia);
        communityList.add(jianjia);
        Community zhanli = new Community("站立架防足内翻功能成人偏瘫直立架\n",R.drawable.zhanli);
        communityList.add(zhanli);
        Community dongtian = new Community("冬天关节炎要注意的八点！\n",R.drawable.dongtian);
        communityList.add(dongtian);
        Community zhanli2 = new Community("站立架不锈钢成人适用\n\n",R.drawable.zhanli2);
        communityList.add(zhanli2);
        }
    }


}