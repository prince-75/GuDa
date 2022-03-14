package com.example.guda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guda.recyclerviewinformation.Information;
import com.example.guda.recyclerviewinformation.InformationAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends BaseActivity {

    private List<Information> informationList = new ArrayList<>();
    ListView listV ;
    ArrayList<String> selected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //隐藏系统自带标题栏
        getSupportActionBar().hide();
        //导航栏按钮
        Button button1 = (Button) findViewById(R.id.button1);
//        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(com.example.guda.SecondActivity.this,MainActivity.class);
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
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, FiveActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(com.example.guda.SecondActivity.this,FourActivity.class);
                startActivity(intent);
            }
        });
//        listV = this.findViewById(R.id.lv_sele);
//        listV.setOnItemClickListener(this);
        initContents();//滚动数据初始化
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_contents);
        //设置布局方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//实现横向滚动
        InformationAdapter adapter = new InformationAdapter(this, informationList);
        recyclerView.setAdapter(adapter);


    }



    private void initContents(){
        informationList.add(new Information("一弯腰就腰痛？或预警着这几种腰椎疾病", R.drawable.wanyao, "https://mp.weixin.qq.com/s?__biz=MzIxMjI4OTk5OQ==&mid=2247492195&idx=1&sn=10027a2cfb86edf8921f7255c6e61ddb&chksm=974af2c3a03d7bd515c6ed94cb00b9b9ca8fc4f7cffcb932232c100f851bf00ce02aa847712f&token=523158986&lang=zh_CN#rd"));
        informationList.add(new Information("心律失常竟与颈椎病有关！专家提醒，科学的康复治疗可防心律失常加重", R.drawable.jingzhui, "https://mp.weixin.qq.com/s?__biz=MzIxMjI4OTk5OQ==&mid=2247492056&idx=1&sn=56e03dae748569bc33b3e8de8e6b0a24&chksm=974af178a03d786ef6fa7d742a7c86d44f8c05f9796984aebcbf0132bc6832429fed3d48e9cd&token=1451748677&lang=zh_CN#rd"));
        informationList.add(new Information("久坐、长期少运动？当心你的身体这样狠狠地报复你", R.drawable.jiuzuo, "https://mp.weixin.qq.com/s/OGjuytkXjlmWhIxBb3_7NA"));
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        TextView textView = (TextView) view;        //被点击的view对象转换成textView对象
//        String item = textView.getText().toString();//获取选中的文字
//        if(selected.contains(item)){                //如果集合中已经有该选项，再次点击则删除
//            selected.remove(item);
//        }else{                                      //否则就添加进去
//            selected.add(item);
//        }
//        String msg ;
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("https://mp.weixin.qq.com/s?__biz=MzIxMjI4OTk5OQ==&mid=2247492203&idx=1&sn=174ac64f127b9962a70cea4397cac828&chksm=974af2cba03d7bddc1d91b4df52d7117df1be888719d8a62c56dd8fd826e96171d3a0ad71533&token=1243087098&lang=zh_CN#rd"));
//        startActivity(intent);
////        if(selected.size()>0){
////            msg = "你喜欢的运动有：";
////            for(String str : selected){             //遍历数组中的选项，转换成字符串添加到msg中
////                msg += str.toString() + "  |  ";
////            }
////        }else{
////            msg = "请选择你喜欢的运动！";
////        }
//        TextView msgText = this.findViewById(R.id.select);
////        msgText.setText(msg);
//    }

//    private void initContents(){
////        for(int i=0;i<2;i++){
//        Information yundongquanzi = new Information("运动圈子",R.drawable.gerenziliao);
//        informationList.add(yundongquanzi);
//        Information wodeziliao = new Information("我的资料",R.drawable.gerenziliao);
//        informationList.add(wodeziliao);
//        Information yundongjihua = new Information("运动计划",R.drawable.gerenziliao);
//        informationList.add(yundongjihua);
//        Information jiankangfenxi = new Information("健康分析",R.drawable.gerenziliao);
//        informationList.add(jiankangfenxi);
//        Information lianxikefu = new Information("联系客服",R.drawable.gerenziliao);
//        informationList.add(lianxikefu);
//        Information changyonglianxiren = new Information("常用联系人",R.drawable.gerenziliao);
//        informationList.add(changyonglianxiren);
//
////        }
//    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(SecondActivity.this, "test", Toast.LENGTH_SHORT).show();
//    }

//    public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
//
//        private String[] localDataSet;
//
//        /**
//         * Provide a reference to the type of views that you are using
//         * (custom ViewHolder).
//         */
//        public static class ViewHolder extends RecyclerView.ViewHolder {
//            private final TextView textView;
//
//            public ViewHolder(View view) {
//                super(view);
//                // Define click listener for the ViewHolder's View
//
//                textView = (TextView) view.findViewById(R.id.textView);
//            }
//
//            public TextView getTextView() {
//                return textView;
//            }
//        }
//
//        /**
//         * Initialize the dataset of the Adapter.
//         *
//         * @param dataSet String[] containing the data to populate views to be used
//         * by RecyclerView.
//         */
//        public CustomAdapter(String[] dataSet) {
//            localDataSet = dataSet;
//        }
//
//        // Create new views (invoked by the layout manager)
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//            // Create a new view, which defines the UI of the list item
//            View view = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.information_list_item, viewGroup, false);
//
//            return new ViewHolder(view);
//        }
//
//        // Replace the contents of a view (invoked by the layout manager)
//        @Override
//        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//
//            // Get element from your dataset at this position and replace the
//            // contents of the view with that element
//            viewHolder.getTextView().setText(localDataSet[position]);
//        }
//
//        // Return the size of your dataset (invoked by the layout manager)
//        @Override
//        public int getItemCount() {
//            return localDataSet.length;
//        }
//    }



}