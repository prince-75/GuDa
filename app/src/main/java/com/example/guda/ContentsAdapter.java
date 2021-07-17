//创建一个自定义适配器，泛型指向Contents类，实例代码在FourActivity类
package com.example.guda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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

