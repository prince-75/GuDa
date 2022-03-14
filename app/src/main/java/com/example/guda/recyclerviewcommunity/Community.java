//定义Community类，同Content类，实例代码在FiveActivity类
package com.example.guda.recyclerviewcommunity;

public class Community {
    private  String name;
    private int imageId;
    public Community(String name, int imageId){
        this.name=name;//名字
        this.imageId=imageId;//对应的图片
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
