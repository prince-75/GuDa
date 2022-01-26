package com.example.guda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guda.recyclerviewdatasets.BaseHolder;
import com.example.guda.recyclerviewdatasets.MyData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    //滚动目录实列申明
    private RecyclerView recylcerview;//外层recyclerview
    private ArrayList<MyData> data;//假数据
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
//        Button button3 = (Button) findViewById(R.id.button3);
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
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
//                //由一向三实现数据传递
////                intent.putExtra("param1", "data1");
////                intent.putExtra("param2", "data2");
//                startActivity(intent);
//            }
//        });
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
    private ArrayList<MyData> initData() {
//      最外层的list
        ArrayList<MyData> rootList = new ArrayList<MyData>();
//      条目1
        MyData myData1 = new MyData();
        String s1 = "拉伸训练";
        ArrayList<Integer> tuList1 = new ArrayList<>();
        tuList1.add(R.drawable.datasets1);
        tuList1.add(R.drawable.datasets2);
        tuList1.add(R.drawable.datasets3);
        myData1.setArrayList(tuList1);
        myData1.setTitle(s1);
        rootList.add(myData1);
//      条目2
        MyData myData2 = new MyData();
        String s2 = "腿部训练";
        ArrayList<Integer> tuList2 = new ArrayList<>();
        tuList2.add(R.drawable.datasets4);
        tuList2.add(R.drawable.datasets5);
        tuList2.add(R.drawable.datasets6);
        myData2.setTitle(s2);
        myData2.setArrayList(tuList2);
        rootList.add(myData2);
//      条目1
        MyData myData3 = new MyData();
        String s3 = "腰腹训练";
        ArrayList<Integer> tuList3 = new ArrayList<>();
        tuList3.add(R.drawable.datasets1);
        tuList3.add(R.drawable.datasets2);
        tuList3.add(R.drawable.datasets3);
        myData3.setArrayList(tuList3);
        myData3.setTitle(s3);
        rootList.add(myData3);
//      条目2
        MyData myData4 = new MyData();
        String s4 = "核心训练";
        ArrayList<Integer> tuList4 = new ArrayList<>();
        tuList4.add(R.drawable.datasets4);
        tuList4.add(R.drawable.datasets5);
        tuList4.add(R.drawable.datasets6);
        myData4.setTitle(s4);
        myData4.setArrayList(tuList4);
        rootList.add(myData4);
//      条目1
        MyData myData5 = new MyData();
        String s5 = "柔韧训练";
        ArrayList<Integer> tuList5 = new ArrayList<>();
        tuList5.add(R.drawable.datasets1);
        tuList5.add(R.drawable.datasets2);
        tuList5.add(R.drawable.datasets3);
        myData5.setArrayList(tuList5);
        myData5.setTitle(s5);
        rootList.add(myData5);
//      条目2
        MyData myData6 = new MyData();
        String s6 = "力量训练";
        ArrayList<Integer> tuList6 = new ArrayList<>();
        tuList6.add(R.drawable.datasets4);
        tuList6.add(R.drawable.datasets5);
        tuList6.add(R.drawable.datasets6);
        myData6.setTitle(s6);
        myData6.setArrayList(tuList6);
        rootList.add(myData6);

        return rootList;

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
        //实例数据初始化
        ArrayList<MyData> rootlist = initData();
        //初始化外层RecyclerView的Adapter
        recylcerview.setAdapter(new RecyclerViewAdapter(rootlist,MainActivity.this));
    }

    /**
     * 将dp转化为px
     */
    private int dip2px(float dip) {
        float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return (int) (v + 0.5f);
    }

    /**
     * 外层RecyclerView的Adapter
     * 外层的RecyclerView
     */
    private class RecyclerViewAdapter extends RecyclerView.Adapter<BaseHolder> {
        private ArrayList<MyData> rootList = null;
        private Context context;

        public RecyclerViewAdapter(ArrayList<MyData> rootList, Context context) {
            this.rootList = rootList;
            this.context = context;
        }

        //条目样式
        private final int HORIZONTAL_VIEW = 1000;

        @Override
        public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case HORIZONTAL_VIEW:
                    return new HorizontalViewHolder(R.layout.item_recyclerview_datasets, parent, viewType);

            }
            return null;
        }

        @Override
        public void onBindViewHolder(BaseHolder holder, int position) {
            if (holder instanceof HorizontalViewHolder) {
                holder.refreshData(rootList, position);
            }
        }

        @Override
        public int getItemCount() {
            return rootList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return HORIZONTAL_VIEW;


        }
    }


    //----------------------外层子条目Holder----------------------------

    /**
     * 嵌套的水平RecyclerView
     * 绑定的外层的RecyclerView的条目
     */
    private class HorizontalViewHolder extends BaseHolder<List<MyData>> {
        private RecyclerView hor_recyclerview;
        private TextView tvMyTitle;
        private TextView tvMyContent;

        private List<MyData> data;

        public HorizontalViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            //用于申明外层RecyclerView的子条目显示格式
            hor_recyclerview = (RecyclerView) itemView.findViewById(R.id.item_recyclerview);
            tvMyTitle = (TextView) itemView.findViewById(R.id.tvMyTitle);
            Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/typeface.ttf");
            tvMyTitle.setTypeface(typeFace);
//            tvMyContent = (TextView) itemView.findViewById(R.id.tvMyContent);

        }

        @Override
        public void refreshData(List<MyData> data, int position) {
            this.data = data;
            ViewGroup.LayoutParams layoutParams = hor_recyclerview.getLayoutParams();
            //高度等于＝条目的高度＋ 10dp的间距 ＋ 10dp（为了让条目居中）
            layoutParams.height = screenWidth / 3 + dip2px(20);
            hor_recyclerview.setLayoutParams(layoutParams);
            hor_recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
            hor_recyclerview.setBackgroundResource(R.color.blue);


            String text = data.get(position).getTitle();
            String content = data.get(position).getContent();
//          判断如果仿图片的listbu存在，就隐藏hor_recyclerview
            if(null== data.get(position).getArrayList()) {
                hor_recyclerview.setVisibility(View.GONE);
            }
            //加固定后缀
//            tvMyTitle.setText(text + ">>>");
//            tvMyContent.setText(content+"666");
            //不加后缀
            tvMyTitle.setText(text);

            ArrayList<Integer> intlist = data.get(position).getArrayList();
            hor_recyclerview.setAdapter(new HorizontalAdapter(intlist, MainActivity.this));
        }
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>内层的RecyclerView>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        /**
         * 内层的RecyclerView
         */
        private class HorizontalAdapter extends RecyclerView.Adapter<BaseHolder> {
            private ArrayList<Integer> intlist;
            private Context context;

            private HorizontalAdapter(ArrayList<Integer> intlist, Context context) {
                this.intlist = intlist;
                this.context = context;
            }

            @Override
            public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ItemViewHolder(R.layout.item_datasets, parent, viewType);
            }

            @Override
            public void onBindViewHolder(BaseHolder holder, int position) {
                holder.refreshData(intlist, position);
            }

            @Override
            public int getItemCount() {
                if (null != intlist) {
                    return intlist.size();
                }else{
                    return 0;
                }

            }
        }
    }

    /**
     * 通用子条目hodler
     * 绑定内层RecyclerView的条目
     */
    private class ItemViewHolder extends BaseHolder<ArrayList<Integer>> {

        private ImageView imageview_item;

        public ItemViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            imageview_item = (ImageView) itemView.findViewById(R.id.imageview_item);
            ViewGroup.LayoutParams layoutParams = imageview_item.getLayoutParams();
            layoutParams.width = layoutParams.height = screenWidth / 3;
            imageview_item.setLayoutParams(layoutParams);
        }

        @Override
        public void refreshData(ArrayList<Integer> intlist, int position) {
            super.refreshData(intlist, position);
            if(intlist==null) {
                imageview_item.setVisibility(View.GONE);
            }
            Integer intPosition = intlist.get(position);
            imageview_item.setBackgroundResource(intPosition);

        }
    }

}