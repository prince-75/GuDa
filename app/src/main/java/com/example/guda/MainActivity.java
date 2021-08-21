package com.example.guda;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guda.recyclerviewdatasets.BaseHolder;
import com.example.guda.recyclerviewdatasets.Datasets;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    //滚动目录实列申明
    private RecyclerView recylcerview;//外层recyclerview
    private Datasets data;//数据
    private int screenWidth;//屏幕宽度
    private int HORIZONTAL_VIEW_X = 0;//水平RecyclerView滑动的距离
    private TextView mTextView;

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
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
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
//                intent.putExtra("param1", "data1");
//                intent.putExtra("param2", "data2");
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

        //RecyclerView滚动目录
        basicParamInit();//计算屏幕宽度，用于计算一行放几个button
        initData();//数据初始化
        initRecyclerView();//外层recyclerView格式初始化

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


    /**
     * 计算屏幕的宽度
     */
    private void basicParamInit() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        screenWidth = metric.widthPixels;

    }

    /**
     * 初始化显示数据
     */
    private void initData() {
        data = new Datasets();
        ArrayList<Integer> datasetsList = new ArrayList<>();
        datasetsList.add(R.drawable.datasets1);
        datasetsList.add(R.drawable.datasets2);
        datasetsList.add(R.drawable.datasets3);
        datasetsList.add(R.drawable.datasets4);
        datasetsList.add(R.drawable.datasets5);
        datasetsList.add(R.drawable.datasets6);

        data.gridData = data.horizontalData = data.verticalData = datasetsList;
    }

    /**
     * 初始化外层RecyclerView
     */
    private void initRecyclerView() {
        recylcerview = (RecyclerView) findViewById(R.id.recyclerview_datasets);
        //竖直排列、正向排序
        recylcerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //添加了一个蓝色背景
        recylcerview.setBackgroundResource(R.color.blue);
        //初始化外层RecyclerView的Adapter
        recylcerview.setAdapter(new RecyclerViewAdapter());
    }

    /**
     * 外层RecyclerView的Adapter
     */
    private class RecyclerViewAdapter extends RecyclerView.Adapter<BaseHolder> {
        //条目样式
        private final int GRID_VIEW = 1002;

        @Override
        public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GridViewHolder(R.layout.item_recyclerview_datasets, parent, viewType);
        }

        @Override
        public void onBindViewHolder(BaseHolder holder, int position) {
            holder.refreshData(data.gridData, position);
        }

        @Override
        /**
         * 当Item出现时调用此方法
         */
        public void onViewAttachedToWindow(BaseHolder holder) {
            Log.i("Prince75", "onViewAttachedToWindow:" + holder.getClass().toString());
        }

        @Override
        /**
         * 当Item被回收时调用此方法
         */
        public void onViewDetachedFromWindow(BaseHolder holder) {
            Log.i("Prince75", "onViewDetachedFromWindow:" + holder.getClass().toString());
        }


        @Override
        public int getItemCount() {
            return 2 + data.verticalData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return GRID_VIEW;
        }
    }


    //----------------------外层子条目Holder----------------------------

    /**
     * GridView形状的RecyclerView
     */
    private class GridViewHolder extends BaseHolder<List<Integer>> {

        private RecyclerView item_recyclerview;

        private final int ONE_LINE_SHOW_NUMBER = 3;

        private List<Integer> data;

        public GridViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            item_recyclerview = (RecyclerView) itemView.findViewById(R.id.item_recyclerview_datasets_lashen);

        }

        @Override
        public void refreshData(List<Integer> data, int position) {
            super.refreshData(data, position);
            this.data = data;
            //每行显示3个，水平显示
            item_recyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this, ONE_LINE_SHOW_NUMBER, LinearLayoutManager.VERTICAL, false));
            //设置个背景色
            item_recyclerview.setBackgroundResource(R.color.blue);
            //设置Adapter
            item_recyclerview.setAdapter(new GridAdapter());
        }


        private class GridAdapter extends RecyclerView.Adapter<BaseHolder> {

            @Override
            public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ItemViewHolder(R.layout.item_datasets, parent, viewType);
            }

            @Override
            public void onBindViewHolder(BaseHolder holder, int position) {
                holder.refreshData(data.get(position), position);
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        }


    }

    //----------------------内层子条目Holder----------------------------

    /**
     * 通用子条目hodler
     */
    private class ItemViewHolder extends BaseHolder<Integer> {

        private ImageView imageview_item;

        public ItemViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            imageview_item = (ImageView) itemView.findViewById(R.id.imageview_item);
            ViewGroup.LayoutParams layoutParams = imageview_item.getLayoutParams();
            layoutParams.width = layoutParams.height = screenWidth / 3;//一行三个
            imageview_item.setLayoutParams(layoutParams);
        }

        @Override
        public void refreshData(Integer data, final int position) {
            imageview_item.setBackgroundResource(data);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}