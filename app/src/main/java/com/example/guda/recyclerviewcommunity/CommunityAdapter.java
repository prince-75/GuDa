package com.example.guda.recyclerviewcommunity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.guda.R;

import java.util.List;

//RecyclerView版本
public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder>{
    private List<Community> mCommunityList;
    //内部类ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView communityImage;
        TextView communityName;
        //ViewHolder：实例缓存、显示子项
        public ViewHolder(View view){//参数view表示RecyclerView子项的最外层布局
            super(view);
            //获取实例
            communityImage = (ImageView) view.findViewById(R.id.community_image);
            communityName = (TextView) view.findViewById(R.id.community_name);
        }
    }

    //构造函数CommunityAdapter
    public CommunityAdapter(List<Community> communityList){
        //全局变量传入数据
        mCommunityList = communityList;
    }

    //重写RecyclerView.Adapter的三个方法p124
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Community community = mCommunityList.get(position);
        holder.communityImage.setImageResource(community.getImageId());
        holder.communityName.setText(community.getName());
    }
    @Override
    public int getItemCount(){
        return mCommunityList.size();
    }
}