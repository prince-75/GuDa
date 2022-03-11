package com.example.guda.recyclerviewdatasets;

import com.bumptech.glide.load.engine.Resource;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/8.
 * 数据
 */
public class MyData {
    private String title;
    private String content;
    private Integer video;
    private ArrayList<Integer> arrayList;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public MyData() {
    }

    public MyData(String title, ArrayList<Integer> arrayList) {
        this.title = title;
        this.arrayList = arrayList;
    }

    public MyData(String title, String content, ArrayList<Integer> arrayList) {
        this.title = title;
        this.content = content;
        this.arrayList = arrayList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public void setvideo(Integer video) {
        this.video = video;
    }

    public Integer getvideo(){return this.video;}
}
