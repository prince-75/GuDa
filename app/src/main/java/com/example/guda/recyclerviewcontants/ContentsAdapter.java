//创建一个自定义适配器，泛型指向Contents类，实例代码在FourActivity类,有ListView和RecyclerView两个版本，根据需要打开
package com.example.guda.recyclerviewcontants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.guda.R;

import java.util.List;
//ListView控件版本
/*
public class ContentsAdapter extends ArrayAdapter<Contents> {
    private int resourceId;
    //传入上下文、布局、数据
    public ContentsAdapter(Context context, int textViewResourceId, List<Contents> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    //在每个子项被滚动到屏幕内的时候被调用
    public View getView(int position, View convertView, ViewGroup parent) {
        Contents contents = getItem(position);
        View view;

        if(convertView == null){
            //如果布局从未加载过，则加载布局
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        }else{
            //已经加载过则重用
            view = convertView;
        }
        ImageView contentsImage = (ImageView) view.findViewById(R.id.contents_image);
        TextView contentsName = (TextView) view.findViewById(R.id.contents_name);
        contentsImage.setImageResource(contents.getImageId());
        contentsName.setText(contents.getName());
        return view;
    }
}
 */

//RecyclerView版本
public class ContentsAdapter extends RecyclerView.Adapter<ContentsAdapter.ViewHolder>{
    private List<Contents> mContentsList;
    //内部类ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView contentsImage;
        TextView contentsName;
        //ViewHolder：实例缓存、显示子项
        public ViewHolder(View view){//参数view表示RecyclerView子项的最外层布局
            super(view);
            //获取实例
            contentsImage = (ImageView) view.findViewById(R.id.contents_image);
            contentsName = (TextView) view.findViewById(R.id.contents_name);
        }
    }

    //构造函数ContentsAdapter
    public ContentsAdapter(List<Contents> contentsList){
        //全局变量传入数据
        mContentsList = contentsList;
    }

    //重写RecyclerView.Adapter的三个方法p124
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contents_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Contents contents = mContentsList.get(position);
        holder.contentsImage.setImageResource(contents.getImageId());
        holder.contentsName.setText(contents.getName());
    }
    @Override
    public int getItemCount(){
        return mContentsList.size();
    }
}